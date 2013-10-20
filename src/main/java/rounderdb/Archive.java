package rounderdb;

import java.util.ArrayList;

import rounderdb.store.DataPoint;

public class Archive {
	private ArrayList<DataBucket> buckets;

	public Archive() {
		buckets = new ArrayList<DataBucket>();
	}

	public Archive(int nrBuckets) {
		this();
		for (int i = 0; i < nrBuckets; i++)
			buckets.add(new DataBucket());
	}

	public int addBucket(DataBucket b) {
		buckets.add(b);
		return buckets.size();
	}
	
	public DataBucket getBucket(int index) {
		if (index >= 0 && index < buckets.size())
			return buckets.get(index);

		return null;
	}
	
	public int nrBuckets() {
		return buckets.size();
	}

	public DataPoint[] getDataForBucket(int index) {
		if (index >= 0 && index < buckets.size())
			return buckets.get(index).getData();

		return null;
	}

	public void add(double val, long timestamp) {
		if (buckets.size() == 0)
			throw new RuntimeException("No buckets available for storage!");

		boolean rollUp = buckets.get(0).add(val, timestamp);
		if (rollUp && buckets.size() > 1)
			for (int i = 1; i < buckets.size() && rollUp; i++)
				rollUp = buckets.get(i).add(buckets.get(i - 1).aggregate(), timestamp);
	}

}
