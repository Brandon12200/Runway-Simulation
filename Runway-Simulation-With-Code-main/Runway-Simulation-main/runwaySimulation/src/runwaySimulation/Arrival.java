package runwaySimulation;

/**
 * <p>
 * Title: Arrival.java
 * </p>
 * 
 * <p>
 * Description: Class to represent a arrival queue used in runway simulation.
 * Queue is populated by Airplane objects waiting to use the runway to arrive
 * at the airport.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class Arrival extends Thread {
	
	private boolean running = true; // Used by main program to stop this thread
	private ArrayQueue<Airplane> arrivalQueue = new ArrayQueue<Airplane>(1000); // Arrival ArrayQueue
	private int time; // Landing duration (sleep time)
	private final long STARTTIME = System.currentTimeMillis(); // time when thread begins in milliseconds
	
	/**
	 * Arrival thread parameterized constructor
	 * @param time - landing duration as an int
	 */
	public Arrival(int time) {
		this.time = time;
	}
	
	/**
	 * getter method for queue of flights waiting to arrive
	 * @return ArrayQueue arrivalQueue
	 */
	public ArrayQueue<Airplane> getQueue(){
		return arrivalQueue;
	}
	
	/**
	 * getter method for landing duration time
	 * @return int time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * run - Started by the start method, loop until main program calls the stopRunning method.
	 * Instantiates new Airplane object and adds it to the arrialQueue. Sleeps until new Airplane object is to be instantiated
	 */
	public void run() {
		
		// So long as stopRunning has not been called
		while(running) {
			
			// Time between the instantiation of this Airplane object and next Airplane object
			int timeTillNext = SimulationTime.timeTillNext(15000);
			long currentTime = System.currentTimeMillis(); // Current time in milliseconds
			
			// New flight instantiated at the difference between right now and the start of the program in seconds
			Airplane newFlight = new Airplane(SimulationTime.randomAirplaneID(), ((currentTime - STARTTIME) / 1000));
			// Add new flight to the queue
			arrivalQueue.enqueue(newFlight);
			
			// Output info about newly instantiated Airplane object and time until next Airplane object is to be instantiated
			System.out.println("Minute: " + ((currentTime - STARTTIME) / 1000) + " - Added flight " + newFlight.getID() + " to the arrival Queue");
			System.out.println("Random wait time before next arrival: " + (timeTillNext / 1000) + " mins");
			System.out.println(" ");
			
			try {
				// Sleep until next arriving Airplane object is to be instantiated
				sleep(timeTillNext);
			} catch (InterruptedException e) {
			}
		}
	}
	
	/**
	 * stopRunning - sets running to false in order to stop the thread
	 */
	public void stopRunning() { 
		running = false;
	} 
	
	/**
	 * toString - returns current state of the Arrival as a String
	 * @return current state of the Arrival as a String
	 */
	public String toString() {
		// Place info about time and size in a String
		String returnMe = "Arrival duration: " + time + " mins\n"
				+ "Number of flights in arrival Queue: " + arrivalQueue.getSize() + "\n";
		
		// If the queue is empty, there is no specific flight info to be added
		if(arrivalQueue.isEmpty())
			return returnMe;
		
		// Add label for specific flight info
		returnMe += "Flights in the Queue: \n";
		
		int queueSize = arrivalQueue.getSize();
		
		// Loop through queue
		for(int i = 0; i < queueSize; i++) {
			
			// Dequeue front flight
			Airplane currentFlight = arrivalQueue.dequeue();
			
			// Add its ID to the String
			returnMe += currentFlight.getID() + "\n";
			
			// Enqueue it back to the queue
			arrivalQueue.enqueue(currentFlight);
		}
		return returnMe;
	}
}