package pl.waw.mizinski.med.main;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import pl.waw.mizinski.med.clusterer.HierarhicalClusterer;
import pl.waw.mizinski.med.clusterer.KMeansClusterer;
import pl.waw.mizinski.med.distance.DistanceMeasurementMethod;
import pl.waw.mizinski.med.distance.DistanceStrategy;
import pl.waw.mizinski.med.distance.DistanceStrategyFactory;
import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;
import pl.waw.mizinski.med.util.DrawUtil;
import pl.waw.mizinski.med.util.PointsLoader;

public class Main {
	
	public static void main(String args[]) throws FileNotFoundException, URISyntaxException {
		assert(args.length == 5);
		String arg0 = args[0]; //mdetoda pomiaru odleglosci (0 - CENTROIDS, 1- AVERAGE, 2 - MINIMAL)
		String arg1 = args[1]; //stopien odleglosci Minkowskiego (1 - Manchatan, 2- euclides, etc...)
		String arg2 = args[2]; //paramert errorEpsilon algorytmu k-means
		String arg3 = args[3]; //maksymalna liczba iteracji algorytmu k-means
		String arg4	= args[4]; //parametr reduceFactor grupowania herachicznego
		String arg5 = args[5]; //plik z którego wczytujemy dane
		String arg6 = args[6]; //liczba klastrów na które należy podzielić punkty
		
		//Ustalamy strategię pomiaru odległości:
		DistanceMeasurementMethod distanceMeasurementMethod = DistanceMeasurementMethod.values()[Integer.valueOf(arg0)];
		double minkowskiDistanceDegree = Double.valueOf(arg1);
		DistanceStrategy distanceStrategy = DistanceStrategyFactory.getDistanceStrategy(distanceMeasurementMethod, minkowskiDistanceDegree);
	
		//Tworzymy Clusterer dla algorytmu k-means
		KMeansClusterer kMeansClusterer = new KMeansClusterer(distanceStrategy);
		
		//Maxymalna roznica miedzy centroidami pozwalajaca na zakonczenie algorytmu
		kMeansClusterer.setDefaultErrorEpsilon(Double.valueOf(arg2));
		
		//Mksymalna licza iteracji algorytmu k-means
		kMeansClusterer.setDefaultMaxIterations(Integer.valueOf(arg3));
		
		//Na podstawie clusterera k-means tworzymy cluster
		HierarhicalClusterer hierarhicalClusterer = new HierarhicalClusterer(kMeansClusterer);
		
		//Ile razy ma w kolejnych iteracjach algorytmu spadac liczba klastrów
		hierarhicalClusterer.setDefaultReduceFactor(Double.valueOf(arg4));
		
		//Wczytujemy dane z pliku:
		List<Point> points = PointsLoader.loadPointsFromFile(arg5);
		
		//Wykonujemy grupowanie:
		List<Cluster> clusters = hierarhicalClusterer.performClustering(points, Integer.valueOf(arg6));
		
		//Wypisujemy;
		int i = 1;
		for (Cluster cluster : clusters) {
			System.out.println("Cluster: " + i++);
			for(Point point : cluster.getPoints()) {
				System.out.println(asString(point));
			}
			System.out.println();
		}
		
		//I rysujemy
		DrawUtil drawUtil = new DrawUtil(clusters);
		drawUtil.drawClusters(4);
	}

	private static String asString(Point point) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < point.getDimensions(); ++i) {
			stringBuilder.append( i>0 ? " " : "");
			stringBuilder.append(point.getCoordinate(i));
		}
		return stringBuilder.toString();
	}
}
