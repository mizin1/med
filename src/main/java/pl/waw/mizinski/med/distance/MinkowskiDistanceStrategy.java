package pl.waw.mizinski.med.distance;

import pl.waw.mizinski.med.model.Point;


public abstract class MinkowskiDistanceStrategy implements DistanceStrategy {
	
	private double degree;

	MinkowskiDistanceStrategy(double degree) {
		assert(degree > 0);
		this.degree = degree;
	}
	
	protected double  getMinkowskiDistance(Point point1, Point point2) {
		int dimensions = Math.max(point1.getDimensions(), point2.getDimensions());
		double sum = 0;
		
		for(int i = 0; i < dimensions; ++i) {
			double diff = point1.getCoordinate(i) - point2.getCoordinate(i);
			diff = Math.abs(diff);
			diff = Math.pow(diff, degree);
			sum += diff;
		}
		
		return Math.pow(sum,(1.0/degree));
	}
}
