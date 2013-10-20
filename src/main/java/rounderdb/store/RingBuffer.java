package rounderdb.store;

import java.util.ArrayList;

public class RingBuffer {
	public static int DEFAULT_CAPACITY = 60;

	private ArrayList<DataPoint> data;
	private int head = -1; 	// Points to the index of the most recently added
							// element in buffer
	private DataPoint lastTrimmed = null;

	// this._tail = -1; // Points to the index of the oldest element in buffer

	private int capacity;

	public RingBuffer() {
		this(DEFAULT_CAPACITY);
	}

	public RingBuffer(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException(
					"Cannot create zero or negative sized buffer: " + capacity);
		this.capacity = capacity;
		this.data = new ArrayList<DataPoint>(capacity);
	}

	public DataPoint[] toArray() {
		DataPoint[] dpArr = new DataPoint[data.size()]; // New to create this to preserve type info
		
		return data.toArray(dpArr);
	}

	public int getCurrentSize() {
		return this.data.size();
	}

	public int getBufferCapacity() {
		return this.capacity;
	}

	/**
	 * Get element at index, relative to tail (i = 0 -> tail(), i = 1 ->
	 * (tailIndex+1)()
	 * 
	 * @param i
	 * @return
	 */
	public DataPoint getElementAt(int i) {
		if (i > this.getBufferCapacity())
			i %= this.getBufferCapacity();
		if (i < this.getCurrentSize())
			return (DataPoint) this.data.get(i);
		else if (i >= this.getBufferCapacity())
			return (DataPoint) this.data.get(i % this.getBufferCapacity());

		return null;
	}

	/**
	 * Set element indexed relative to tail position (tail+i)
	 * 
	 * @param index
	 * @param element
	 */
	public void setElementAt(int index, DataPoint element) {
		if (index < this.getCurrentSize())
			this.data.set(index, element);
		else if (index >= this.getBufferCapacity())
			this.data.set(index % this.getBufferCapacity(), element);
	}

	/**
	 * As the ringbuffer reaches capacity and starts wrapping around, the oldest
	 * element is trimmed off when a new elements are added. This keeps the ring
	 * to a fixed size.
	 * 
	 * This method returns the most recently trimmed element, or null, of the
	 * ring is still under capacity (filling up)
	 * 
	 * @returns element
	 */
	public DataPoint getLastTrimmedElement() {
		return this.lastTrimmed;
	}

	/**
	 * Add element and trim ring to size (throwing out the oldest element as
	 * necessary)
	 * 
	 * @param element
	 * @return current size of buffer
	 */
	public int push(DataPoint element) {
		this.data.add(element);

		if (this.data.size() > this.getBufferCapacity()) // Need to trim
			this.lastTrimmed = this.data.remove(0);

		this.head = this.getCurrentSize() - 1;

		return this.getCurrentSize();
	}

	public DataPoint head() {
		if (this.getCurrentSize() <= 0)
			return null;
		return this.data.get(this.head);
	}

	public DataPoint tail() {
		if (this.getCurrentSize() <= 0)
			return null;
		return this.data.get(0);
	}

}
