package problem_set_due_07_07;

public class ArithmeticAndGeometricExpansion {
	public static int arithmeticExpansion(int x, int r) {
		int sum = 0;
		for (int i = 1; i < x + 1; i++) {
			int term = 1 + (i - 1) * r;
			sum += term;
		}
		return sum;
	}

	public static double geomrtricExpantion(int x) {
		double sum = 0;
		for (int i = 1; i < x + 1; i++) {
			double term = 1 * Math.pow(2, i - 1);
			sum += term;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println("n\tari\tgeo");
		for (int n = 1; n <= 20; n = n + 1) {
			System.out.println(n + "\t" + arithmeticExpansion(n, 4) + "\t" + geomrtricExpantion(n) + "\t");

		}
	}
}