package santa_claus;

// Class for Santa
public class Santa implements Runnable
{
	// Shared tasks by elves and reindeer
	private ReindeerTasks rt;
	private ElfTasks et;
	
	private boolean sleeping = true;
	private boolean busy = false;
	private String wokeUp = "";		// who woke up santa
	
	public Santa(ElfTasks et, ReindeerTasks rt)
	{
		this.et = et;
		this.rt = rt;
	}
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				System.out.println("Santa is going to sleep");
				santaSleep();
				sleeping = true;
				wokeUp = "";
				busy = false;
			}
		}
		catch (InterruptedException ie) {}
	}
	
	// Santa goes to sleep
	private synchronized void santaSleep() throws InterruptedException
	{
		if (sleeping == true)
		{
			wait();
		}
		
		if (wokeUp.equals("elves"))
		{
			// R&D with Elves
			System.out.println("Santa is letting in his Elves");
			et.alert();
			Thread.sleep(5000);
			System.out.println("Santa is discussing R&D with his Elves");
			Thread.sleep(5000);
			System.out.println("Santa is sending his Elves out since the discussion is over");
		}
		else if (wokeUp.equals("reindeer"))
		{
			// Reindeer harness and deliver presents
			System.out.println("Santa is harnessing his reindeer to deliver presents");
			rt.alert();
			Thread.sleep(5000);
			System.out.println("Presents being delivered");
			Thread.sleep(5000);
			System.out.println("Santa is unharnessing his reindeer");
		}
		else
		{
			System.out.println("Santa woke up for no reason, must have been a bad dream\n"+
					"Going back to sleep now...");
		}
	}
	
	// Santa is woken up
	public synchronized void wakeUpSanta(String whoami)
	{
		try
		{
			System.out.println("Santa is waking up");
			Thread.sleep(5000);	// Santa is groggy, needs to wake up
			sleeping = false;
			busy = true;
			wokeUp = whoami;
			notify();
		}
		catch (InterruptedException ie) {}
	}
	
	// Getter
	public synchronized boolean isBusy()
	{
		return busy;
	}
}
