package pl.waw.mizinski.med.model;

import java.util.LinkedList;
import java.util.List;

public class PointsCluster implements Cluster {

	private List<Cluster> clusters = new LinkedList<Cluster>();
	
	public PointsCluster() {
	}
	
	public PointsCluster(List<Cluster> clusters) {
		this.clusters.addAll(clusters);
	}

	public PointsCluster(Cluster... clusters) {
		for(Cluster cluster : clusters) {
			this.clusters.add(cluster);
		}
	}

	public void addCluster(Cluster cluster) {
		clusters.add(cluster);
	}
	
	@Override
	public List<Point> getPoints() {
		List<Point> ret = new LinkedList<Point>();
		for(Cluster cluster : clusters) { 
			ret.addAll(cluster.getPoints());
		}
		return ret;
	}
	
	@Override
	public Point getCentroid() {
		List<Point> points = getPoints();
		if (points.isEmpty()) {
			return null;
//			throw new IllegalStateException("Empty cluster!");
		}
		int dimensions = points.get(0).getDimensions();
		double[] centridCoordinates = new double[dimensions];
		for(Point point : points) {
			for (int i = 0; i < dimensions; ++i) {
				centridCoordinates[i] += point.getCoordinate(i);
			}
		}
		for (int i = 0; i < dimensions; ++i) {
			centridCoordinates[i] = centridCoordinates[i]/points.size();
		}
		return new Point(centridCoordinates);
	}

	@Override
	public String toString() {
		List<Point> points = getPoints();
		return String.format("Cluster{clusters=%d, points=%d, %s}", clusters.size(), points.size(), points);
	}
}
