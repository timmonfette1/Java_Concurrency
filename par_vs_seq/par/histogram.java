package cs361_1_par;

import java.util.Random;

// Set initial to 3gb, max to 4gb
public class histogram
{
	private static int NUMBINS;
	private static int DATASIZE;
	private static int NUMTHREADS;
	
	public static void main(String[] args)
	{
		checkArguments(args);
		
		int[] data = createData(DATASIZE);
				
		System.out.println();
		
		// Create the dataContainer for the data
		dataContainer dc = new dataContainer(data, NUMBINS);
		
		final double startTime = System.nanoTime();
		buildHistogram((DATASIZE / NUMTHREADS), dc);
		final double endTime = System.nanoTime();
		final double execTime = endTime - startTime;
		
		printHistogram(dc);
		
		System.out.println("\nTotal execution time (miliseconds): " + (execTime / 1000000));
		System.out.println("Total execution time (seconds): " + (execTime / (1000000 * 1000)) + "\n");
	}
	
	// Check and process arguments
	private static void checkArguments(String[] args)
	{
		// Check for supplied args
		if(args.length != 6)
		{
			System.out.println("\nInvalid number of command line arguments passed\n"+
					"Please pass the arguments and run again\n");
			System.exit(0);
		}
		
		// Process args
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equals("-NUMBINS"))
			{
				String temp = args[i+1];
				temp = temp.replaceAll(",", "");
				NUMBINS = Integer.parseInt(temp.trim());
			}
			else if (args[i].equals("-DATASIZE"))
			{
				String temp = args[i+1];
				temp = temp.replaceAll(",", "");
				DATASIZE = Integer.parseInt(temp.trim());
			}
			else if (args[i].equals("-NUMTHREADS"))
			{
				String temp = args[i+1];
				temp = temp.replaceAll(",", "");
				NUMTHREADS = Integer.parseInt(temp.trim());
			}
		}
	}
	
	// Process the data using threads
	private static void buildHistogram(int range, dataContainer dc)
	{
		try
		{
			// Create a 'pool' for all the threads
			Thread[] pool = new Thread[NUMTHREADS];
			int start = 0;
			
			// Create specified number of threads and start them on a chunk of data
			for (int i = 0; i < NUMTHREADS; i++)
			{
				Thread t = new Thread(new Task(start, ((start + range) - 1), NUMBINS, dc));
				pool[i] = t;
				t.start();
				
				start = start + range;
			}
		
			// Wait for all the threads to finish
			for(int i = 0; i < pool.length; i++)
				pool[i].join();
		} catch (InterruptedException e) {}
	}
	
	// Print the histogram
	private static void printHistogram(dataContainer dc)
	{
		int[] hist = dc.getHist();		
		for (int i = 0; i < hist.length; i++)
		{
			int day = i + 1;
	        int val = hist[i]; 
	        System.out.print("Day " + day +
	        		": Number of times this day was the hottest: " + val);
	            
	        System.out.println();
		}
	}
		
	// Get the array of data. Length of 'datasize'
	private static int[] createData(int datasize)
	{
		int[] data = new int[datasize];
		Random rand = new Random(); 
		
		for (int i = 0; i < datasize; i++)
		{
			// Temperature can be between 0-100 degrees Fahrenheit
			int d = rand.nextInt(101);
			data[i] = d;
		}
		
		return data;
	}
}