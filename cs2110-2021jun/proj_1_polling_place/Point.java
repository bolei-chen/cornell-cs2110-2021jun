package proj_1_polling_place;

public class Point {
	private double x;  // ***This MUST stay private***
	private double y;  // ***This MUST stay private***

	// We only have the parameterized constructor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Getter for the x-coordinate
	public double x() {
		return x;
	}

	// Getter for the y-coordinate
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
