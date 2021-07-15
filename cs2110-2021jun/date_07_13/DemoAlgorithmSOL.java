package date_07_13;

import java.util.HashMap;
import java.util.Map;

public interface DemoAlgorithmSOL {
	public static void main(String[] args) {
		int a[] = { 3, 1, 6, 4, 2, 9, 7, 5 };
		if (sumOfTwo(a, 11))
			System.out.println("It's in there");
		else
			System.out.println("It's not in there");
	}

	public static boolean sumOfTwo(int[] a, int k) {
		return sumOfTwo(a, k, new HashMap<Integer,Integer>());
	}

	public static boolean sumOfTwo(int[] a, int k, Map<Integer, Integer> map) {
		for (int i = 0; i < a.length; i++) {
			map.put(a[i], a[i]);
		}
		for (int i = 0; i < a.length; i++)
			if (map.containsKey(k - a[i]))
				return true;
		return false;
	}
}
