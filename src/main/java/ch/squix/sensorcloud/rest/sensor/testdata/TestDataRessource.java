package ch.squix.sensorcloud.rest.sensor.testdata;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;

public class TestDataRessource extends ServerResource {

	private static final Logger logger = Logger.getLogger(TestDataRessource.class.getName());
	

	@Get(value = "json")
	public String execute() {
		Long [] ids = {100L, 101L, 102L, 200L, 201L, 202L, 203L};
		int [] minValue = {0, 30, 15, 30, -1, 0, 30593};
		int [] maxValue = {10, 80, 20, 80, 22, 23, 30893};
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < ids.length; i++) {
				
				SensorData entry = ofy().load().type(SensorData.class).filter("sensorId = ", ids[i]).first().now();
				if (entry == null) {
					entry = new SensorData();
				} 
				entry.getDataTuples().clear();
				for (int j = 0; j < 288; j++) {
					calendar.setTime(new Date());
					calendar.add(Calendar.MINUTE, (288 - j) * 5);
					Double value = randDouble(minValue[i], maxValue[i]);
					entry.addTuple(new DataTuple(value, calendar.getTime()));
				}
				ofy().save().entity(entry);

		}
		return "OK";
	}
	
	public static Double randDouble(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    Double randomNum = 0.0 + rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}



}
