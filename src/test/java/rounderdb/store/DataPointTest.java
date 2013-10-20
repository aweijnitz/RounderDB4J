package rounderdb.store;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * More or less meaningless test of simple data holding object without business
 * logic.
 */
public class DataPointTest {

	@Test
	public void testDataPoint() {
		DataPoint p = new DataPoint();
		assertTrue(p instanceof DataPoint);
	}

	@Test
	public void testDataPointDoubleLong() {
		DataPoint p = new DataPoint(1.2, 1234);
		assertTrue(p.getValue() == 1.2);
		assertTrue(p.getTimestamp() == 1234);
	}

	@Test
	public void testGetValue() {
		DataPoint p = new DataPoint(1.2, 1234);
		assertTrue(1.2 == p.getValue());
	}

	@Test
	public void testSetValueDouble() {
		DataPoint p = new DataPoint();
		p.setValue(1.2);
		assertTrue(1.2 == p.getValue());
	}

	@Test
	public void testSetValueDouble1() {
		DataPoint p = new DataPoint();
		p.setValue(new Double(1.2));
		assertTrue(1.2 == p.getValue());
	}

	@Test
	public void testSetValueInt() {
		DataPoint p = new DataPoint();
		p.setValue(12);
		assertTrue(12.0 == p.getValue());
	}

	@Test
	public void testGetTimestamp() {
		DataPoint p = new DataPoint(1.2, 1234);
		assertEquals(1234, p.getTimestamp());
	}

	@Test
	public void testSetTimestamp() {
		DataPoint p = new DataPoint();
		p.setTimestamp(1234);
		assertEquals(1234, p.getTimestamp());
	}

}
