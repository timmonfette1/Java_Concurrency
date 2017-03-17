package santa_claus;

// Main class
public class NorthPole
{
	public static void main(String args[])
	{
		try {
			ReindeerTasks rt = new ReindeerTasks();
			ElfTasks et = new ElfTasks();
			
			// Create Santa thread
			Santa santa = new Santa(et, rt);
			Thread s = new Thread(santa);
			s.start();

			// Create Reindeer threads
			for (int i = 1; i < 10; i++)
			{
				Thread r = new Thread(new Reindeer(i, rt, santa));
				r.start();
			}

			// Create Elf threads
			for (int i = 1; i < 11; i++)
			{
				Thread e = new Thread(new Elf(i, et, santa));
				e.start();
			}

			s.join();
		} catch (InterruptedException e) {}
	}
}
