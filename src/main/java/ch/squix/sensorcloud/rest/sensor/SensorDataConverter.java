package ch.squix.sensorcloud.rest.sensor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.squix.sensorcloud.model.sensor.DataTuple;
import ch.squix.sensorcloud.model.sensor.SensorData;
import ch.squix.sensorcloud.rest.sensor.current.DataTupleDto;

public class SensorDataConverter {

	public static DataPointOutDto convertToOverviewDto(SensorData dataPoint) {
		DataPointOutDto dto = convertCommonFields(dataPoint);
		dto.getData().clear();
		dto.getData().add(convertCondensedTuples(dataPoint.getDataTuples()));
		return dto;
	}
	private static DataPointOutDto convertCommonFields(SensorData dataPoint) {
		DataPointOutDto dto = SensorDao.getDataPoint(dataPoint.getSensorId());
		if (dto != null) {
			dto.setValue(dataPoint.getValue());
			dto.setUpdatedDate(dataPoint.getUpdatedDate());
			dto.setAverage(dataPoint.getAverage());
			dto.setTupleCount(dataPoint.getDataTuples().size());
			dto.setTrend(dataPoint.getTrend());
			DataTuple oldestTuple = dataPoint.getOldestTuple();
			if (oldestTuple != null) {
				dto.setOldestTupleDate(oldestTuple.getDate());
			}
			DataTuple maxTuple = dataPoint.getMaxTuple();
			if (maxTuple != null) {
				dto.setMaxValue(maxTuple.getValue());
				dto.setMaxDate(maxTuple.getDate());
			}
			DataTuple minTuple = dataPoint.getMinTuple();
			if (minTuple != null) {
				dto.setMinValue(minTuple.getValue());
				dto.setMinDate(minTuple.getDate());
			}
			
			return dto;
		}
		return null;
	}
	public static DataPointOutDto convertToDetailDto(SensorData dataPoint) {
		DataPointOutDto dto = convertCommonFields(dataPoint);
		dto.getData().clear();
		dto.getData().add(convertAllTuples(24, dataPoint.getDataTuples()));
		return dto;
	}
	
	public static DataPointOutDto convertToHistoryDto(SensorData dataPoint) {
		DataPointOutDto dto = convertCommonFields(dataPoint);
		dto.getData().clear();
		dto.getData().add(convertAllTuples(100*24, dataPoint.getHistory()));
		return dto;
	}

	private static DataTupleDto convertAllTuples(int maxHours, List<DataTuple> dataTuples) {
		List<List<String>> dtoTuples = new ArrayList<>();
		Long now = new Date().getTime();
		for (DataTuple tuple : dataTuples) {
			List<String> doubleTuple = new ArrayList<>();
			long delta = (now - tuple.getDate().getTime()) / (1000 * 60 * 60);
			if (delta < maxHours) {
				doubleTuple.add(String.valueOf(tuple.getDate().getTime()));
				doubleTuple.add(String.valueOf(tuple.getValue()));
				dtoTuples.add(doubleTuple);
			}
		}
		return new DataTupleDto(dtoTuples, "data");
	}
	private static DataTupleDto convertCondensedTuples(List<DataTuple> dataTuples) {
		List<List<String>> dtoTuples = new ArrayList<>();
		int i = 1;
		Double value = 0d;
		Long now = new Date().getTime();
		for (DataTuple tuple : dataTuples) {
			value += tuple.getValue();
			if (i % 12 == 0) {
				List<String> doubleTuple = new ArrayList<>();
				Long delta = (tuple.getDate().getTime() - now) / (1000 * 60 * 60);
				doubleTuple.add(String.valueOf(delta));
				doubleTuple.add(String.valueOf(roundTo2Decimals(value / 12)));
				dtoTuples.add(doubleTuple);
				value = 0d;
			}
			i++;
		}

		return new DataTupleDto(dtoTuples, "data");
	}

	private static double roundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.##");
		return Double.valueOf(df2.format(val));
	}

}
