package proj_1_polling_place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.Queue;

public class District {
	private List<Point> corners = null;

	private Point leftMost = null;
	private Point rightMost = null;
	private Point lowest = null;
	private Point highest = null;

	public void save() {
		save(System.out);
	}

	/*
	 * Displays on the screen the coordinates of the district in pure text form.
	 */
	public void save(String path) throws Exception {
		PrintStream out = new PrintStream(path);
		save(out);
		out.close();
	}

	/*
	 * Displays to the specified output file the coordinates of the district in pure
	 * text form.
	 */
	public void save(PrintStream out) {
		for (Point p : corners) {
			out.println(p.x() + "," + p.y());
		}
	}

	/*
	 * Displays on the screen the coordinates of the district in GraphViz form.
	 * Pasting the output of this method at the following URL will visually show the
	 * boundary: http://dreampuf.github.io/GraphvizOnline
	 */
	public void displayGV(boolean showCoordiates) {
		displayGV(showCoordiates, System.out);
	}

	/*
	 * Displays to the specified output file the coordinates of the district in
	 * GraphViz form .
	 */
	public void displayGV(boolean showCoordiates, PrintStream out) {
		out.println("graph g {");
		out.println("\tgraph[layout=neato]");
		out.println("\tgraph[margin=0]");
		if (showCoordiates)
			out.println("\tnode[shape=plaintext]");
		else
			out.println("\tnode[shape=point]");
		int i = 0;
		for (Point p : corners) {
			String label = "\"(" + p.x() + "," + p.y() + ")\"";
			String pos = "\"" + p.x() + "," + p.y() + "!\"";
			out.println("\t" + i + " [label=" + label + ",pos=" + pos + "]");
			if (i > 0) {
				out.println("\t" + i + " -- " + (i - 1));
			}
			i++;
		}
		out.println("\t" + (corners.size() - 1) + " -- " + 0);
		out.println("}");
	}

	/*
	 * Given a location that we suspect lies in a district, this method helps
	 * produce a point to which we draw a line to count the number times it
	 * intersects with the boundary of the district.
	 */
	public Point candidateOutThere() {
		Random r = new Random();
		double hSpan = rightMost.x() - leftMost.x();
		double vSpan = rightMost.y() - leftMost.y();
		Point outThere = new Point( //
				rightMost.x() + 10 * r.nextDouble() * hSpan, //
				highest.y() + 10 * r.nextDouble() * vSpan);
		return outThere;
	}

	/*
	 * Tests to make sure that each edge is consistent with the one that precedes
	 * it. This means that except for the shared corner, the two edges do not
	 * overlap. Note that we temporarily inject a copy of the first point at the end
	 * the list. This is actually not a thread-safe thing to do but it does lead to
	 * cleaner code (for now).
	 */
	private void constructorCore(List<Point> points) {
		points.add(points.get(0));
		for (int c = 1; c < points.size() - 1; c++) {
			Point j = points.get(c - 1);
			Point cPoint = points.get(c);
			Point k = points.get(c + 1);
			if (!Geometry.canFollow(j, cPoint, k)) {
				System.err.println("Cannot follow: " + j + " - " + c + " - " + k);
				System.exit(1);
			}
		}
		points.remove(points.size() - 1);

		// Makes sure that none of the edges of the district cross another one.
		for (int j = 0; j < points.size() - 3; j++) {
			Point j1 = points.get(j);
			Point j2 = points.get(j + 1);
			for (int k = j + 2; k < points.size() - 1; k++) {
				Point k1 = points.get(k);
				Point k2 = points.get(k + 1);
				if (Geometry.intersects(j1, j2, k1, k2)) {
					System.err.println("Intersects: " + j1 + " - " + j2 + " , " + k1 + " - " + k2);
					System.exit(1);
				}
			}
		}

		// Updates the boundaries of the rectangle that contains the district. This
		// method comes in handy when an out-there point needs be generated as we test
		// to see if a point falls within the district.
		leftMost = points.get(0);
		rightMost = points.get(0);
		lowest = points.get(0);
		highest = points.get(0);
		for (int cIdx = 1; cIdx < points.size(); cIdx++) {
			Point c = points.get(cIdx);
			if (c.x() < leftMost.x())
				leftMost = c;
			if (c.x() > rightMost.x())
				rightMost = c;
			if (c.y() < lowest.y())
				lowest = c;
			if (c.y() > highest.y())
				highest = c;
		}

		/*
		 * We can now commit the point list.
		 */
		corners = points;
	}

	/*
	 * Reads the (x,y) coordinate pairs form the specified file.
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
	 * Given two parallel arrays that contain (x,y) coordinate pairs (each being a
	 * double value), returns a List carrying corresponding Point objects. This is
	 * needed because the rest of the program works with Point objects, not raw
	 * double values.
	 */
	public static List<Point> doublesToPoints(Queue<Double> dx, Queue<Double> dy) {
		// ...YOUR WORK GOES HERE...

		return null; // ...THIS NEEDS TO CHANGE...
	}

	/*
	 * The constructor to create a District with two parallel arrays that contain
	 * (x,y) coordinate pairs.
	 */
	public District(Queue<Double> xCoo, Queue<Double> yCoo) throws Exception {
		// ...YOUR WORK GOES HERE...
	}

	/*
	 * The constructor to create a District from (x,y) coordinate pairs coming from
	 * a file.
	 * 
	 */
	public District(String path) throws Exception {
		// ...YOUR WORK GOES HERE...
	}

	/*
	 * Determines whether the specified target point 'outThere' is suitable to be
	 * used when drawing a line from a location to count the number of intersections
	 * with the district boundary. In this context, "suitability" means that the
	 * resulting line does not touch any of the corners of the polygon. Lines that
	 * touch corners register as crossing two line segments rendering the final
	 * count incorrect.
	 */
	public boolean usable(Point p, Point outThere) {
		// ...YOUR WORK GOES HERE...

		return false; // ...THIS NEEDS TO CHANGE...
	}

	/*
	 * Determines whether the specified point (i.e. our location) is contained
	 * within the district. It is assumed that the point 'outThere' has already been
	 * determined to be suitable (i.e. the line segment from 'p' to 'outThere' does
	 * not go through any of the corners of the district's boundary polygon.
	 */
	public boolean contains(Point p, Point outThere) {
		// ...YOUR WORK GOES HERE...

		return false; // ...THIS NEEDS TO CHANGE...
	}

	/*
	 * Given a list of points, returns the number of them that fall within the
	 * boundary polygon of the district.
	 */
	public int countContained(List<Point> locations) {
		// ...YOUR WORK GOES HERE...

		return 0; // ...THIS NEEDS TO CHANGE...
	}

	public static void main(String[] args) throws Exception {
		// ...YOUR WORK GOES HERE...
	}
}
