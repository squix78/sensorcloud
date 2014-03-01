package ch.squix.sensorcloud.rest.sensor;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.model.sensor.actor.Actor;
import ch.squix.sensorcloud.model.sensor.actor.ActorDao;

public class ActorService {
	
	public void checkNotifications(SensorData entry) {
		List<Actor> actors = ActorDao.getActors(entry.getSensorId());
		for (Actor actor : actors) {
			if (isEntryActivatingActor(entry, actor)) {
				sendMessage(actor);
			}

		}
	}
	
	public boolean isEntryActivatingActor(SensorData entry, Actor actor) {
		List<DataTuple> dataTuples = entry.getDataTuples();
		Integer numberOfTuples = dataTuples.size();
		Integer supportingValues = actor.getSupportingValues();
		List<DataTuple> oldStateValues = dataTuples.subList(numberOfTuples - 2 * supportingValues, numberOfTuples - supportingValues);
		List<DataTuple> newStateValues = dataTuples.subList(numberOfTuples - supportingValues, numberOfTuples);
		boolean isOldStateAbove = actor.getDirection().isOldStateAbove();
		boolean isNewStateAbove = actor.getDirection().isNewStateAbove();
		Double threshold = actor.getThreshold();
		return isValueSetValid(isOldStateAbove, threshold, oldStateValues) 
				&& isValueSetValid(isNewStateAbove, threshold, newStateValues);


	}
	
	public boolean isValueSetValid(boolean isValueSetAbove, Double threshold, List<DataTuple> tuples) {
		for (DataTuple tuple : tuples) {
			if (isValueSetAbove != (tuple.getValue() > threshold)) {
				return false;
			}
		}
		return true;
	}

	private void sendMessage(Actor actor) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("dani.eichhorn@squix.ch", "SensorCollect"));
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress(actor.getEmailRecipient(), "Mr. User"));
		    msg.setSubject(actor.getMessageSubject());
		    msg.setText(actor.getMessageBody());
		    Transport.send(msg);

		} catch (AddressException e) {
		    // ...
		} catch (MessagingException e) {
		    // ...
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
