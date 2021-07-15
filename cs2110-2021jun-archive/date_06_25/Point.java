package date_06_25;

public class Point {
	private double x;
	private double y;
	
	// Default constructor
	public Point() {
		x = 0;
		y = 0;
	}

	// Parameterized constructor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	double getX() {
		return this.x;
	}
	
	void setX(double x) {
		this.x = x;
	}
	
	double getY() {
		return this.y;
	}
	
	void setY(double y) {
		this.y = y;
	}
	
	public static boolean equal(Point point1, Point point2) {
		if (point1.x == point2.x && point1.y == point2.y)
			return true;
		else
			return false;
	}

	public boolean equal(Point other) {
		if (this.x == other.x && this.y == other.y)
			return true;
		else
			return false;
	}
	
	public double distance(Point other) {
		double distance = Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
		return distance;
	}

	public static void main(String[] args) {

	}
}
