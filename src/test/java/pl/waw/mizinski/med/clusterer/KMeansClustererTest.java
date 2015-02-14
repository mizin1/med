package pl.waw.mizinski.med.clusterer;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.waw.mizinski.med.distance.DistanceMeasurementMethod;
import pl.waw.mizinski.med.distance.DistanceStrategy;
import pl.waw.mizinski.med.distance.DistanceStrategyFactory;
import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;
import pl.waw.mizinski.med.util.PointsLoader;

public class KMeansClustererTest {
	
	private KMeansClusterer kMeansClusterer;
	
	@Before
	public void init() {
		kMeansClusterer = new KMeansClusterer(DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS, DistanceStrategyFactory.EUCLIDEAN_DISTANCE));
		kMeansClusterer.setRandom(new Random(0));
	}
	
	@Test
	public void shouldPerformClusteringWith1Dim() throws Exception {
		List<Point> points = PointsLoader.loadPointsFromFile(getClass().getResource("1dim.tsv").toURI());
		List<Cluster> clusters = kMeansClusterer.performClustering(points, 3, 0, 1000);
		assertEquals(3, clusters.size());
		assertEquals(100, clusters.get(0).getPoints().size());
		assertEquals(100, clusters.get(1).getPoints().size());
		assertEquals(100, clusters.get(2).getPoints().size());
	}
	
	@Test
	public void shouldPerformClusteringWith3Dim() throws Exception {
		DistanceStrategy[] strategies = {
			DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS, DistanceStrategyFactory.EUCLIDEAN_DISTANCE),
			DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS, DistanceStrategyFactory.MANHATTAN_DISTANE),
			DistanceStrategyFactory.getDistanceStrategy(DistanceMeasurementMethod.CENTROIDS,3 )
		};
		
		List<Point> points = PointsLoader.loadPointsFromFile(getClass().getResource("3dim.tsv").toURI());
		for(DistanceStrategy strategy : strategies) {
			kMeansClusterer.setRandom(new Random(0));
			kMeansClusterer.setDistanceStrategy(strategy);
			List<Cluster> clusters = kMeansClusterer.performClustering(points, 3, 0, 1000);
			assertEquals(3, clusters.size());
			assertEquals(100, clusters.get(0).getPoints().size());
			assertEquals(100, clusters.get(1).getPoints().size());
			assertEquals(100, clusters.get(2).getPoints().size());
		}
	}
	
}
