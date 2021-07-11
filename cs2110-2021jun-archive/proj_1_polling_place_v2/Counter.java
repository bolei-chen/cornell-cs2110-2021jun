package proj_1_polling_place_v2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class Counter {

	public static final String pkgFolder = "proj_1_polling_place_v2";
	public static final char slash = File.separatorChar;
	public static void main(String[] args) throws Exception {
		// ...YOUR WORK GOES HERE...
		Queue<Double> xCoo = new Queue<>();
		Queue<Double> yCoo = new Queue<>();
		District.readCoordinates(pkgFolder + slash + args[0], xCoo, yCoo);
		List<Point> locations = District.doublesToPoints(xCoo, yCoo);
		int counter = 0;
		List<Point> districtPoints = new ArrayList<>();
		if (args.length > 1) {
			for (int i = 1; i < args.length; i++) {
				District.readCoordinates(pkgFolder + slash + args[i], xCoo, yCoo);
				districtPoints = District.doublesToPoints(xCoo, yCoo);
				District d = new District(districtPoints);
				counter += d.countContained(locations);
				districtPoints.clear();
			}
		}
		System.out.println(counter);
	}
}
