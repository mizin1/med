package pl.waw.mizinski.med.clusterer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import pl.waw.mizinski.med.distance.DistanceStrategy;
import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;
import pl.waw.mizinski.med.model.PointsCluster;

public class KMeansClusterer {
	
	private DistanceStrategy distanceStrategy;
	
	private int defaultClustersCount = 1;
	private double defaultErrorEpsilon = 0;
	private int defaultMaxIterations = 1000;
	
	private Random random = new Random();
	
	public KMeansClusterer(DistanceStrategy distanceStrategy) {
		this.distanceStrategy = distanceStrategy;
	}

	public KMeansClusterer(DistanceStrategy distanceStrategy, int defaultClustersCount,
			double defaultErrorEpsilon, int defaultMaxIterations) {
		this.distanceStrategy = distanceStrategy;
		this.defaultClustersCount = defaultClustersCount;
		this.defaultErrorEpsilon = defaultErrorEpsilon;
		this.defaultMaxIterations = defaultMaxIterations;
	}

	public List<Cluster> performClustering(List<? extends Cluster> clusters) {
		return performClustering(clusters, defaultClustersCount, defaultErrorEpsilon, defaultMaxIterations);
	}
	
	public List<Cluster> performClustering(List<? extends Cluster> clusters, int clustersCount) {
		return performClustering(clusters, clustersCount, defaultErrorEpsilon, defaultMaxIterations);
	}
	
	public List<Cluster> performClustering(List<? extends Cluster> inputClusters, int clustersCount, double errorEpsilon, int maxIterations) {
		assert(inputClusters.size() >= clustersCount);
		List<Point> olDCentroids = getInitialCentroids(inputClusters, clustersCount);
		List<PointsCluster> clusters;
		double oldDiff = Double.MAX_VALUE;
		double diff = Double.MAX_VALUE;
		int i = 0;
		do {
			clusters = getNewClustering(inputClusters, olDCentroids);
			List<Point> newCentroids = getCentroids(clusters);
			if (newCentroids.size() != olDCentroids.size()) {
				diff =  Double.MAX_VALUE;
				olDCentroids = newCentroids;
				continue;
			} else {
				oldDiff = diff;
				diff = getCentroidsDiff(olDCentroids, newCentroids);
				olDCentroids = newCentroids;
			}
			olDCentroids = newCentroids;
		} while ((oldDiff - diff)/diff > errorEpsilon && ++i < maxIterations);
		return (List)clusters;
	}

	
	private List<Point> getInitialCentroids(List<? extends Cluster> inputClusters, int clustersCount) {
		Set<Point> centroids= new HashSet<Point>();
		do {
			Cluster randomCluster = inputClusters.get(random.nextInt(inputClusters.size()));
			Point centroid = randomCluster.getCentroid();
			centroids.add(centroid);
		} while( centroids.size() < clustersCount);
		return new ArrayList<Point>(centroids);
	}

	private double getCentroidsDiff(List<Point> centroids1, List<Point> centroids2) {
		int sum = 0;
		for (int i = 0; i < centroids1.size(); ++i) {
			sum += distanceStrategy.getDistance(centroids1.get(i), centroids2.get(i));
		}
		return sum/centroids1.size();
	}

	private List<PointsCluster> getNewClustering(List<? extends Cluster> inputClusters, List<Point> centroids) {
		Map<Point, PointsCluster> clustering = new LinkedHashMap<Point, PointsCluster>();
		for(Point centroid : centroids) {
			clustering.put(centroid, new PointsCluster());
		}
		for(Cluster inputCluster : inputClusters) {
			Point centroid = getNearestCentroid(inputCluster, centroids);
			PointsCluster cluster = clustering.get(centroid);
			cluster.addCluster(inputCluster);
		}
		return new LinkedList<PointsCluster>(clustering.values());
	}

	private Point getNearestCentroid(Cluster cluster, List<Point> centroids) {
		double minimalDistance = Double.MAX_VALUE;
		Point nearestCentroid = null;
		for (Point cengtroid : centroids) {
			double currentDistance = distanceStrategy.getDistance(cluster, cengtroid);
			if (currentDistance < minimalDistance) {
				minimalDistance = currentDistance;
				nearestCentroid = cengtroid;
			}
		}
		return nearestCentroid;
	}
	
	private List<Point> getCentroids(List<PointsCluster> clusters) {
		List<Point> centroids = new ArrayList<Point>(clusters.size());
		for(Cluster cluster : clusters) {
			Point centroid = cluster.getCentroid();
			if (centroid != null) {
				centroids.add(centroid);
			}
		}
		return centroids;
	}

	public void setDistanceStrategy(DistanceStrategy distanceStrategy) {
		this.distanceStrategy = distanceStrategy;
	}

	public int getDefaultClustersCount() {
		return defaultClustersCount;
	}

	public void setDefaultClustersCount(int defaultClustersCount) {
		this.defaultClustersCount = defaultClustersCount;
	}

	public double getDefaultErrorEpsilon() {
		return defaultErrorEpsilon;
	}

	public void setDefaultErrorEpsilon(double defaultErrorEpsilon) {
		this.defaultErrorEpsilon = defaultErrorEpsilon;
	}

	public int getDefaultMaxIterations() {
		return defaultMaxIterations;
	}

	public void setDefaultMaxIterations(int defaultMaxIterations) {
		this.defaultMaxIterations = defaultMaxIterations;
	}

	//na potrzeby testów
	void setRandom(Random random) {
		this.random = random;
	}
}
