package cs361_1_seq;

// Class for holding and processing all data
public class dataContainer
{
	private int[] data;
	private int[] hist;
	
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
	
	// Process the data based on a range
	// Add hottest element to it's bin number
	public void processData(int start, int end)
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
		
		hist[bin-1] = hist[bin-1] + 1;
	}
	
	// Get the main histogram
	public int[] getHist()
	{
		return hist;
	}
}
