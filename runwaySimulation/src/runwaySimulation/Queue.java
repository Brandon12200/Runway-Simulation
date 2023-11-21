package runwaySimulation;

/**
 * <p>
 * Title: Queue.java
 * </p>
 * 
 * <p>
 * Description:
 * Interface which outlines a Queue data type.
 * Calls for a enqueue, dequeue, front, rear, isEmpty,
 * isFull, and getSize methods in all implementations
 * of the class
 * </p>
 * 
 * @author Brandon Kenney
 */

public interface Queue<T> {
	void enqueue(T d) throws QueueFullException; 
	T dequeue() throws QueueEmptyException;
	T front() throws QueueEmptyException;
	T rear() throws QueueEmptyException;
	boolean isEmpty();
	boolean isFull();
	int getSize();
}
