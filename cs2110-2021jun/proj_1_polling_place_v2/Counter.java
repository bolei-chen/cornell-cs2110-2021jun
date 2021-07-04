package proj_1_polling_place_v2;

import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class Counter {
	public static void main(String[] args) throws Exception {
		// ...YOUR WORK GOES HERE...
		Queue<Double> xCoo = new Queue<>();
		Queue<Double> yCoo = new Queue<>();
		District.readCoordinates(args[0], xCoo, yCoo);
		List<Point> locations = District.doublesToPoints(xCoo, yCoo);
		int counter = 0;
		for (int i = 1; i < args.length; i++){
			District d = new District(args[i]);
			counter += d.countContained(locations);
		}
		System.out.println(counter);
	}
}
