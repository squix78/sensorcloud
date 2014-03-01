package ch.squix.sensorcloud.rest.sensor.current;

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
import ch.squix.sensorcloud.rest.sensor.SensorDao;
import ch.squix.sensorcloud.rest.sensor.SensorDataConverter;

public class CurrentDataRessource extends ServerResource {

	private static final Logger logger = Logger.getLogger(CurrentDataRessource.class.getName());


	@Get(value = "json")
	public List<DataPointOutDto> execute() {
		List<SensorData> dataPoints = ofy().load().type(SensorData.class).list();
		List<DataPointOutDto> dataPointDtos = new ArrayList<>();
		for (SensorData dataPoint : dataPoints) {
				dataPointDtos.add(SensorDataConverter.convertToOverviewDto(dataPoint));
		}
		return dataPointDtos;
	}



}
