package date_07_02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

interface PointStorage {
	public void add(Point p);
	public Point fetch(int i);

}

class byY implements Comparator<Point> {
	public int compare(Point p1, Point p2) {
		if (p1.y() < p2.y()) {
			return -1;
		} else if (p1.y() > p2.y()) {
			return 1;
		} else {
			return 0;
		}
	}
}

class byX implements Comparator<Point> {
	public int compare(Point p1, Point p2) {
		if (p1.x() < p2.x()) {
			return -1;
		} else if (p1.x() > p2.x()) {
			return 1;
		} else {
			return 0;
		}
	}
}

class byD implements Comparator<Point> {
	static final Point origin = new Point(0.0, 0.0);
	public int compare(Point p1, Point p2) {
		if (p1.distance(origin) < p2.distance(origin)) {
			return -1;
		} else if (p1.distance(origin) > p2.distance(origin)) {
			return 1;
		} else {
			return 0;
		}
	}
}

class PointArray implements PointStorage {
	List<Point> points = new ArrayList<>();

	public void add(Point p) {
		if (isLargerest(p))
			points.add(p);
	}

	public Point fetch(int i) {
		return points.get(i);
	}

	private boolean isLargerest(Point newPoint) {
		for (Point p : points) {
			if (p.compareTo(newPoint) != 1)
				return false;
		}
		return true;
	}
}

class PointList implements PointStorage {
	List<Point> points = new LinkedList<>();
	Comparator<Point> comparator;

	public PointList(Comparator<Point> comparator) {
		this.comparator = comparator;
	}

	public void add(Point p) {
		if (isLargerest(p))
			points.add(p);
	}

	public Point fetch(int i) {
		return points.get(i);
	}

	private boolean isLargerest(Point newPoint) {
		for (Point p : points) {
			if (comparator.compare(p, newPoint) < 0)
				return false;
		}
		return true;
	}
}

public class Interfaces {
	public static void main(String[] args) {
		
	}

}
