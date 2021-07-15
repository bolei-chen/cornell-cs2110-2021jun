package probelm_set_2;

public class Point implements Comparable<Point> {

    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    public int compareTo(Point p) {
        if (z > p.z()) {
            return 1;
        } else if (z < p.z()) {
            return -1;
        } else {
            if (y < p.y()) {
                return 1;
            } else if (y > p.y()) {
                return -1;
            } else {
                if (x > p.x()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

    }

}
