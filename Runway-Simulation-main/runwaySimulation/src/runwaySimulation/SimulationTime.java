package runwaySimulation;

import java.util.Random;

/**
 * <b>Title:</b> SimulationTime.java<br>
 * <p>
 * <b>Description:</b><br>
 * A class with static methods for converting time between milliseconds and minutes or seconds.<br>
 * It also contains a method <i>timeTillNext</i> for calculating the time an event will occur based on a mean arrival time
 * 
 * @author Brandon Kenney
 */

public class SimulationTime {
	public static final int TAKEOFF_TIME = 4000;
	public static final int LANDING_TIME = 5000;
	public static String[] AIRLINES = {"AA","AI","AF","AZ","KL","BA","BW","DL","FL",
		 "BA","AC","ET","GH","LH","JM","KE","TW","UA"};	
	
	/**
	 * Calculates the time the next event will occur
	 * @param meanArrivalTime the mean average time between events
	 * @return the time the next event will occur
	 */
	public static int timeTillNext(int meanArrivalTime){
		Random random = new Random();
		double randomGaussian = random.nextGaussian();
		
		int waitTime = (int) (meanArrivalTime + randomGaussian  * meanArrivalTime);
		return Math.max(0, waitTime);
    	//int timeTillNext = (int)Math.round (-meanArrivalTime * Math.log (1 - randomDouble));
    	//System.out.println("Next plane in " + timeTillNext / 1000 + " mins.");
    	//return timeTillNext;
	}
	
	/**
	 * randomAirlineID - returns a randomly created ID for an Airline object
	 * @return randomly created ID for an Airline object as a String
	 */
	public static String randomAirplaneID() {
		Random random = new Random();
		int randomIndex = random.nextInt(18);
		int randomNum = random.nextInt(100);
		
		return "" + AIRLINES[randomIndex] + randomNum;
		
	}
}
