package ch.squix.sensorcloud.model.sensor;

import java.io.Serializable;
import java.util.Date;

public class DataTuple implements Serializable {
    /** start with 1 for all classes */
    private static final long serialVersionUID = 1L;
	
	private Double value;
	private Date date;
	
	public DataTuple() {
		
	}
	
	public DataTuple(Double value, Date date) {
		this.value = value;
		this.date = date;
	}
	
	public Double getValue() {
		return value;
	}
	public Date getDate() {
		return date;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
