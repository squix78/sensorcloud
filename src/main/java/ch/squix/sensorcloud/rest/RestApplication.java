package ch.squix.sensorcloud.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.rest.sensor.SensorDataPointRessource;
import ch.squix.sensorcloud.rest.sensor.SensorDataPointsRessource;
import ch.squix.sensorcloud.rest.sensor.csv.CsvSensorResource;
import ch.squix.sensorcloud.rest.sensor.current.CurrentDataRessource;
import ch.squix.sensorcloud.rest.sensor.detail.DetailSensorResource;
import ch.squix.sensorcloud.rest.sensor.history.HistorySensorResource;
import ch.squix.sensorcloud.rest.sensor.testdata.TestDataRessource;

import com.googlecode.objectify.ObjectifyService;

public class RestApplication extends Application {
	
    static {
        ObjectifyService.register(SensorData.class);
    }
	
	@Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/sensor/{sensorId}/value/{value}", SensorDataPointRessource.class);
        router.attach("/sensor", SensorDataPointsRessource.class);
        router.attach("/current", CurrentDataRessource.class);
        router.attach("/test", TestDataRessource.class);
        router.attach("/detail/{sensorId}", DetailSensorResource.class);
        router.attach("/history/{sensorId}", HistorySensorResource.class);
        router.attach("/csv/{sensorId}", CsvSensorResource.class);
       
        //router.attach("/submissions", PaperOverviewResource.class);
        return router;
    }

}
