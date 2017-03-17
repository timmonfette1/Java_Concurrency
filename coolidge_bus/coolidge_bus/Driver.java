package coolidge_bus;

// Main class for running the Coolidge Bus
public class Driver
{
	private static int NUMSTUDENTS;
	
	public static void main(String args[])
	{
		try
		{
			checkArgs(args);
			
			// Initial Bus Stop
			BusStop bs = new BusStop("bus stop");
			
			// Bus stop for where the students have class
			BusStop bclass = new BusStop("classroom stop");
			
			// Create a new bus thread and start generating students
			Bus b = new Bus(bs, bclass);
			Thread tb = new Thread(b);
			
			// Utility for generation of students
			Utilities u = new Utilities(NUMSTUDENTS, bs, bclass, b);
		
			Thread tu = new Thread(u);
			tu.start();
		
			// Bus waits for students to fill up the Bus Stop before starting it's
			// day of driving
			Thread.sleep(160000);
			
			tb.start();
			
			tu.join();
			tb.join();
		} catch (InterruptedException e) {}
	}
	
	// Process Args
	private static void checkArgs(String[] args)
	{
		// If no args provided, default to 50 students
		if (args.length == 0)
			NUMSTUDENTS = 50;
		else
		{
			for (int i = 0; i < args.length; i++)
			{
				// Otherwise use arguments provided for NUMSTUDENTS
				if (args[i].equals("-NUMSTUDENTS"))
				{
					String temp = args[i+1];
					temp = temp.replaceAll(",", "");
					NUMSTUDENTS = Integer.parseInt(temp.trim());
				}
			}
		}
	}
}
