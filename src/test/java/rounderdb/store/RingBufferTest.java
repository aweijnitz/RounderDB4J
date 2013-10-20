package rounderdb.store;


import static org.junit.Assert.*;
import org.junit.Test;

public class RingBufferTest {

	@Test
	public void testRingBuffer() {
		RingBuffer rb = new RingBuffer();
		assertEquals(RingBuffer.DEFAULT_CAPACITY, rb.getBufferCapacity());
	}

	@Test
	public void testRingBufferInt() {
		RingBuffer rb = new RingBuffer(10);
		assertEquals(10, rb.getBufferCapacity());
	}

	@Test
	public void testToArray() {
		RingBuffer rb = new RingBuffer(5);
		assertTrue(rb.toArray() instanceof Object[]);		
	}

	@Test
	public void testGetCurrentSize() {
		RingBuffer rb = new RingBuffer(10);
		rb.push(new DataPoint(0, 0));
		assertEquals(1, rb.getCurrentSize());
	}

	@Test
	public void testGetBufferCapacity() {
		RingBuffer rb = new RingBuffer(10);
		rb.push(new DataPoint(0, 0));
		assertEquals(10, rb.getBufferCapacity());
	}

	@Test
	public void testGetElementAt() {
		RingBuffer rb = new RingBuffer(10);
		rb.push(new DataPoint(0, 10));
		assertEquals(10, rb.getElementAt(0).getTimestamp());
		assertTrue(0.0 == rb.getElementAt(0).getValue());
	}

	@Test
	public void testSetElementAt() {
		RingBuffer rb = new RingBuffer(10);
		rb.push(new DataPoint(0, 10));
		rb.push(new DataPoint(1, 11));
		rb.push(new DataPoint(2, 12));
		rb.setElementAt(1, new DataPoint(99, 1199));
		assertEquals(1199, rb.getElementAt(1).getTimestamp());
		assertTrue(99.0 == rb.getElementAt(1).getValue());
	}

	@Test
	public void testGetLastTrimmedElement() {
		RingBuffer rb = new RingBuffer(2);
		rb.push(new DataPoint(0, 10));
		rb.push(new DataPoint(1, 11));
		rb.push(new DataPoint(2, 12));
		assertEquals(10, rb.getLastTrimmedElement().getTimestamp());
		assertTrue(0.0 == rb.getLastTrimmedElement().getValue());
	}

	@Test
	public void testPush() {
		RingBuffer rb = new RingBuffer(5);
		rb.push(new DataPoint(0, 10));
		rb.push(new DataPoint(1, 11));
		rb.push(new DataPoint(2, 12));
		assertEquals(12, rb.head().getTimestamp());
		assertTrue(2.0 == rb.head().getValue());
	}

	@Test
	public void testHead() {
		RingBuffer rb = new RingBuffer(5);
		rb.push(new DataPoint(0, 10));
		rb.push(new DataPoint(1, 11));
		rb.push(new DataPoint(2, 12));
		assertTrue(12 == rb.head().getTimestamp());
		assertTrue(2.0 == rb.head().getValue());
	}

	@Test
	public void testTail() {
		RingBuffer rb = new RingBuffer(5);
		rb.push(new DataPoint(0, 10));
		rb.push(new DataPoint(1, 11));
		rb.push(new DataPoint(2, 12));
		assertTrue(10 == rb.tail().getTimestamp());
		assertTrue(0.0 == rb.tail().getValue());
	}

}
