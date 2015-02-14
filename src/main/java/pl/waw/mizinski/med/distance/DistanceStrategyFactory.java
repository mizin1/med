package pl.waw.mizinski.med.distance;

public class DistanceStrategyFactory {
	
	public static final double MANHATTAN_DISTANE = 1;
	public static final double EUCLIDEAN_DISTANCE = 2;
	
	public static DistanceStrategy getDistanceStrategy(DistanceMeasurementMethod method, double minkowskiDistanceDegree) {
		switch (method) {
		case CENTROIDS:
			return new MinkowskiDistanceStrategyCentroids(minkowskiDistanceDegree);
		case MINIMAL:
			return new MinkowskiDistanceStrategyMinimal(minkowskiDistanceDegree);
		case AVERAGE:
			return new MinkowskiDistanceStrategyAverage(minkowskiDistanceDegree);
		default:
			throw new IllegalStateException("Unknown method: " + method);
		}
	}

}


