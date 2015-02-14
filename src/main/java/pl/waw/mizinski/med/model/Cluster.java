package pl.waw.mizinski.med.model;

import java.util.List;

public interface Cluster {
	
	List<Point> getPoints();
	
	Point getCentroid();
}
