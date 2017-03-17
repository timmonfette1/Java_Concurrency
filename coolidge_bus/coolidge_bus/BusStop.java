package coolidge_bus;

import java.util.concurrent.Semaphore;

// Bus stop for the students
// Acts as a container for shared data/semaphores
public class BusStop
{
	// Number of students waiting
	private int waiting = 0;
	
	// Name of the bus stop
	private String name;
	
	// Students waiting
	private final Semaphore busStopWaiting = new Semaphore(50);
	
	// Lock telling Student it's okay to board
	//(prevent race condition of students boarding at once)
	private final Semaphore studentBoard = new Semaphore(0);
	
	// Lock that tells the bus it can leave
	private final Semaphore busDepart = new Semaphore(0);
	
	// Semaphore acting as mutex lock for critical section
	private final Semaphore mlock = new Semaphore(1);
	
	public BusStop(String name)
	{
		this.name = name;
	}
	
	// Getters
	public int getWaiting()
	{
		return waiting;
	}
	public String getName()
	{
		return name;
	}
	public Semaphore getBusStopWaiting()
	{
		return busStopWaiting;
	}
	public Semaphore getStudentBoard()
	{
		return studentBoard;
	}
	public Semaphore getBusDepart()
	{
		return busDepart;
	}
	public Semaphore getLock()
	{
		return mlock;
	}
	
	// Student is waiting at bus stop
	public void newStudentWaiting()
	{
		waiting++;
	}
	
	// Student leaves bus stop to board bus
	public void studentOnboard()
	{
		waiting--;
	}
}
