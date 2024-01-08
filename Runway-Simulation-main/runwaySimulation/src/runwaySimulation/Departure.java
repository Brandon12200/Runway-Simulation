package runwaySimulation;

/**
 * <p>
 * Title: Departure.java
 * </p>
 * 
 * <p>
 * Description: Class to represent a queue of departing flights
 * in an airport runway simulation.
 * Creates new instances of Airplane objects, and adds them to the
 * ArrayQueue so that they can eventually depart. Used by Runway thread
 * to determine when should a plane take off.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class Departure extends Thread {
	
	private boolean running = true; // Used by main program to stop this thread
	private int time; // Takeoff duration (sleep time)
	private ArrayQueue<Airplane> departureQueue = new ArrayQueue<Airplane>(1000); // Queue of departing flights
	private final long STARTTIME = System.currentTimeMillis(); // time when thread begins
	
	/**
	 * Departure thread parameterized constructor
	 * @param time - Takeoff duration (sleep time) as an int
	 */
	public Departure(int time) {
		this.time = time;
	}
	
	/**
	 * getQueue - Getter method for ArrayQueue of departing flights
	 * @return ArrayQueue of departing flights
	 */
	public ArrayQueue<Airplane> getQueue(){
		return departureQueue;
	}
	
	/**
	 * getTime - getter for takeoff duration (sleep time)
	 * @return takeoff duration (sleep time) as an int
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * toString - returns current state of the Departure thread as a String
	 * @return state of the Departure thread as a String
	 */
	public String toString() {
		// Place info about time and size in a String
		String returnMe = "Departure duration: " + time + " mins\n"
				+ "Number of flights in departure Queue: " + departureQueue.getSize() + "\n";
		
		// If the queue is empty, there is no specific flight info to be added
		if(departureQueue.isEmpty())
			return returnMe;
		
		// Add label for specific flight info
		returnMe += "Flights in the Queue: \n";
		
		int queueSize = departureQueue.getSize();
		
		// Loop through queue
		for(int i = 0; i < queueSize; i++) {
			
			// Dequeue front flight
			Airplane currentFlight = departureQueue.dequeue();
			
			// Add its ID to the String
			returnMe += currentFlight.getID() + "\n";
			
			// Enqueue it back to the queue
			departureQueue.enqueue(currentFlight);
		}
		return returnMe;
	}

	/**
	 * run - Started by the start method, loop until main program calls the stopRunning method.
	 * Instantiates new Airplane object and adds it to the departureQueue. Sleeps until new Airplane object is to be instantiated
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
			departureQueue.enqueue(newFlight);
			
			// Output info about newly instantiated Airplane object and time until next Airplane object is to be instantiated
			System.out.println("Minute: " + ((currentTime - STARTTIME) / 1000) + " - Added flight " + newFlight.getID() + " to the departure Queue");
			System.out.println("Random wait time before next departure: " + (timeTillNext / 1000) + " mins");
			System.out.println(" ");
			
			try {
				// Sleep until next departing Airplane object is to be instantiated
				sleep(timeTillNext);
			} catch (InterruptedException e) {
			}
		}
	}
	
	/**
	 * stopRunning - changes running to false in order to stop the thread
	 */
	public void stopRunning(){ 
		running = false;
	} 
}