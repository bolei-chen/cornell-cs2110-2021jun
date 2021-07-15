package date_07_13;

public interface DemoAlgorithm {
	public static void main(String[] args) {
		int a[] = { 3, 1, 6, 4, 2, 9, 7, 5 };
		if (sumOfTwo(a, 11))
			System.out.println("It's in there");
		else
			System.out.println("It's not in there");
	}

	public static boolean sumOfTwo(int[] a, int k) {
		return false; // Just to make the compiler happy
	}
}
