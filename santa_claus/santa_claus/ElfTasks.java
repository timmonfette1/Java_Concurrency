package santa_claus;

// Class for shared tasks between elves
public class ElfTasks
{
	private int elfCount = 0;	// Elves bothering Santa
	
	// Perform R&D
	public synchronized void performRD(int id, boolean busy) throws InterruptedException
	{
		// If not enough Elves or Santa is busy, wait
		if (elfCount < 3 || busy == true)
		{
			elfCount++;
			wait();
		}
		
		System.out.printf("Elf %d is entering Santa's Workshop for R&D discussion\n", id);
		elfCount--;
	}
	
	// Alert all waiting Elves its time for R&D
	public synchronized void alert()
	{
		notifyAll();
	}
	
	// Getter for number of Elves waiting on Santa
	public int getCount()
	{
		return elfCount;
	}
}
