package cs361_1_par;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Class for holding and processing all data
public class dataContainer
{
	private int[] data;
	private int[] hist;
	final Lock lock = new ReentrantLock();
	
	public dataContainer(int[] data, int bins)
	{
		this.data = data;
		initializeHist(bins);
	}
	
	// Initialize all bins to zero
	private void initializeHist(int numbins)
	{
		this.hist = new int[numbins];
		for(int i = 0; i < numbins; i++)
			hist[i] = 0;
	}
	
	//Combine a histogram from a thread into main one
	public void combineHist(int[] h)
	{
		lock.lock();
		for (int i = 0; i < h.length; i++)
			hist[i] = hist[i] + h[i];
		lock.unlock();
	}
	
	// Process the data based on a range
	// Add hottest element to it's bin number
	public int processData(int start, int end)
	{
		int hottest = data[start];
		int counter = 1;
		int bin = 1;
		
		for (int i = start+1; i <= end; i++)
		{
			counter++;
			if (data[i] > hottest)
			{
				hottest = data[i];
				bin = counter;
			}
		}

		return bin;
	}
	
	// Get the main histogram
	public int[] getHist()
	{
		return hist;
	}
}
