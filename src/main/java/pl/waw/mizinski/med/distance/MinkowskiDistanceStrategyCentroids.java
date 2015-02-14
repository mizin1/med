package pl.waw.mizinski.med.distance;

import pl.waw.mizinski.med.model.Cluster;

public class MinkowskiDistanceStrategyCentroids extends MinkowskiDistanceStrategy {

	MinkowskiDistanceStrategyCentroids(double degree) {
		super(degree);
	}

	@Override
	public double getDistance(Cluster cluster1, Cluster cluster2) {
		return getMinkowskiDistance(cluster1.getCentroid(), cluster2.getCentroid());
	}
	
}
