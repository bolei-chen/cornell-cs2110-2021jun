package probelm_set_2;

import java.util.Random;

enum Alg {
	SELECTION, INSERTION, MERGE_ASSERT, QUICK_ASSERT, MERGE, QUICK
};

public class Comparison {
	static Random r = new Random();

	/*
	 * Generates an array of 'numOfInts' integers. This array initially contains an
	 * ascending series of values but based on the value of 'disorderPercentage',
	 * this order is reduced in a controlled manner.
	 * 
	 * EXAMPLE: Suppose 'disorderPercentage' is 3 and 'numOfInts' is 500. Then
	 * (3/100)*500=15 pairs of values within an initially sorted array are swapped.
	 */
	public static Integer[] genNums(int numOfInts, int disorderPercentage) {
		Integer[] temp = new Integer[numOfInts];
		for (int i = 0; i < numOfInts; i++)
			temp[i] = i;
		int numOfSwaps = (int) ((disorderPercentage / 100.0) * numOfInts);
		for (int i = 0; i < numOfSwaps; i++) {
			int j = r.nextInt(numOfInts);
			int k = r.nextInt(numOfInts);
			int t = temp[j];
			temp[j] = temp[k];
			temp[k] = t;
		}
		return temp;
	}

	/*
	 * This method creates a single data point for the empirical data collection
	 * process. For the given algorithm, given array size (i.e. N) and, given
	 * disorder percentage, the returned duration is the average of numOfTrials
	 * trials.
	 */
	public static double dataPoint(Alg alg, //
			int N, int disorderPercentage, //
			int numOfTrials) {
		long duration = 0;
		for (int i = 0; i < numOfTrials; i++) {
			Integer nums[] = genNums(N, disorderPercentage);
			long start = System.currentTimeMillis();
			if (alg == Alg.SELECTION){
				Selection.sort(nums);
			} else if (alg == Alg.INSERTION) {
				Insertion.sort(nums);
			} else if (alg == Alg.QUICK) {
				Quick.sort(nums);
			} else if (alg == Alg.MERGE) {
				Merge.sort(nums);
			} else if (alg == Alg.MERGE_ASSERT) {
				MergeAssert.sort(nums);
			} else if (alg == Alg.QUICK_ASSERT) {
				QuickAssert.sort(nums);
			} else {
				System.err.println("We should never see this");
			}
			long end = System.currentTimeMillis();
			duration = duration + (end - start);
		}
		return duration / (double) numOfTrials;
	}

	/*
	 * This method drives the empirical data collection process. The caller
	 * specifies the array sizes to be tried as well as the extent of disorder added
	 * to the arrays.
	 */
	public static void experiment(Alg alg, //
			int minN, int maxN, int stepN, //
			int minP, int maxP, int stepP) {
		for (int N = minN; N < maxN; N = N + stepN) {
			for (int p = minP; p < maxP; p = p + stepP) {
				double duration = dataPoint(alg, N, p, 100);
				System.out.println(alg + "\t" + N + "\t" + p + "\t" + duration);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Alg\tN\tp\td");
		experiment(Alg.MERGE_ASSERT, 100, 1000, 100, 0, 10, 1);
		experiment(Alg.QUICK_ASSERT, 100, 1000, 100, 0, 10, 1);
	}
}
