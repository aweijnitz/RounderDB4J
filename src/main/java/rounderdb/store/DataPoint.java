package rounderdb.store;

public class DataPoint {
	
	private Double value;
	private Long timestamp;
	
	public DataPoint() {}
	
	public DataPoint(double value, long timestamp) {
		this.setValue(new Double(value));
		this.setTimestamp(new Long(timestamp));
	}

	public double getValue() {
		return value.doubleValue();
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setValue(double value) {
		this.value = new Double(value);
	}

	public void setValue(int value) {
		this.value = new Double(value);
	}
	
	public long getTimestamp() {
		return timestamp.longValue();
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
