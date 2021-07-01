package class_06_30;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformance {

	public static long grow(List list, int finalSize) {
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < finalSize; i++) {
			list.add(list.size() / 2, i);
		}
		long end = System.currentTimeMillis();
		return end - start;

	}
	public static void main(String[] args) {
		
		for ( int finalSize = 1000; finalSize < 30000; finalSize += 1000) {
			//List<Integer> list = new ArrayList<>();
			List<Integer> list = new LinkedList<>();
			long duration = grow(list, finalSize);
			System.out.println(finalSize + "\t" + duration);
		}
	}

}
