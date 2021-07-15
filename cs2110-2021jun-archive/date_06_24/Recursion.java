package class_06_24;

public class Recursion {

	public static int factorial(int n) {
		int product = 1;
		for (int i = 1; i <= n; i++) {
			product *= i;
		}
		return product;
	}

	public static int factorialR(int n) {
		if (n < 1) {
			return 1;
		} else {
			return n * factorialR(n - 1);
		}
	}

	public static String binaryConvertion(int n) {
		if (n > 0) {
			return binaryConvertion(n / 2) + (n % 2);
		}
		return "";
	}

	public static boolean isPalindrome(String s) {
		if (s.length() == 1) {
			return true;
		}

		char firstDigit = s.charAt(0);
		char lastDigit = s.charAt(s.length() - 1);

		if (firstDigit == lastDigit) {
			return isPalindrome(s.substring(1, s.length() - 1));

		}
		return false;
	}

	public static boolean isPalindrome(String s, int i) {
		if (i < s.length() / 2) {
			return true;
		}

		if (s.charAt(i) == s.charAt(s.length() - i - 1)) {
			return isPalindrome(s, i - 1);
		}
		return false;
	}


	public static int fibonacchi(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}

		return fibonacchi(n - 1) + fibonacchi(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(factorial(4));
		System.out.println(factorialR(4));
		System.out.println(binaryConvertion(16));
		System.out.println(fibonacchi(5));

	}

}