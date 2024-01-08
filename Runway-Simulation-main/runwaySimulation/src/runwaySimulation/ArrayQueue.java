package runwaySimulation;

/**
 * <p>
 * Title: ArrayQueue.java
 * </p>
 * 
 * <p>
 * Description:
 * Class which creates an array based Queue data type.
 * Includes constructors, enqueue, dequeue, isEmpty, and isFull methods.
 * Also includes methods such as front, rear, getSize, toString, and makeEmpty
 * </p>
 * 
 * @author Brandon Kenney
 */

public class ArrayQueue<T> implements Queue<T> {
	
	private T[] data; // Array of T objects to store ArrayQueue contents
	private int size, front; // Amount of objects in an ArrayQueue and index of the front element
	static final int CAPACITY = 100; // Default capacity of an ArrayQueue
	
	/**
	 * ArrayQueue - default constructor
	 * Instantiates an ArrayQueue capable of holding 100 T objects
	 */
	public ArrayQueue() {
		data = (T[])new Object[CAPACITY];
		size = 0;
	}
	
	/**
	 * ArrayQueue - parameterized constructor
	 * Instantiates an ArrayQueue capable of holding an amount of T objects passed in by the user
	 * @param capacity - user defined capacity of ArrauQueue
	 */
	public ArrayQueue(int capacity) {
		data = (T[])new Object[capacity];
		size = 0;
	}

	@Override
	/**
	 * enqueue - adds element to an ArrayQueue
	 * @param d - data to be added
	 * @throws QueueException when adding to an already full queue
	 */
	public synchronized void enqueue(T d) throws QueueFullException {
		if(this.isFull())
			throw new QueueFullException("Queue full exception...");
		
		data[(front + size) % data.length] = d;
		size++;
		
	}

	@Override
	/**
	 * dequeue - removes front element from the ArrayQueue and updates front index
	 * @return removed element
	 * @throws QueueException when trying to dequeue from an empty ArrayQueue
	 */
	public synchronized T dequeue() throws QueueEmptyException {
		if(this.isEmpty())
			throw new QueueEmptyException("Queue empty exception...");
		size--;
		T item = data[front];
		front = (front + 1) % data.length;
		return item;
	}

	@Override
	/**
	 * front - returns front element without removing it
	 * @return front element
	 * @throws QueueException when attempting to return front from an empty ArrayQueue
	 */
	public synchronized T front() throws QueueEmptyException {
		if(this.isEmpty())
			throw new QueueEmptyException("Queue empty exception...");
		return data[front];
	}

	@Override
	/**
	 * rear - returns rear element without removing it
	 * @return rear element
	 * @throws QueueException when attempting to return rear from an empty ArrayQueue
	 */
	public synchronized T rear() throws QueueEmptyException {
		if(this.isEmpty())
			throw new QueueEmptyException("Queue empty exception...");
		return data[(front + size - 1) % data.length];
	}

	@Override
	/**
	 * isEmpty - returns whether an ArrayQueue is empty or not
	 * @return true if an ArrayQueue is empty. false otherwise
	 */
	public synchronized boolean isEmpty() {
		return size == 0;
	}

	@Override
	/**
	 * isFull - returns whether an ArrayQueue is full or not
	 * @return true if an ArrayQueue is full. false otherwise
	 */
	public synchronized boolean isFull() {
		return size == data.length;
	}

	@Override
	/**
	 * getSize - returns amount of elements in the ArrayQueue
	 * @return amount of elements in the ArrayQueue
	 */
	public synchronized int getSize() {
		return size;
	}
	
	/**
	 * toSting - returns state of an ArrayQueue as a String
	 * @return state of an ArrayQueue
	 */
	public synchronized String toString() {
		if(this.isEmpty()) {
			return "Queue is empty! Maximum number of items that can be stored is " + data.length;
		}
		
		// String to be returned
		String message = "The number of items in the queue is: " + size + 
						 "\nThe queue contains the following: \n";
		// Iterate through data[] and append ArrayQueue contents to message
		for(int i = 0; i < size; i++)
			message += data[(front + i) % data.length] + " ";
		
		return message;
	}
	
	/**
	 * makeEmpty - removed all contents of an ArrayQueue
	 */
	public synchronized void makeEmpty() {
		// Iterate through elements in ArrayQueue and put null in the location
		for(int i = 0; i < size; i++)
			data[(front + i) % data.length] = null;
		// Set size to 0
		size = 0;
	}

}