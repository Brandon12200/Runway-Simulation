package runwaySimulation;

/**
 * <p>
 * Title: QueueEmptyException.java
 * </p>
 * 
 * <p>
 * Description:
 * Child class of RuntimeException to represent exceptions
 * when a queue is empty.
 * </p>
 * 
 * @author Brandon Kenney
 */

public class QueueEmptyException extends RuntimeException {
	
	/**
	 * QueueEmptyException - default constructor
	 * Calls RuntimeExcpetion parameterized constructor with a default message
	 */
	public QueueEmptyException() {
		super("Queue Full Exception...");
	}
	
	/**
	 * QueueEmptyException - parameterized constructor
	 * Calls RuntimeExcpetion parameterized constructor with a custom passed in message
	 * @param msg - custom message as a String
	 */
	public QueueEmptyException(String msg) {
		super(msg);
	}

}