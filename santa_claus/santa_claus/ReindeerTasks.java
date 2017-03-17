package santa_claus;

// Class for shared tasks between reindeer
public class ReindeerTasks
{
	private int rCount = 0;		// Reindeer bothering Santa
	
	// Get harnessed and deliver presents
	public synchronized void deliverPresents(int id, boolean busy) throws InterruptedException
	{
		// If not all Reindeer or Santa is busy
		if (rCount < 9 || busy == true)
		{
			rCount++;
			wait();
		}
		
		System.out.printf("Reindeer number %d harnessed\n", id);
		rCount--;
	}
	
	// Alert waiting Reindeer its time to deliver presents
	public synchronized void alert()
	{
		notifyAll();
	}
	
	// Getter for number of Reindeer waiting on Santa
	public int getCount()
	{
		return rCount;
	}
}
