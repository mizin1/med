package pl.waw.mizinski.med.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import pl.waw.mizinski.med.model.Point;

public class PointsLoader {
	
	public static List<Point> loadPointsFromFile(String pathname) throws FileNotFoundException {
		return loadPointsFromFile(new File(pathname));
	}
	
	public static List<Point> loadPointsFromFile(URI uri) throws FileNotFoundException {
		return loadPointsFromFile(new File(uri));
	}

	public static List<Point> loadPointsFromFile(File file) throws FileNotFoundException {
		List<Point> points = new LinkedList<Point>();
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			line = line.trim();
			String[] splittedLine = line.split("\\s");
			double[] coordinates = new double[splittedLine.length];
			int i = 0;
			for (String coordinate : splittedLine) {
				coordinates[i++] = Double.valueOf(coordinate);
			}
			points.add(new Point(coordinates));
		}
		return points;
	}
	
}
