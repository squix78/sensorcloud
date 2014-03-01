package ch.squix.sensorcloud.model.sensor;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class SensorDataTest {
	
	@Test 
	public void testCleanup() {
		SensorData data = new SensorData();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -20);
		data.addTuple(new DataTuple(5.0, calendar.getTime()));
		calendar.add(Calendar.HOUR_OF_DAY, -20);
		data.addTuple(new DataTuple(5.0, calendar.getTime()));
		
		Assert.assertEquals(2, data.getDataTuples().size());
		data.cleanOldEntries(24);
		Assert.assertEquals(1, data.getDataTuples().size());
		
	}
	
	@Test 
	public void testArchive() {
		SensorData data = new SensorData();
		data.addTuple(getTestTuple(1.0, -65));
		data.addTuple(getTestTuple(2.0, -55));
		data.addTuple(getTestTuple(3.0, -45));
		data.addTuple(getTestTuple(4.0, -35));
		data.addTuple(getTestTuple(5.0, 10));
		data.addTuple(getTestTuple(5.0, 65));
		
		Assert.assertEquals(6, data.getDataTuples().size());
		Assert.assertEquals(0, data.getHistory().size());
		data.archiveEntries();
		Assert.assertEquals(6, data.getDataTuples().size());
		Assert.assertEquals(3, data.getHistory().size());
		
	}
	
	private DataTuple getTestTuple(Double value, int minutesDelta) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, 11, 29, 3, 0, 0);
		calendar.add(Calendar.MINUTE, minutesDelta);
		return new DataTuple(value, calendar.getTime());
	}
	
	@Test
	public void testAverage1() {
		SensorData data = getTestData();
		Assert.assertEquals(5.0, data.getAverage());
	}
	
	@Test
	public void testAverage2() {
		SensorData data = getTestData();
		Assert.assertEquals(5.0, data.getAverage(0,9));
	}
	
	@Test
	public void testAverage3() {
		SensorData data = getTestData();
		Assert.assertEquals(1.0, data.getAverage(0,1));
	}
	
	@Test
	public void testAverage4() {
		SensorData data = getTestData();
		Assert.assertEquals(8.0, data.getAverage(6, 3));
	}
	
	@Test
	public void testTrend1() {
		SensorData data = getTestData();
		data.addTuple(new DataTuple(10.0, new Date()));
		Assert.assertEquals(1, data.getTrend());
	}
	
	@Test
	public void testTrend2() {
		SensorData data = getTestData();
		data.addTuple(new DataTuple(7.0, new Date()));
		Assert.assertEquals(-1, data.getTrend());
	}


	private SensorData getTestData() {
		Double [] testData = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
		SensorData data = new SensorData();
		for (Double number : testData) {
			data.addTuple(new DataTuple(number, new Date()));
		}
		return data;
	}

}
