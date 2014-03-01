package ch.squix.sensorcloud.rest.sensor;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.logging.Logger;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;

public class SensorDataPointRessource extends ServerResource {
	
	private static final Logger logger = Logger.getLogger(SensorDataPointRessource.class.getName());
	
	@Get(value = "json")
	public String execute() {
		String sensorIdText = (String) this.getRequestAttributes().get("sensorId");
		String valueText = (String) this.getRequestAttributes().get("value");
		logger.info("Received data for " + sensorIdText + " with value " + valueText);
		Long sensorId = Long.valueOf(sensorIdText);
		Double value = Double.valueOf(valueText);
		SensorData dataPoint = new SensorData();
		dataPoint.setSensorId(sensorId);
		dataPoint.addTuple(new DataTuple(value, new Date()));
		dataPoint.cleanOldEntries(48);
		ofy().save().entity(dataPoint);
		return "OK";
	}

}
