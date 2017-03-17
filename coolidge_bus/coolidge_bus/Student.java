package coolidge_bus;

import java.util.concurrent.ThreadLocalRandom;

// Class for the Student
public class Student implements Runnable
{
	private int studentId;
	private Bus b;
	private BusStop bs;
	private BusStop bclass;

	public Student(int studentId, Bus b, BusStop bs, BusStop bclass)
	{
		this.studentId = studentId;
		this.b = b;
		this.bs = bs;
		this.bclass = bclass;
	}
	
	@Override
	public void run()
	{
		try
		{
			// Enter the first bus stop
			enterBusStop(bs);
			boardBus(bs);
			
			// Go to class for 20-30 seconds (the dream school day)
			int time = ThreadLocalRandom.current().nextInt(20, 30 + 1);
			Thread.sleep(time * 1000);
			
			enterBusStop(bclass);
			boardBus(bclass);
			
		} catch (InterruptedException e) {}
	}
	
	// Student enters the Bus Stop
	private void enterBusStop(BusStop stop) throws InterruptedException
	{
		// Acquire semaphore for bus stop (limit of 50)
		stop.getBusStopWaiting().acquire();
		
		// Crit section
		stop.getLock().acquire();
		System.out.printf("Student %d entering %s\n", studentId, stop.getName());
		stop.newStudentWaiting();  // Increment waiting student counter
		stop.getLock().release();
	}
	
	// Student boards the bus
	private void boardBus(BusStop stop) throws InterruptedException
	{
		// Declare yourself as getting on the bus and get a seat
		stop.getStudentBoard().acquire();
		System.out.printf("Student %d boarding bus at %s\n", studentId, stop.getName());
		b.getSeat(this);
		stop.getBusStopWaiting().release();  // release yourself from the bus stop semaphore
		stop.studentOnboard();  // decrement waiting student counter
		
		// If no more students are waiting, bus can leave.
		// Otherwise, give up the boarding semaphore to let another student board
		if (stop.getWaiting() == 0)
			stop.getBusDepart().release();
		else
			stop.getStudentBoard().release();
	}
	
	public int getId()
	{
		return studentId;
	}
}
