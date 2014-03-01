package ch.squix.sensorcloud.rest.sensor;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.model.sensor.actor.Actor;
import ch.squix.sensorcloud.model.sensor.actor.ThresholdDirection;

public class ActorTest {
	
	@Test
	public void testActorStateChangeUp() {
		Double [] values = {3.0, 4.0, 5.0, 7.0, 8.0, 9.0};
		Assert.assertTrue("Expected state change", runTest(values, 6.0, ThresholdDirection.UP, 3));
	}
	@Test
	public void testActorStateChangeShortUp() {
		Double [] values = {5.0, 9.0};
		Assert.assertTrue("Expected state change", runTest(values, 6.0, ThresholdDirection.UP, 1));
	}
	@Test
	public void testActorStateChangeShortDown() {
		Double [] values = {9.0, 5.0};
		Assert.assertTrue("Expected state change", runTest(values, 6.0, ThresholdDirection.DOWN, 1));
	}
	@Test
	public void testActorStateChangeShortInvalid() {
		Double [] values = {5.0, 5.0};
		Assert.assertFalse("Expected state change", runTest(values, 6.0, ThresholdDirection.DOWN, 1));
	}
	@Test
	public void testActorStateChangeDown() {
		Double [] values = {7.0, 8.0, 9.0, 3.0, 4.0, 5.0};
		Assert.assertTrue("Expected state change", runTest(values, 6.0, ThresholdDirection.DOWN, 3));
	}
	@Test
	public void testActorNoStateChange() {
		Double [] values = {7.0, 4.0, 5.0, 7.0, 8.0, 9.0};
		Assert.assertFalse("Expected state change", runTest(values, 6.0, ThresholdDirection.UP, 3));
	}
	@Test
	public void testActorStateChangeWrongDirection() {
		Double [] values = {3.0, 4.0, 5.0, 7.0, 8.0, 9.0};
		Assert.assertFalse("Expected state change", runTest(values, 6.0, ThresholdDirection.DOWN, 3));
	}
	
	private boolean runTest(Double [] values, Double threshold, ThresholdDirection direction, int supportingValues) {
		SensorData data = new SensorData();
		for (Double value: values) {
			data.addTuple(new DataTuple(value, new Date()));
		}
		Actor actor = new Actor();
		actor.setThreshold(threshold);
		actor.setSupportingValues(supportingValues);
		actor.setDirection(direction);

		ActorService service = new ActorService();
		return service.isEntryActivatingActor(data, actor);
	}

}
