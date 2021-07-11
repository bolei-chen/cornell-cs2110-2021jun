package class_07_02;

public class Point implements Comparable<Point> {

	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

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

	public int compareTo(Point other) {
		if (x < other.x()) {
			return -1;

		} else if (x > other.x()) {
			return 1;

		} else {
			return 0;
		}
	}
}
