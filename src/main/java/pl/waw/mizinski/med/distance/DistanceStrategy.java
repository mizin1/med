package pl.waw.mizinski.med.distance;

import pl.waw.mizinski.med.model.Cluster;

public interface DistanceStrategy {
	
	double getDistance(Cluster cluster1, Cluster cluster2);
}
