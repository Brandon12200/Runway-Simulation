package runwaySimulation;

/**
 * <p>
 * Title: Runway.java
 * </p>
 * 
 * <p>
 * Description: Class to represent a runway at an airport.
 * Processes requests from Arrival and Depart threads for flights
 * to use the runway.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class Runway extends Thread { 
	
	private Arrival arrivals; // A reference to the arrival thread
	private Departure departures; // A reference to the departure thread
	private boolean running = true; // Used by main program to stop this thread
	private final long STARTTIME = System.currentTimeMillis(); // time when thread begins in milliseconds
	private int arrivalCount = 0; // Count of how many planes arrived
	private int departureCount = 0; // Count of how many planes departed
	
	/**
	 * Runway - Parameterized constructor for the Runway thread
	 * @param arrivals - A reference to the arrival thread
	 * @param departures - // A reference to the departure thread
	 */
	public Runway(Arrival arrivals, Departure departures) {
		this.arrivals = arrivals;
		this.departures = departures;
	}

	/**
	 * run - Started by the start method, loop until main program calls the stopRunning method.
	 * While giving priority to arriving flights, allows flights to land or depart
	 */
	public void run() { 
		
		// So long as stopRunning has not been called
		while(running) {
			
			// While there are flights waiting to arrive
			while(arrivals.getQueue().getSize() > 0) {
				
				// Next arriving flight
				Airplane currentFlight = arrivals.getQueue().dequeue();
				
				// Set fuel amount of an Airplane to a percentage between 0 and 99
				currentFlight.setFuelAmount();
				
				long currentTime = System.currentTimeMillis(); // current time in milliseconds
				
				// Update exited of the flight to current minute since the simulation started
				currentFlight.setExited((currentTime - STARTTIME) / 1000);
				
				// Arriving Airplanes go through 1 percent of their tank per minute they are waiting
				// If their percentage of fuel minus every minute it waited is less than or equal to 0, the plane ran out of fuel 
				if(currentFlight.getFuelAmount() - currentFlight.getWaitTime() <= 0) {
					System.out.println("Minute: " + ((currentTime - STARTTIME) / 1000) + " - Flight " + currentFlight.getID() + " has ran out of fuel");
					// Move on to next Airplane object
					continue;
				}
				
				// Output confirmation that the flight landed along with how long it waited
				System.out.println("Minute: " + ((currentTime - STARTTIME) / 1000) + " - Flight " + currentFlight.getID() + " cleared for landing - Entered Queue at " 
				+ currentFlight.getEntered() + " - waited " + currentFlight.getWaitTime() + " mins");
				System.out.println("");
				arrivalCount++;
				
				try {
					// Sleep to simulated actual time it took the Airplane object to land
					sleep(arrivals.getTime() * 1000);
				} catch (InterruptedException e) {
				}
			}
			
			// While there are flights waiting to depart
			while(departures.getQueue().getSize() > 0) {
				
				// Next departing flight
				Airplane currentFlight = departures.getQueue().dequeue();
				
				long currentTime = System.currentTimeMillis(); // current time in milliseconds
				
				// Departing planes do not to be assigned fuel because, theoretically, they will not run out of fuel before taking off
				
				// Update exited of the flight to current minute since the simulation started
				currentFlight.setExited((currentTime - STARTTIME) / 1000);
				
				// Output confirmation that the flight departed along with how long it waited
				System.out.println("Minute: " + ((currentTime - STARTTIME) / 1000) + " - Flight " + currentFlight.getID() + " cleared for takeoff - Entered Queue at " 
				+ currentFlight.getEntered() + " - waited " + currentFlight.getWaitTime() + " mins");
				System.out.println("");
				departureCount++;
				
				try {
					// Sleep to simulated actual time it took the Airplane object to depart
					sleep(departures.getTime() * 1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	/**
	 * stopRunning - changes running to false in order to stop the thread
	 */
	public void stopRunning() { 
		running = false;
	}
	
	/**
	 * toString - returns current state of the Runway thread as a String
	 * @return state of the Runway thread as a String
	 */
	public String toString() {
		
		// Return values of arrivalCount and departureCount
		return "Runway state: \nNumber of planes that arrived on the runway: " + arrivalCount +
				"\nNumber of planes that departed on the runway: " + departureCount;
	}
}