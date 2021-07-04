package proj_1_polling_place_v2;

/*
 * The 'Geometry' class is where we aggregate all geometric operations of the
 * polling places assignment. Note that all of the methods are static which
 * means they can be called without being associated with objects. This also
 * means that the data on which they work must be provided parameters.
 */

public class Geometry {

	/*
	 * When we do arithmetic with real numbers (i.e. when we use 'double' and
	 * 'float' type), we may encounter errors caused by imprecise numerical
	 * representations; please see the equalityTests() method in the 'GeometryTests'
	 * class, you might be surprised when you run it. The epsilonEquals() method is
	 * a way to comparing two real numbers and concluding that they are equal if the
	 * difference between them is minuscule.
	 */
	public static final double EPSILON = 0.0000000001;

	public static boolean equalsEpsilon(double d1, double d2) {
		return Math.abs(d1 - d2) < EPSILON;
	}

	/*
	 * Returns true if c is a value with the interval of j to k, but even when j and
	 * k are in descending order.
	 */
	public static boolean within(Double j, Double k, Double c) {
		return c >= Math.min(j, k) && c <= Math.max(j, k);
	}

	/*
	 * Returns the slope of the line between the two given points. It is assumed
	 * that this function will not be called if the line segment is vertical.
	 * 
	 */
	public static double slope(Point j, Point k) {
		assert j.x() != k.x();
		return (k.y() - j.y()) / (double) (k.x() - j.x());
	}

	/*
	 * Segment j constitutes of the j1-j2 range and segment k constitutes of k1-k2
	 * range. There are three possibilities: j may be less than k in its entirety, j
	 * may be greater than k in its entirety, or j and k may overlap. If there is an
	 * overlap (the third case), then the minimum end of the overlap interval is
	 * returned. If there is no overlap (the first and second cases), a null value
	 * is returned.
	 */
	public static Double overlapMin(Double j1, Double j2, Double k1, Double k2) {
		double jMin = Math.min(j1, j2);
		double jMax = Math.max(j1, j2);

		double kMin = Math.min(k1, k2);
		double kMax = Math.max(k1, k2);

		if (jMax < kMin)
			return null;
		if (jMin > kMax)
			return null;

		if (jMin < kMin)
			return kMin;
		else
			return jMin;
	}

	/*
	 * Counterpart to overlapMin() but considers the maximum end of overlap.
	 */
	public static Double overlapMax(Double j1, Double j2, Double k1, Double k2) {
		double jMin = Math.min(j1, j2);

		double jMax = Math.max(j1, j2);

		double kMin = Math.min(k1, k2);
		double kMax = Math.max(k1, k2);

		if (jMin > kMax)
			return null;
		if (jMax < kMin)
			return null;

		if (jMax > kMax)
			return kMax;
		return jMax;
	}

	/*
	 * As a district polygon is being built one line segment at a time, this method
	 * is called to make sure that the next line segment does not overlap with the
	 * previous one.
	 */
	public static boolean canFollow(Point j, Point c, Point k) {
		if (j.x() == c.x() && c.x() == k.x())
			// Both segments are vertical; one can follow the other if they don't overlap
			return (j.y() < c.y() && k.y() > c.y()) && (j.y() > c.y() && k.y() < c.y());
		if (j.x() == c.x() || c.x() == k.x())
			// Only one of the segments is vertical; so overlap is not possible.
			return true;
		// Neither is vertical so overlap is not possible if their slopes are different.
		return !equalsEpsilon(slope(j, c), slope(c, k));
	}

	/*
	 * Given two line segments j1-j2 and p1-p2, this method returns TRUE if they
	 * have an intersection and FALSE otherwise. The method is resilient to one or
	 * both of the line segments being vertical or horizontal. The only edge case
	 * for which we unconditionally return FALSE is when at least one of the line
	 * segments has a zero length.
	 */
	public static boolean intersects(Point j1, Point j2, Point k1, Point k2) {
		// Error check: Both segments should have length
		if (j1.equals(j2) || (k1.equals(k2)))
			return false;
		if (j1.x() == j2.x() && k1.x() == k2.x())
			// Edge case: Both segments are vertical
			return (j1.x() == k1.x()) && overlapMin(j1.y(), j2.y(), k1.y(), k2.y()) != null;
		if (j1.y() == j2.y() && k1.y() == k2.y())
			// Edge case: Both segments are horizontal
			return (j1.y() == k1.y()) && overlapMin(j1.x(), j2.x(), k1.x(), k2.x()) != null;
		if (j1.x() == j2.x()) {
			// Edge case: Only the first segment is vertical
			double kSlope = slope(k1, k2);
			double kIntercept = k1.y() - (kSlope * k1.x());
			double intersectY = kSlope * j1.x() + kIntercept;
			return within(j1.y(), j2.y(), intersectY) && within(k1.x(), k2.x(), j1.x());
		}
		if (k1.x() == k2.x()) {
			// Edge case: Only the second segment is vertical
			double jSlope = slope(j1, j2);
			double jIntercept = j1.y() - (jSlope * j1.x());
			double intersectY = jSlope * k1.x() + jIntercept;
			return within(k1.y(), k2.y(), intersectY) && within(j1.x(), j2.x(), k1.x());
		}

		// Remaining (i.e. general) case
		double jSlope = slope(j1, j2);
		double jIntercept = j1.y() - (jSlope * j1.x());
		double kSlope = slope(k1, k2);
		double kIntercept = k1.y() - (kSlope * k1.x());
		double x = (jIntercept - kIntercept) / (kSlope - jSlope);
		return within(j1.x(), j2.x(), x) && within(k1.x(), k2.x(), x);
	}

	public static void main(String[] args) {
	}
}
