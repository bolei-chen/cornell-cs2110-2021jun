package class_06_23;

public class SelectionSort {
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = array[i];
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < min) {
					min = array[j];
					minIndex = j;
				}
			}
			int temp = 0;
			temp = array[i];
			array[i] = min;
			array[minIndex] = temp;
		}
	}

	public static void print(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int numbers[] = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			numbers[i] = Integer.parseInt(args[i]);
		}
		print(numbers);
		selectionSort(numbers);
		print(numbers);

	}

}