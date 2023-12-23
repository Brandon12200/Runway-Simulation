package runwaySimulation;

/**
 * <p>
 * Title: Main.java
 * </p>
 * 
 * <p>
 * Description: Application class to test the Departure, Arrival, and Runway thread classes.
 * Calls for the threads to run for a specified number of minutes and stops them once the
 * specified amount of time elapses.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class Main {
	
	Arrival arrivals = new Arrival(5);
	Departure departures = new Departure(4);
	Runway runway = new Runway(arrivals, departures);
	long startTime;
	long simulationTime;
	
	/**
	 * startSimulation - starts all threads needed for the simulation and ands them when simulation time has elapsed
	 * @param time of the entire simulation in minutes
	 */
	public void startSimulation(long time) {
		
		startTime = System.currentTimeMillis(); // Current time in milliseconds
		simulationTime = time * 60000; // Time for simulations converted from minutes to milliseconds
		
		// Start the three threads
		arrivals.start();
		departures.start();
		runway.start();
		
		// Loop until inputed time for the simulation has elapsed
		while(System.currentTimeMillis() < startTime + simulationTime){
		}
		
		// After elapsed time, stop each thread
		arrivals.stopRunning();
		departures.stopRunning();
		runway.stopRunning();

		// After elapsed time, interrupt each thread
		arrivals.interrupt();
		departures.interrupt();
		runway.interrupt();
		
		// Wait for each thread to die
		try {
			arrivals.join();
			departures.join();
			runway.join();
		} catch (InterruptedException e) { 
		} 
	}
	
	/**
	 * main - sets the time in minutes for how long the program will run, calls startSimulation,
	 * and stops runway thread at the end of the simulation
	 * @param args
	 */
	public static void main(String[] args) { 
		
		int time = 1; // Time, in minutes, for the simulation
		
		// Create instance of Program3 and call startSimulation
		Main myProgram = new Main();
		myProgram.startSimulation(time);
		
		// Loop while arrivals and departures are alive
		while(myProgram.arrivals.isAlive()) {
		}
		
		while(myProgram.departures.isAlive()) {
		}
		
		// Kill the runway thread
		myProgram.runway.stopRunning();
		
		// Output final states of the threads
		System.out.println(myProgram.arrivals.toString());
		System.out.println(" ");
		System.out.println(myProgram.departures.toString());
		System.out.println(" ");
		System.out.println(myProgram.runway.toString());
	} 

}
