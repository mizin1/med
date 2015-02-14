package pl.waw.mizinski.med.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointsClusterTest {
	
	
	@Test
	public void shouldGetCentroid() throws Exception {
		Point point1 = new Point(1, 2, -2);
		Point point2 = new Point(0, 2, 1);
		PointsCluster cluster = new PointsCluster(point1, point2);
		Point centroid = cluster.getCentroid();
		assertEquals(0.5, centroid.getCoordinate(0), 0);
		assertEquals(2, centroid.getCoordinate(1), 0);
		assertEquals(-0.5, centroid.getCoordinate(2), 0);
	}
}
