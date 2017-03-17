package cs361_1_par;

public class Task implements Runnable
{
	private int start;
	private int end;
	private int numbins;
	private int[] hist;
	private dataContainer dc;
	
	// Constructor
	public Task(int start, int end, int numbins, dataContainer dc)
	{
		this.start = start;
		this.end = end;
		this.numbins = numbins;
		this.dc = dc;
		initializeHist(numbins);
	}
	
	// Initialize all bins to zero
	private void initializeHist(int numbins)
	{
		this.hist = new int[numbins];
		for(int i = 0; i < numbins; i++)
			hist[i] = 0;
	}
	
	@Override
	public void run()
	{
		// Process a chunk of data into local histogram
		for (int i = start; i < end; i = i + numbins)
		{
			int bin = dc.processData(i, (i + numbins) - 1);
			hist[bin-1] = hist[bin-1] + 1;
		}
		
		dc.combineHist(hist);
	}
}
