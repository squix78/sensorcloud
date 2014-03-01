package ch.squix.sensorcloud.rest.sensor;

import java.util.HashMap;
import java.util.Map;

public class SensorDao {
	
	private final static Map<Long, DataPointOutDto> dtoMap = new HashMap<>();
	
	static {
		DataPointOutDto dto = new DataPointOutDto();
		dto.setLocation("Power Consumation Laundry Machine");
		dto.setUnit("kWh");
		dto.setSensorId(100L);
		dtoMap.put(100L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Humidity Basement");
		dto.setUnit("%");
		dto.setSensorId(101L);
		dtoMap.put(101L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Temperatur Basement");
		dto.setUnit("°C");
		dto.setSensorId(102L);
		dtoMap.put(102L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Humidity Balcony");
		dto.setUnit("%");
		dto.setSensorId(200L);
		dtoMap.put(200L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Temperature 1 Balcony");
		dto.setUnit("°C");
		dto.setSensorType(SensorType.Analog);
		dto.setSensorId(201L);
		dtoMap.put(201L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Temperature 2 Balcony");
		dto.setUnit("°C");
		dto.setSensorType(SensorType.Analog);
		dto.setSensorId(202L);
		dtoMap.put(202L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Barometric Pressure");
		dto.setUnit("Pa");
		dto.setSensorType(SensorType.Analog);
		dto.setSensorId(203L);
		dtoMap.put(203L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Foto Resistor");
		dto.setUnit("");
		dto.setSensorType(SensorType.Analog);
		dto.setSensorId(204L);
		dtoMap.put(204L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Carbon Monoxide");
		dto.setUnit("");
		dto.setSensorType(SensorType.Analog);
		dto.setSensorId(205L);
		dtoMap.put(205L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Tab");
		dto.setUnit("On");
		dto.setSensorType(SensorType.Digital);
		dto.setSensorId(300L);
		dtoMap.put(300L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("A");
		dto.setUnit("On");
		dto.setSensorType(SensorType.Digital);
		dto.setSensorId(301L);
		dtoMap.put(301L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("iPh");
		dto.setUnit("On");
		dto.setSensorType(SensorType.Digital);
		dto.setSensorId(302L);
		dtoMap.put(302L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("PB");
		dto.setUnit("On");
		dto.setSensorType(SensorType.Digital);
		dto.setSensorId(303L);
		dtoMap.put(303L, dto);
		
		dto = new DataPointOutDto();
		dto.setLocation("Total");
		dto.setUnit("On");
		dto.setSensorType(SensorType.Digital);
		dto.setSensorId(304L);
		dtoMap.put(304L, dto);
	}
	
	public static Map<Long, DataPointOutDto> getDataPoints() {
		return dtoMap;
	}
	
	public static DataPointOutDto getDataPoint(Long sensorId) {
		return dtoMap.get(sensorId);
	}

}
