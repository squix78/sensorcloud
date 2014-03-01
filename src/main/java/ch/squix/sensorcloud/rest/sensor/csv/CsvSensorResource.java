package ch.squix.sensorcloud.rest.sensor.csv;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.logging.Logger;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.rest.sensor.DataPointOutDto;
import ch.squix.sensorcloud.rest.sensor.SensorDataConverter;
import ch.squix.sensorcloud.rest.sensor.current.DataTupleDto;

public class CsvSensorResource extends ServerResource {

	private static final Logger logger = Logger.getLogger(CsvSensorResource.class.getName());


	@Get
	public String execute() {
		String sensorIdText = (String) this.getRequestAttributes().get("sensorId");
		Long sensorId = Long.parseLong(sensorIdText);
		SensorData dataPoint = ofy().load().type(SensorData.class).filter("sensorId = ", sensorId).first().now();
		StringBuilder builder = new StringBuilder();
		for (DataTuple dto :dataPoint.getHistory()) {
			builder.append(dto.getDate().getTime() + "\t" + dto.getValue() +"\n");
		}
		return builder.toString();
	}



}
