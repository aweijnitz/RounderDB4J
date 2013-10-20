package rounderdb;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArchiveTest {

	@Test
	public void testArchiveInt() {
		Archive a = new Archive(2);
		assertTrue(a.nrBuckets() == 2);
	}

	@Test
	public void testAddBucket() {
		Archive a = new Archive(2);
		a.addBucket(new DataBucket());
		assertTrue(a.nrBuckets() == 3);
	}

	@Test
	public void testAdd() {
		Archive a = new Archive();
		a.addBucket(new DataBucket(2));
		a.addBucket(new DataBucket(2));
		
        a.add(1D, 1234);
        a.add(2D, 1235);
        a.add(3D, 1235); // this will trigger the rollUp
        assertTrue(a.getBucket(1).lastAdded().getValue() == 1.5);
	}

}
