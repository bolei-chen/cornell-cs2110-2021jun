package class_06_22;

public class BinarySearch {
	public static int rank(int key, int[] a) { // Array must be sorted.
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) { // Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	/*
	 * public static void bubbleSort(int[] a) { int finishedDigit = 0; for (int i =
	 * 0; i < (a.length - 1); i++) { for (int j = 0; j < (a.length - 1 -
	 * finishedDigit); j++) { int temp = 0; if (a[j] > a[j + 1]) { temp = a[j]; a[j]
	 * = a[j + 1]; a[j + 1] = temp; } } finishedDigit++; }
	 * 
	 * }
	 * 
	 * public static void main(String[] args) { int a[] = { 9, -5, 7, 8, 33,
	 * 74564564, 3, 4234, 1, 3, 5, 6 }; bubbleSort(a); System.out.println(rank(1,
	 * a));
	 * 
	 * }
	 * 
	 * }
	 */

	public static void bubbleSort(int[] a) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] > a[i + 1]) {
					int temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
					swapped = true;
				}
			}
		}
	}

	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int a[] = { 9, -5, 7, 8, 33, 74564564, 3, 4234, 1, 3, 5, 6 };
		bubbleSort(a);
		print(a);
		System.out.println(rank(1, a));

	}

}
