package pl.waw.mizinski.med.util;

import java.util.Random;

import pl.waw.mizinski.med.model.Point;

public class PointsGenerator {
	
	
	
	public static void main(String[] args) {
		Point[] points = {
				new Point(0, 0),
				new Point(5, 5),
				new Point(-5, -5),
				new Point(10, 10),
				new Point(-10, -10),
				new Point(-5, 5),
				new Point(5, -5),
				new Point(-10, 10),
				new Point(10, -10),
		};
		
		int multiplier = 100;
		
		Random random = new Random();
		for (Point point : points) {
			for(int i=0; i< multiplier; ++i){
				for (int j=0; j<point.getDimensions(); ++j) {
					System.out.print(point.getCoordinate(j) + random.nextGaussian());
					System.out.print('\t');
				}
				System.out.println();
			}
		}
	}
}
