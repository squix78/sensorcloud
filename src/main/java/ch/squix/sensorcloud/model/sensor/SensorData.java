package ch.squix.sensorcloud.model.sensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.annotation.Serialize;

@Entity
@Cache
public class SensorData {
	
	@Id
	private Long id;
	
	@Index
	private Long sensorId;
	
	@Index
	private Date createdDate;
	
	@Index
	private Date updatedDate;
	
	@Serialize(zip=true)
	private List<DataTuple> dataTuples = new ArrayList<>();
	
	@Serialize(zip=true)
	private List<DataTuple> history = new ArrayList<>();
	
	private String state = "" ;
	
	@OnSave
	private void writeDate() {
		if (createdDate == null) {
			createdDate = new Date();
		}
		updatedDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public Long getSensorId() {
		return sensorId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Double getValue() {
		return getLastValue().getValue();
	}
	
	public DataTuple getLastValue() {
		return dataTuples.get(dataTuples.size() - 1);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @return the dataTuples
	 */
	public List<DataTuple> getDataTuples() {
		return dataTuples;
	}

	public void addTuple(DataTuple tuple) {
		dataTuples.add(tuple);
	}
	
	public void cleanOldEntries(int maxHours) {
		Long maxAge = new Date().getTime() - maxHours * 1000 * 60 * 60;
		List<DataTuple> newTuples = new ArrayList<>();
		for (DataTuple dataTuple : dataTuples) {
			if (dataTuple.getDate().getTime() >= maxAge) {
				newTuples.add(dataTuple);
			} 
		}
		dataTuples = newTuples;
		archiveEntries();
	}
	
	public void archiveEntries() { 
		try {
			List<DataTuple> allTuples = new ArrayList<>();
			allTuples.addAll(history);
			allTuples.addAll(dataTuples);
			history.clear();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH00");
			SimpleDateFormat parseFormat = new SimpleDateFormat("yyyyMMddHHss");
			String oldKey = null;
			Double sum = 0.0;
			int counter = 0;
			for (DataTuple tuple : allTuples) {
				String key = dateFormat.format(tuple.getDate());
				if (oldKey != null && !key.equals(oldKey)) {
					history.add(new DataTuple(sum/counter, parseFormat.parse(oldKey)));
					sum = 0.0;
					counter = 0;
				}
				sum += tuple.getValue();
				counter++;
				oldKey = key;
			}
			Collections.sort(history, new Comparator<DataTuple>() {

				@Override
				public int compare(DataTuple o1, DataTuple o2) {
					return o1.getDate().compareTo(o2.getDate());
				}
				
			});
		} catch (ParseException e) {
			// ignore
			System.out.println(e);
		}
	}
	
	public Double getAverage() {
		return getAverage(0, dataTuples.size());
	}
	
	public Double getAverage(int from, int numberOfElements) {
		from = Math.max(0, from);
		int to = Math.min(dataTuples.size(), from + numberOfElements);
		Double sum = 0.0d;
		int numberOfTuples = dataTuples.size();
		if (numberOfTuples == 0) {
			return 0.0d;
		}
		int count = 0;
		for (int i = from; i < to; i++) {
			sum += dataTuples.get(i).getValue();
			count++;
		}
		return sum / count;
	}
	
	public Double getAverageLastN(int n) {
		return getAverage(dataTuples.size() - n, n);
	}

	
	public long getTrend() {
		Double average = getAverage(dataTuples.size() - 3, 3);
		return Math.round(getValue() - average);
	}

	public DataTuple getMaxTuple() {
		DataTuple maxTuple = null;
		for (DataTuple tuple : dataTuples) {
			if (maxTuple == null || tuple.getValue().compareTo(maxTuple.getValue()) > 0) {
				maxTuple = tuple;
			}
		}
		return maxTuple;
	}
	
	public DataTuple getMinTuple() {
		DataTuple minTuple = null;
		for (DataTuple tuple : dataTuples) {
			if (minTuple == null || tuple.getValue().compareTo(minTuple.getValue()) <= 0) {
				minTuple = tuple;
			}
		}
		return minTuple;
	}
	public DataTuple getOldestTuple() {
		if (dataTuples.size() > 0) {
			return dataTuples.get(0);
		}
		return null;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	public List<DataTuple> getHistory() {
		return history;
	}
	

}
