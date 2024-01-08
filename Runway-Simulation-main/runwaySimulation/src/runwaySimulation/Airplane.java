package runwaySimulation;

import java.util.Random;

/**
 * <p>
 * Title: Airplane.java
 * </p>
 * 
 * <p>
 * Description: Class to represent a flight used in runway simulation.
 * Each Airplane has a flightID, time entered, and time exited.
 * Used by Arrival and Departure threads to create Airplane objects to
 * arrive and depart on the runway.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class Airplane {
	
	private String flightID; // String identifier for the flight
	private long entered; // Time the flight entered the queue
	private long exited; // Time the flight exited the queue
	private long waitTime; // Time flight waited in queue
	private int fuelAmount; // Randomized amount of fuel arriving planes have
	
	/**
	 * Airplane - Airplane parameterized constructor
	 * @param flightID - String representation of the flight
	 * @param entered - long time when flight entered the queue
	 */
	public Airplane(String flightID, long entered) {
		this.flightID = flightID;
		this.entered = entered;
	}
	
	/**
	 * getID - getter for ID of a flight
	 * @return String representation of a flight
	 */
	public String getID() {
		return flightID;
	}
	
	/**
	 * getEntered - getter for time when flight entered the queue
	 * @return - time when flight entered the queue as a long
	 */
	public long getEntered() {
		return entered;
	}
	
	/**
	 * getWaitTime - returns amount of time flight waited in queue as a long
	 * @return time flight waited in queue as a long
	 */
	public long getWaitTime() {
		return waitTime;
	}
	/**
	 * setEntered - setter for time when flight entered the queue
	 * @param entered - new time when flight entered the queue
	 */
	public void setEntered(long entered) {
		this.entered = entered;
	}
	
	/**
	 * setExited - setter for time when flight exited the queue
	 * @param exited - new time when flight exited the queue
	 */
	public void setExited(long exited) {
		this.exited = exited;
		waitTime = exited - entered;
	}
	
	/**
	 * setFuelAmount - randomly assign the Airplane object an amount of fuel between 0 and 99
	 * 0 - 99 represents percentages of their fuel tank filled
	 * 0 is an empty tank and 99 is as close to full as possible. 
	 * If a plane is arriving at an airport, it cannot have a full tank since it is flying
	 */
	public void setFuelAmount() {
		// Create instance of Random
		Random rand = new Random();
		
		// Set fuel amount between 0 and 99 (0 being an empty tank)
		fuelAmount = rand.nextInt(99);
	}
	
	/**
	 * getFuelAmount - getter for amount of fuel an Airplane object has
	 * @return amount of fuel an Airplane object has as an int
	 */
	public int getFuelAmount() {
		return fuelAmount;
	}
	
	/**
	 * toString - returns current state of an Airplane object as a String
	 * @return String state of Airplane object
	 */
	public String toString() {
		return "Flight: " + flightID + " used the runway at min " + (exited - entered) + " and waited for " + waitTime + " min";
	}

}
