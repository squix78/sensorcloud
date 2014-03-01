package ch.squix.sensorcloud.rest.sensor;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.model.sensor.actor.Actor;
import ch.squix.sensorcloud.model.sensor.actor.ActorDao;
import ch.squix.sensorcloud.model.sensor.actor.ThresholdDirection;

public class SensorDataPointsRessource extends ServerResource {
	
	private static final Logger logger = Logger.getLogger(SensorDataPointsRessource.class.getName());
	
	@Get(value = "json")
	public String execute() {
		List<DataPointInDto> tuples = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			String sensorIdText = (String) this.getQueryValue("id"+i);
			String valueText = (String) this.getQueryValue("value"+i);
			if (sensorIdText == null || valueText == null) {
				break;
			}
			DataPointInDto tuple = new DataPointInDto();
			tuple.setId(Long.valueOf(sensorIdText));
			tuple.setValue(Double.valueOf(valueText));
			tuples.add(tuple);
		}
		logger.info("Received " + tuples.size() + " values by GET");
		return execute(tuples.toArray(new DataPointInDto[tuples.size()]));
	}
	
	@Post(value = "json")
	public String execute(DataPointInDto[] tuples) {
		ActorService actorService = new ActorService();
		for (DataPointInDto tuple : tuples) {
			SensorData entry = ofy().load().type(SensorData.class).filter("sensorId = ", tuple.getId()).first().now();
			if (entry == null) {
				entry = new SensorData();
				entry.setSensorId(tuple.getId());
			} 
			DataTuple dataTuple = new DataTuple(tuple.getValue(), new Date());
			entry.addTuple(dataTuple);
			entry.cleanOldEntries(7*24);
			actorService.checkNotifications(entry);
			ofy().save().entity(entry);
		}
		return "OK";
	}





}
