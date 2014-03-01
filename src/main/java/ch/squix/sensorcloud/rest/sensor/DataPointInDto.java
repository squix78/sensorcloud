package ch.squix.sensorcloud.rest.sensor;

public class DataPointInDto {
	Long id;
	Double value;
	public Long getId() {
		return id;
	}
	public Double getValue() {
		return value;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setValue(Double value) {
		this.value = value;
	}
}
