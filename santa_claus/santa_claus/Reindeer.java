package santa_claus;

import java.util.concurrent.ThreadLocalRandom;

// Class for Reindeer
public class Reindeer implements Runnable
{
	private int idNumber;
	private ReindeerTasks rt;
	private Santa santa;
	
	public Reindeer(int idNumber, ReindeerTasks rt, Santa santa)
	{
		this.idNumber = idNumber;
		this.rt = rt;
		this.santa = santa;
	}
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				// Wait 10 - 15 seconds before bothering Santa
				int time = ThreadLocalRandom.current().nextInt(10, 15 + 1);
				Thread.sleep(time * 1000);
				
				boolean busy = santa.isBusy();
				int rCount = rt.getCount();
				
				// If 9th Reindeer is this thread, wake up Santa
				if (rCount + 1 == 9 && busy == false)
					santa.wakeUpSanta("reindeer");
				
				rt.deliverPresents(idNumber, busy);
			}
		}
		catch (InterruptedException ie) {}
	}
}
