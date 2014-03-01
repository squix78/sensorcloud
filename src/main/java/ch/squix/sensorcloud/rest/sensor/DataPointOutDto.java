package ch.squix.sensorcloud.rest.sensor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.rest.sensor.current.DataTupleDto;

public class DataPointOutDto {
	
	private Long sensorId;
	private Double value;
	private String unit;
	private String location;
	private Date updatedDate;
	private Double minValue;
	private Double maxValue;
	private Date minDate;
	private Date maxDate;
	private Double average;
	private Long trend;
	private int tupleCount;
	private Date oldestTupleDate;
	private List<DataTupleDto> data = new ArrayList<>();
	private SensorType sensorType;
	
	public Double getValue() {
		return value;
	}
	public String getUnit() {
		return unit;
	}
	public String getLocation() {
		return location;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the sensorId
	 */
	public Long getSensorId() {
		return sensorId;
	}
	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
	public Double getMinValue() {
		return minValue;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public Date getMinDate() {
		return minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public Double getAverage() {
		return average;
	}
	public Long getTrend() {
		return trend;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public void setTrend(Long trend) {
		this.trend = trend;
	}
	public void setTupleCount(int tupleCount) {
		this.tupleCount = tupleCount;
	}
	
	public int getTupleCount() {
		return tupleCount;
	}

	public Date getOldestTupleDate() {
		return oldestTupleDate;
	}

	public void setOldestTupleDate(Date oldestTupleDate) {
		this.oldestTupleDate = oldestTupleDate;
	}
	/**
	 * @return the data
	 */
	public List<DataTupleDto> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<DataTupleDto> data) {
		this.data = data;
	}
	/**
	 * @return the sensorType
	 */
	public SensorType getSensorType() {
		return sensorType;
	}
	/**
	 * @param sensorType the sensorType to set
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}


}
