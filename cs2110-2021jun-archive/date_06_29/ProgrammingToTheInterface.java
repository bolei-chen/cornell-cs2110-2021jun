package date_06_29;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProgrammingToTheInterface {
	
	
	public static void main(String[] args) {
		List<Integer> listA = new ArrayList<>();
		listA.add(10);
		listA.add(20);
		listA.add(30);
		listA.add(40);
		display(listA);
		
		List<Integer> listL = new LinkedList<>();
		listL.add(10);
		listL.add(20);
		listL.add(30);
		listL.add(40);
		display(listL);
	}

	private static void display(List<Integer> list) {
		for (Integer i: list) {
			System.out.print(i + " ");
		System.out.println();
		}
	}

}
