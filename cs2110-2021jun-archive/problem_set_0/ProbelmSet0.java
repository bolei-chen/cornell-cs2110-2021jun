package problem_set_0;

import java.util.Random;

public class ProbelmSet0 {
	public static int gcd(int num1, int num2) {
		int gcd = 0;
		for (int i = 1; i <= num1 && i <= num2; i++) {
			if (num1 % i == 0 && num2 % i == 0)
				gcd = i;
		}
		return gcd;
	}

	public static void main(String[] args) {
		double reducedFractionCount = 0.0;
		double totalCount = 0.0;
		double ratio = 0.0;
		Random rand = new Random();
		for (int i = 0; i < 100000; i++) {
			int num1 = rand.nextInt(999999);
			int num2 = rand.nextInt(999999);
			int gcd = gcd(num1, num2);
			if (gcd == 1) {
				reducedFractionCount ++;
			}
			totalCount ++;
		}
		ratio = reducedFractionCount / totalCount;
		System.out.println(ratio);
	}
}
