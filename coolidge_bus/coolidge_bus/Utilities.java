package coolidge_bus;

import java.util.concurrent.ThreadLocalRandom;

public class Utilities implements Runnable
{
	private int numStudents;
	private BusStop bs;
	private BusStop bclass;
	private Bus b;
	
	// DataContainer dc
	public Utilities(int NUMSTUDENTS, BusStop bs, BusStop bclass, Bus b)
	{
		this.numStudents = NUMSTUDENTS;
		this.bs = bs;
		this.bclass = bclass;
		this.b = b;
	}
	
	@Override
	// Generate specified number of students
	public void run()
	{
		for (int i = 0; i < numStudents; i++)
		{
			try
			{
				// Send Student to Bus Stop
				Student s = new Student(i, b, bs, bclass);
				Thread t = new Thread(s);
				t.start();
				
				// Wait 1 - 3 seconds to create next Student at the bus stop
				int time = ThreadLocalRandom.current().nextInt(1, 3 + 1);
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {}
		}
	}
}
