package runwaySimulation;

/**
 * <p>
 * Title: QueueFullException.java
 * </p>
 * 
 * <p>
 * Description:
 * Child class of RuntimeException to represent exceptions
 * when a queue is full.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class QueueFullException extends RuntimeException {
	
	/**
	 * QueueFullException - default constructor
	 * Calls RuntimeExcpetion parameterized constructor with a default message
	 */
	public QueueFullException() {
		super("Queue Full Exception...");
	}
	
	/**
	 * QueueFullException - parameterized constructor
	 * Calls RuntimeExcpetion parameterized constructor with a custom passed in message
	 * @param msg - custom message as a String
	 */
	public QueueFullException(String msg) {
		super(msg);
	}
}
