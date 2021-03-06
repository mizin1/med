package pl.waw.mizinski.med.distance;

import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;

public class MinkowskiDistanceStrategyMinimal extends MinkowskiDistanceStrategy {

	MinkowskiDistanceStrategyMinimal(double degree) {
		super(degree);
	}

	@Override
	public double getDistance(Cluster cluster1, Cluster cluster2) {
		double minimalDistance = Double.MAX_VALUE;
		for(Point point1 : cluster1.getPoints()) {
			for (Point point2 : cluster2.getPoints()) {
				double currentDistance = getMinkowskiDistance(point1, point2);
				minimalDistance = currentDistance < minimalDistance ? currentDistance : minimalDistance;
			}
		}
		return minimalDistance;
	}

}
