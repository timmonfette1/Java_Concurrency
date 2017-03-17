package santa_claus;

import java.util.concurrent.ThreadLocalRandom;

// Class for Elves
public class Elf implements Runnable
{
	private int idNumber;
	private ElfTasks et;
	private Santa santa;
	
	public Elf(int idNumber, ElfTasks et, Santa santa)
	{
		this.idNumber = idNumber;
		this.et = et;
		this.santa = santa;
	}
	
	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				// Wait 20 - 30 seconds before bothering Santa
				int time = ThreadLocalRandom.current().nextInt(20, 30 + 1);
				Thread.sleep(time * 1000);
				
				boolean busy = santa.isBusy();
				int elfCount = et.getCount();
				
				// If at least 3rd Elf, wake up Santa
				if (elfCount + 1 >= 3 && busy == false)
					santa.wakeUpSanta("elves");
				
				et.performRD(idNumber, busy);
			}
		}
		catch (InterruptedException ie) {}
	}
}
