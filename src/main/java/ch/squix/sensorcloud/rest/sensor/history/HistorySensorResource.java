package ch.squix.sensorcloud.rest.sensor.history;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.rest.sensor.DataPointOutDto;
import ch.squix.sensorcloud.rest.sensor.SensorDataConverter;

public class HistorySensorResource extends ServerResource {

	private static final Logger logger = Logger.getLogger(HistorySensorResource.class.getName());


	@Get(value = "json")
	public DataPointOutDto execute() {
		String sensorIdText = (String) this.getRequestAttributes().get("sensorId");
		Long sensorId = Long.parseLong(sensorIdText);
		SensorData dataPoint = ofy().load().type(SensorData.class).filter("sensorId = ", sensorId).first().now();
		return SensorDataConverter.convertToHistoryDto(dataPoint);
	}



}
