package coolidge_bus;

// Class for the Bus
public class Bus implements Runnable
{
	private boolean waiting = false;
	private BusStop bs;
	private BusStop bclass;
	private Student[] seats = new Student[50];
	private int seatCounter = 0;
	
	public Bus(BusStop bs, BusStop bclass)
	{
		this.bs = bs;
		this.bclass = bclass;
		
		for (int i = 0; i < 50; i++)
			seats[i] = null;
	}
	
	@Override
	public void run()
	{
		try
		{
			// Check for students at first bus stop
			if (bs.getWaiting() != 0)
				waiting = true;
			
			// Continue looping as long as one bus stop has students waiting
			while(waiting == true)
			{
				// Arrive at first bus stop
				arrive(bs);
				depart(bs.getName(), "classroom stop");
				
				// Travel to next bus stop
				Thread.sleep(10000);
				arrive(bclass);
				depart(bclass.getName(), "bus stop");
			}
			
			System.out.println("No more students, bus driver going home for today");
			
		} catch (InterruptedException e) {}
	}
	
	// Bus arrives at a bus stop
	private void arrive(BusStop stop) throws InterruptedException
	{
		stop.getLock().acquire();
		System.out.printf("Bus has arrived at %s\n", stop.getName());
		
		// If students are waiting
		if (stop.getWaiting() != 0)
		{
			System.out.println("Letting students board");
			stop.getStudentBoard().release();  // let student on
			stop.getBusDepart().acquire();  // tell bus to wait
		}
		
		stop.getLock().release();
	}
	
	// Bus departs a bus stop and travels to it's location
	private void depart(String stopName, String dest) throws InterruptedException
	{
		System.out.printf("Departing %s now\n", stopName);
		
		// Travel to destination then let everyone off
		Thread.sleep(10000);
		System.out.printf("Letting students off at %s\n", dest);
		emptyBus(dest);
		
		// Check for more students waiting
		if ((bs.getWaiting() == 0) && (bclass.getWaiting() == 0))
			waiting = false;
	}
	
	// Bus is at its destination, everyone gets off
	private void emptyBus(String dest)
	{
		for (int k = seatCounter - 1; k >= 0; k--)
		{
			System.out.printf("Student %d is getting off the bus at %s\n",
					seats[k].getId(), dest);
			seats[k] = null;
		}
		
		seatCounter = 0;
	}
	
	// Student claims a seat on the bus
	public void getSeat(Student s)
	{
		seats[seatCounter] = s;
		seatCounter++;
	}
}
