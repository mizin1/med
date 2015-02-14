package pl.waw.mizinski.med.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pl.waw.mizinski.med.model.Point;

public class PointsLoaderTest {

	private PointsLoader pointsLoader = new PointsLoader();
	
	@Test
	public void shouldLoadPointsFromFile() throws Exception {
		List<Point> points = pointsLoader.loadPointsFromFile(getClass().getResource("points.tsv").toURI());
		assertEquals(2, points.size());
		assertEquals(1, points.get(0).getCoordinate(0), 0);
		assertEquals(2, points.get(0).getCoordinate(1), 0);
		assertEquals(3, points.get(0).getCoordinate(2), 0);
		assertEquals(-1, points.get(1).getCoordinate(0), 0);
		assertEquals(0.5, points.get(1).getCoordinate(1), 0);
		assertEquals(-3.345, points.get(1).getCoordinate(2), 0);
	}
}
