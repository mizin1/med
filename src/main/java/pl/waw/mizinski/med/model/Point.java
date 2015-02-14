package pl.waw.mizinski.med.model;

import java.util.Arrays;
import java.util.List;

public class Point implements Cluster {
	
	private double[] coordinates;

	public Point(double... coordinates) {
		this.coordinates = coordinates;
	}

	public int getDimensions() {
		return coordinates.length;
	}
	
	public double getCoordinate(int i) {
		return i < coordinates.length ? coordinates[i] : 0;
	}
	
	@Override
	public List<Point> getPoints() {
		return Arrays.asList(this);
	}
	
	@Override
	public Point getCentroid() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coordinates);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (!Arrays.equals(coordinates, other.coordinates))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(coordinates);
	}
 }
