package ch.squix.sensorcloud.rest.sensor.current;

import java.util.ArrayList;
import java.util.List;

public class DataTupleDto {
	private List<List<String>> values = new ArrayList<>();
	private String key = "";
	
	public DataTupleDto() {
		
	}
	public DataTupleDto(List<List<String>> values, String key) {
		this.values = values;
		this.key = key;
	}
	
	public List<List<String>> getValues() {
		return values;
	}
	public String getKey() {
		return key;
	}
	public void setValues(List<List<String>> values) {
		this.values = values;
	}
	public void setKey(String key) {
		this.key = key;
	}



}
