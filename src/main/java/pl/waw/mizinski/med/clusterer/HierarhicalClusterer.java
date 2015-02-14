package pl.waw.mizinski.med.clusterer;

import java.util.LinkedList;
import java.util.List;

import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;

public class HierarhicalClusterer {
	
	private KMeansClusterer kMeansClusterer;

	private double defaultReduceFactor = 1.2;
	private int defaultClustersCount = 1;
	
	
	public HierarhicalClusterer(KMeansClusterer kMeansClusterer) {
		this.kMeansClusterer = kMeansClusterer;
	}
	
	public HierarhicalClusterer(KMeansClusterer kMeansClusterer, int defaultReduceFactor) {
		super();
		this.kMeansClusterer = kMeansClusterer;
		this.defaultReduceFactor = defaultReduceFactor;
	}

	public HierarhicalClusterer(KMeansClusterer kMeansClusterer, double defaultReduceFactor, int defaultClustersCount) {
		super();
		this.kMeansClusterer = kMeansClusterer;
		this.defaultReduceFactor = defaultReduceFactor;
		this.defaultClustersCount = defaultClustersCount;
	}

	public List<Cluster> performClustering(List<Point> points) {
		return performClustering(points, defaultClustersCount, defaultReduceFactor);
	}
	
	public List<Cluster> performClustering(List<Point> points, int clustersCount) {
		return performClustering(points, clustersCount, defaultReduceFactor);
	}

	public List<Cluster> performClustering(List<Point> points, int clustersCount, double reduceFactor) {
		List<? extends Cluster> clusters = points;
		while (clusters.size() > clustersCount ) {
			int nextClustersCount = getNextClustersCount(clustersCount, clusters.size(), reduceFactor);
			clusters = kMeansClusterer.performClustering(clusters, nextClustersCount);
			clusters = removeEmptyClusters(clusters);
		}
		return (List<Cluster>) clusters;
	}

	private int getNextClustersCount(int targetClustersCount, int currentClusterCount, double reduceFactor) {
		int ret = (int) Math.ceil(currentClusterCount / reduceFactor);
		ret = ret != currentClusterCount ? ret : ret - 1;
		return  (int) Math.max(ret, targetClustersCount);
	}
	
	private List<Cluster> removeEmptyClusters(List<? extends Cluster> clusters) {
		List<Cluster> ret = new LinkedList<Cluster>();
		for (Cluster cluster : clusters) {
			if (!cluster.getPoints().isEmpty()) {
				ret.add(cluster);
			}
		}
		return ret;
	}

	public KMeansClusterer getkMeansClusterer() {
		return kMeansClusterer;
	}

	public void setkMeansClusterer(KMeansClusterer kMeansClusterer) {
		this.kMeansClusterer = kMeansClusterer;
	}

	public double getDefaultReduceFactor() {
		return defaultReduceFactor;
	}
	
	public void setDefaultReduceFactor(double defaultReduceFactor) {
		this.defaultReduceFactor = defaultReduceFactor;
	}
	
	public int getDefaultClustersCount() {
		return defaultClustersCount;
	}

	public void setDefaultClustersCount(int defaultClustersCount) {
		this.defaultClustersCount = defaultClustersCount;
	}
}
