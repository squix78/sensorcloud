package ch.squix.sensorcloud.model.sensor.actor;

public enum ThresholdDirection {
	
	UP(false, true), DOWN(true,false);
	
	private boolean isOldStateAbove;
	private boolean isNewStateAbove;

	ThresholdDirection(boolean isOldStateAbove, boolean isNewStateAbove) {
		this.isOldStateAbove = isOldStateAbove;
		this.isNewStateAbove = isNewStateAbove;
	}
	
	public boolean isOldStateAbove() {
		return isOldStateAbove;
	}
	
	public boolean isNewStateAbove() {
		return isNewStateAbove;
	}

}
