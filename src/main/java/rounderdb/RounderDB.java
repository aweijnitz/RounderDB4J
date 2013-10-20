package rounderdb;

import java.util.Hashtable;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

import rounderdb.persistence.DBLoader;
import rounderdb.store.DataPoint;


public class RounderDB {
	
	private long syncInterval = 0;
	private boolean syncActive = false;
	private Hashtable<String, Archive> archives;
	
	private Log log = LogFactory.getLog(RounderDB.class);
	
	public RounderDB() {
		this.syncActive = false;
		this.syncInterval = 0;
		this.setArchives(new Hashtable<String, Archive>());
	}
	
	protected RounderDB(Hashtable<String, Archive> archives) {
		this();
		this.archives = archives;
	}
	
	protected RounderDB(Hashtable<String, Archive> archives, long syncInterval, boolean syncActive) {
		this.syncInterval = syncInterval;
		this.syncActive = syncActive;
		this.archives = archives;
	}
	
	public static RounderDB createInstance() {
		LogFactory.getLog(RounderDB.class).info("Creating new instance");
		
		return null;
	}

	public static RounderDB loadInstance(DBLoader loader) {
		LogFactory.getLog(RounderDB.class).info("Loading instance");

		return null;
	}
	
	
	public static RounderDB saveInstance(RounderDB instance) {
		LogFactory.getLog(RounderDB.class).info("Saving instance");

		return null;
	}
	

	public void add(String archiveName, double value) {
		this.add(archiveName, value, System.currentTimeMillis());
	}

	
	public void add(String archiveName, double value, long timestamp) {
		log.debug("ADDING DATA> archive: "+archiveName+" val: "+value+" time: "+timestamp);	
		archives.get(archiveName).add(value, timestamp);
	}

	public void add(String archivename, int value) {
		this.add(archivename, value*1.0, System.currentTimeMillis());
	}
	

	
	public void reset() {
		log.warn("Resetting instance. All data will be deleted, also on disk.");
		
	}
	
	public int getNrArchives() {
		return archives.keySet().size();
	}
	
	public void setSyncInterval(int seconds) {
		log.debug("Setting sync interval: "+seconds);	
		syncInterval = seconds*1000;
		
	}
	
	public long getSyncInterval() {
		return syncInterval;
	}
	
	/**
	 * Activate periodic saving of the database to persistent storage
	 * @param s true or false
	 */
	public void setSyncActive(boolean s) {
		log.debug("Setting storage synchronication to: "+s);	
		syncActive = s;
	}
	
	public void addArchive(String name, Archive archive) {
		archives.put(name, archive);
	}
	
	public Archive getArchive(String name) {
		return archives.get(name);
	}

	public DataPoint[] getDataForArchive(String name, int bucketIndex) {
		return archives.get(name).getDataForBucket(bucketIndex);
	}
	
	protected Hashtable<String, Archive> getArchives() {
		return archives;
	}

	protected void setArchives(Hashtable<String, Archive> archives) {
		this.archives = archives;
	}
}
