package proj_1_polling_place_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class District {
	// When we are looking for a point outside the polygon of a district to which
	// lines need to be drawn, we have to find one that can avoids all corners of
	// the polygon. Finding such a point can require multiple attempts but it is
	// likely not to fail more than a few times. If the number of attempts exceed
	// MAX_NUM_OF_ATTEMPTS, then there is an issue that really requires our
	// attention.
	int MAX_NUM_OF_ATTEMPTS = 10;

	private List<Point> corners = null;

	private Double leftMost = null;
	private Double rightMost = null;
	private Double lowest = null;
	private Double highest = null;

	boolean ignoreErrors = true;

	public void save() {
		save(System.out);
	}

	public void save(String pkg, String fileName) throws Exception {
		String outPath = pkg + File.separatorChar + fileName;
		PrintStream out = new PrintStream(outPath);
		save(out);
		out.close();
	}

	public void save(PrintStream out) {
		for (Point p : corners) {
			out.println(p.x() + "," + p.y());
		}
	}

	public void displayGV(boolean showCoordiates) {
		// Paste the output of this method at http://dreampuf.github.io/GraphvizOnline
		System.out.println("graph g {");
		System.out.println("\tgraph[layout=neato]");
		System.out.println("\tgraph[margin=0]");
		if (showCoordiates)
			System.out.println("\tnode[shape=plaintext]");
		else
			System.out.println("\tnode[shape=point]");
		int i = 0;
		for (Point p : corners) {
			String label = "\"(" + p.x() + "," + p.y() + ")\"";
			String pos = "\"" + p.x() + "," + p.y() + "!\"";
			System.out.println("\t" + i + " [label=" + label + ",pos=" + pos + "]");
			if (i > 0) {
				System.out.println("\t" + i + " -- " + (i - 1));
			}
			i++;
		}
		System.out.println("\t" + (corners.size() - 1) + " -- " + 0);
		System.out.println("}");
	}

	/*
	 * This method should be called from the constructors of District to make sure
	 * that the polygon boundary is valid.
	 */
	private void constructorCore(List<Point> points) {
		/*
		 * Tests to make sure that each edge is consistent with the one that precedes
		 * it. This means that except for the shared corner, the two edges do not
		 * overlap. Note that we temporarily inject a copy of the first point at the end
		 * the list. This is actually not a thread-safe thing to do but it does lead to
		 * cleaner code (for now).
		 */
		points.add(points.get(0));
		for (int c = 1; c < points.size() - 1; c++) {
			Point j = points.get(c - 1);
			Point cPoint = points.get(c);
			Point k = points.get(c + 1);
			if (!Geometry.canFollow(j, cPoint, k) && !ignoreErrors) {
				System.err.println("Breakpoint");
			}
			if (!Geometry.canFollow(j, cPoint, k) && !ignoreErrors) {
				System.err.println("Cannot follow: " + j + " - " + c + " - " + k);
				System.exit(1);
			}
		}
		points.remove(points.size() - 1);

		/*
		 * Makes sure that none of the edges of the district cross another one.
		 */
		for (int j = 0; j < points.size() - 3; j++) {
			Point j1 = points.get(j);
			Point j2 = points.get(j + 1);
			for (int k = j + 2; k < points.size() - 1; k++) {
				Point k1 = points.get(k);
				Point k2 = points.get(k + 1);
				if (Geometry.intersects(j1, j2, k1, k2) && !ignoreErrors) {
					System.err.println("Breakpoint");
				}
				if (Geometry.intersects(j1, j2, k1, k2) && !ignoreErrors) {
					System.err.println("Intersects: " + j1 + " - " + j2 + " , " + k1 + " - " + k2);
					System.exit(1);
				}
			}
		}

		/*
		 * Updates the boundaries of the rectangle that contains the district. This
		 * method comes in handy when an out-there point needs be generated as we test
		 * to see if a point falls within the district.
		 */
		leftMost = points.get(0).x();
		rightMost = points.get(0).x();
		lowest = points.get(0).y();
		highest = points.get(0).y();
		for (int cIdx = 1; cIdx < points.size(); cIdx++) {
			Point c = points.get(cIdx);
			if (c.x() < leftMost)
				leftMost = c.x();
			if (c.x() > rightMost)
				rightMost = c.x();
			if (c.y() < lowest)
				lowest = c.y();
			if (c.y() > highest)
				highest = c.y();
		}

		/*
		 * We can now commit the point list.
		 */
		corners = points;
	}

	/*
	 * This method reads a text file that contains coordinate pairs and returns the
	 * data in two queues: one for the x-coordinates and one for the y-coordimates.
	 */
	public static void readCoordinates(String path, //
			Queue<Double> xCoo, //
			Queue<Double> yCoo//
	) throws Exception {
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] coordinates = line.split(" |,");
			assert coordinates.length == 2;
			xCoo.enqueue(Double.parseDouble(coordinates[0]));
			yCoo.enqueue(Double.parseDouble(coordinates[1]));
			line = br.readLine();
		}
	}

	/*
	 * Determines if 'op' can be used as the other end of a line segment from point
	 * 'p'. The return value is TRUE if the said line segment does not intersect any
	 * of the corners of the polygon.
	 */
	private boolean isUsable(Point p, Point op) {
		// 'op' is short for "outside point"
		assert (p.x() != op.x() && p.y() != op.y());
		assert (op.x() < leftMost || op.x() > rightMost);
		assert (op.y() < lowest || op.y() > highest);
		double rise = op.y() - p.y();
		double run = op.x() - p.x();
		double slope = rise / run;
		double intercept = op.y() - slope * op.x();
		for (Point c : corners) {
			if (Geometry.within(p.x(), op.x(), c.x()))
				if (Geometry.equalsEpsilon(slope * c.x() + intercept, c.y()))
					return false;
		}
		return true;
	}

	/*
	 * Returns a point to be used in determining whether a location is contained
	 * within the polygon of a district. It is guaranteed that line segments drawn
	 * from 'p' to the returned point will avoid all corners of the polygon.
	 */
	public Point outsidePoint(Point p) {
		double hSpan = rightMost - leftMost;
		double vSpan = highest - lowest;
		int i = 0;
		Point op;
		do {
			if (++i > MAX_NUM_OF_ATTEMPTS) {
				System.err.println("ERROR: We should not be seeing this");
				System.err.println("ERROR: One of the locations is likely to a polygon corner");
				System.exit(0);
			}
			op = new Point(rightMost + (Math.PI * hSpan), highest + vSpan);
		} while (!isUsable(p, op));
		return op;
	}

	/*
	 * Returns a list of points constructed from the two queues that carry x-y
	 * coordinate pairs.
	 */
	public static List<Point> doublesToPoints(Queue<Double> dx, Queue<Double> dy) {
		// ...YOUR WORK GOES HERE...
		List<Point> points = new ArrayList<>();
		String[] xStr = dx.toString().split(" ");
		String[] yStr = dy.toString().split(" ");
		for (int i = 0; i < xStr.length; i++) {
			points.add(new Point(Double.parseDouble(xStr[i]), Double.parseDouble(yStr[i])));
			dx.dequeue();
			dy.dequeue();
		}
		return points;
	}

	/* Constructs a District object from a list of Points. */
	public District(List<Point> points) {
		constructorCore(points);
	}

	/*
	 * Constructs a District object from x-y coordinate pairs coming from a file.
	 * The actual reading of the data needs to be done with the readCoordinates()
	 * method.
	 */
	public District(String path) throws Exception {
		Queue<Double> xCoo = new Queue<>();
		Queue<Double> yCoo = new Queue<>();
		readCoordinates(path, xCoo, yCoo);
		List<Point> corners = doublesToPoints(xCoo, yCoo);
	}

	/*
	 * Determines whether the specified point 'p' is contained within the district.
	 */
	public boolean contains(Point p) {
		// ...YOUR WORK GOES HERE...
		Point pointOutThere = outsidePoint(p);
		int counter = 0;

		for (int i = 0; i < corners.size() - 1; i++) {
			if (Geometry.intersects(p, pointOutThere, corners.get(i), corners.get(i + 1))) {
				counter++;
			}
		}
		if (Geometry.intersects(p, pointOutThere, corners.get(corners.size() - 1), corners.get(0))) {
			counter++;
		}
		if (counter % 2 == 1) {
			return true;
		}
		return false;
	}

	/*
	 * Returns the number of locations that fall within the district.
	 */
	public int countContained(List<Point> locations) {
		// ...YOUR WORK GOES HERE...
		int counter = 0;
		for (Point p : locations) {
			if (contains(p)) {
				counter++;
			}
		}
		return counter;
	}

}
