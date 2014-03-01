package ch.squix.sensorcloud.model.sensor.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActorDao {
	
	private static Map<Long, List<Actor>> actorMap = new HashMap<>();
	
	static {
		Actor actor = new Actor();
		actor.setSensorId(100L);
		actor.setThreshold(0.4);
		actor.setSupportingValues(2);
		actor.setDirection(ThresholdDirection.DOWN);
		actor.setMessageSubject("Waschmaschine");
		actor.setMessageBody("Deine Waschmaschine hat soeben den Waschvorgang beendet.");
		actor.setEmailRecipient("uTHES7Va441GEHJDNbq9voS7Fauuyd@api.pushover.net");
		addActor(actor);
		
		actor = new Actor();
		actor.setSensorId(101L);
		actor.setThreshold(45.6);
		actor.setSupportingValues(2);
		actor.setDirection(ThresholdDirection.UP);
		actor.setMessageSubject("Keller");
		actor.setMessageBody("Der Keller hat gerade eine Luftfeuchtigkeit von 45.6% überschritten");
		actor.setEmailRecipient("uTHES7Va441GEHJDNbq9voS7Fauuyd@api.pushover.net");
		addActor(actor);
		
		actor = new Actor();
		actor.setSensorId(301L);
		actor.setThreshold(0.5);
		actor.setSupportingValues(1);
		actor.setDirection(ThresholdDirection.UP);
		actor.setMessageSubject("Präsenz");
		actor.setMessageBody("A ist erschienen");
		actor.setEmailRecipient("uTHES7Va441GEHJDNbq9voS7Fauuyd@api.pushover.net");
		addActor(actor);
		
		actor = new Actor();
		actor.setSensorId(301L);
		actor.setThreshold(0.5);
		actor.setSupportingValues(1);
		actor.setDirection(ThresholdDirection.DOWN);
		actor.setMessageSubject("Präsenz");
		actor.setMessageBody("A ist verschwunden");
		actor.setEmailRecipient("uTHES7Va441GEHJDNbq9voS7Fauuyd@api.pushover.net");
		addActor(actor);

	}
	
	public static void addActor(Actor actor) {
		List<Actor> actors = actorMap.get(actor.getSensorId());
		if (actors == null) {
			actors = new ArrayList<>();
			actorMap.put(actor.getSensorId(), actors);
		}
		actors.add(actor);
	}
	
	public static List<Actor> getActors(Long sensorId) {
		List<Actor> actors = actorMap.get(sensorId);
		if (actors == null) {
			actors = new ArrayList<>();
		}
		return actors;
	}

}
