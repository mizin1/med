package pl.waw.mizinski.med.distance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.waw.mizinski.med.model.Point;
import pl.waw.mizinski.med.model.PointsCluster;

public class DistanceStrategyTest {

	@Test
	public void shouldGetManhatanDistane() throws Exception {
		DistanceStrategy distanceStrategy = DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS, DistanceStrategyFactory.MANHATTAN_DISTANE);
		double distance = distanceStrategy.getDistance(new Point(-1 , 2, 1), new Point(-1.4, 3.4, -2));
		assertEquals(4.8, distance, 0);
	}
	
	@Test
	public void shouldGetEuclideanDistane() throws Exception {
		DistanceStrategy distanceStrategy = DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS, DistanceStrategyFactory.EUCLIDEAN_DISTANCE);
		double distance = distanceStrategy.getDistance(new Point(1, 1), new Point(2, -2));
		assertEquals(Math.sqrt(10), distance, 0);
	}
	
	@Test
	public void shouldGetMinimalDistance() throws Exception {
		DistanceStrategy distanceStrategy = DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.MINIMAL, DistanceStrategyFactory.EUCLIDEAN_DISTANCE);
		PointsCluster cluster1 = new PointsCluster(new Point(1, 1), new Point(1,2), new Point(2,1), new Point(2,2));
		PointsCluster cluster2 = new PointsCluster(new Point(-1, -1), new Point(-1,-2), new Point(-2,-1), new Point(-2,-2));
		double distance = distanceStrategy.getDistance(cluster1, cluster2);
		assertEquals(2*Math.sqrt(2), distance, 0);
	}
	
	@Test
	public void shouldGetCentroidsDistance() throws Exception {
		DistanceStrategy distanceStrategy = DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS, DistanceStrategyFactory.EUCLIDEAN_DISTANCE);
		PointsCluster cluster1 = new PointsCluster(new Point(1, 1), new Point(1,2), new Point(2,1), new Point(2,2));
		PointsCluster cluster2 = new PointsCluster(new Point(-1, -1), new Point(-1,-2), new Point(-2,-1), new Point(-2,-2));
		double distance = distanceStrategy.getDistance(cluster1, cluster2);
		assertEquals(3*Math.sqrt(2), distance, 0.000001);
	}
	
	@Test
	public void shouldGetAverageDistance() throws Exception {
		DistanceStrategy distanceStrategy = DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.AVERAGE, DistanceStrategyFactory.MANHATTAN_DISTANE);
		PointsCluster cluster1 = new PointsCluster(new Point(1, 1), new Point(1,2), new Point(2,1), new Point(2,2));
		PointsCluster cluster2 = new PointsCluster(new Point(-1, -1), new Point(-1,-2), new Point(-2,-1), new Point(-2,-2));
		double distance = distanceStrategy.getDistance(cluster1, cluster2);
		double expected = (2*10 + 10+14 + 14+10 + 2*14)/(4.0*4.0);
		assertEquals(expected, distance, 0.000001);
	}
}
