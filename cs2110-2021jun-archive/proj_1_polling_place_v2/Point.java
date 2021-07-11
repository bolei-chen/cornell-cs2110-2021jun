package proj_1_polling_place_v2;

/*
 * All x-y coordinate pairs in our polling place assignment are represented by
 * instances (objects) of the Point class. It is short and simple but pivotally
 * important in terms of how we design the rest of the code. Since there are no
 * "setter" methods for the contained 'x' and 'y' values, we refer to 'Point'
 * objects as immutable.
 */

public class Point {

	private double x; // ***This MUST stay private***
	private double y; // ***This MUST stay private***

	// We only have the parameterized constructor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// "Getter" for the x-coordinate
	public double x() {
		return x;
	}

	// "Getter" for the y-coordinate
	public double y() {
		return y;
	}

	// The equality of two points is based on the equality of their coordinates.
	public boolean equals(Point other) {
		return x == other.x && y == other.y;
	}

	public String toString() {
		return x + "," + y;
	}

	public double distance(Point other) {
		return Math.sqrt(((x - other.x) * (x - other.x)) + //
				((y - other.y) * (y - other.y)));
	}
}
