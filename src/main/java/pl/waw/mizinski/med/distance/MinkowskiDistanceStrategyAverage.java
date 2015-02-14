package pl.waw.mizinski.med.distance;

import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;

public class MinkowskiDistanceStrategyAverage extends MinkowskiDistanceStrategy{

	MinkowskiDistanceStrategyAverage(double degree) {
		super(degree);
	}

	@Override
	public double getDistance(Cluster cluster1, Cluster cluster2) {
		double sum = 0;
		for(Point point1 : cluster1.getPoints()) {
			for (Point point2 : cluster2.getPoints()) {
				double currentDistance = getMinkowskiDistance(point1, point2);
				sum += currentDistance;
			}
		}
		return sum / (cluster1.getPoints().size() * cluster2.getPoints().size());
	}

}
