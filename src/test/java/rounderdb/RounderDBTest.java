package rounderdb;

import static org.junit.Assert.*;

import org.junit.Test;

import rounderdb.store.DataPoint;

public class RounderDBTest {

	@Test
	public void testCreateInstance() {
		RounderDB db = new RounderDB();
		
		assertTrue(db.getSyncInterval() == 0);
	}

	@Test
	public void testLoadInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStringDouble() {
		RounderDB db = new RounderDB();
		db.addArchive("cpuLoad", new Archive(1));
		db.add("cpuLoad", 1.2D);
		assertTrue(db.getArchive("cpuLoad").getBucket(0).lastAdded().getValue() == 1.2D);
		
	}

	@Test
	public void testAddStringDoubleLong() {
		RounderDB db = new RounderDB();
		db.addArchive("cpuLoad", new Archive(1));
		db.add("cpuLoad", 1.2D, 1234L);
		assertTrue(db.getArchive("cpuLoad").getBucket(0).lastAdded().getTimestamp() == 1234);
		assertTrue(db.getArchive("cpuLoad").getBucket(0).lastAdded().getValue() == 1.2);

	}

	@Test
	public void testAddStringInt() {
		RounderDB db = new RounderDB();
		db.addArchive("cpuLoad", new Archive(1));
		db.add("cpuLoad", 1);
		assertTrue(db.getArchive("cpuLoad").getBucket(0).lastAdded().getValue() == 1.0D);
	}

	@Test
	public void testGetNrArchives() {
		RounderDB db = new RounderDB();
		db.addArchive("cpuLoad", new Archive(1));
		db.addArchive("diskIO", new Archive(1));
		assertTrue(db.getNrArchives() == 2);
	}

	@Test
	public void testAddArchive() {
		RounderDB db = new RounderDB();
		db.addArchive("cpuLoad", new Archive(1));
		assertTrue(db.getNrArchives() == 1);
		db.addArchive("diskIO", new Archive(1));
		assertTrue(db.getNrArchives() == 2);
	}

	@Test
	public void testGetDataForArchive() {
		RounderDB db = new RounderDB();
		db.addArchive("cpuLoad", new Archive(1));
		db.add("cpuLoad", 1.2D, 1234L);
		db.add("cpuLoad", 1.3D, 1235L);
		db.add("cpuLoad", 1.4D, 1236L);
		assertTrue(db.getDataForArchive("cpuLoad", 0) instanceof DataPoint[]);
		assertTrue(db.getDataForArchive("cpuLoad", 0)[1].getValue() == 1.3D);
	}

}
