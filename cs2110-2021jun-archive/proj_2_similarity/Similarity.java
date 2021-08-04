package proj_2_similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Similarity {

	/*
	 * Reads the specified file of N lines and returns a list of N names and a list
	 * of N lists of rankings (each of length M). It is assumed that each of the N
	 * rows of the text file starts with a name and is followed by M integers, as
	 * shown below where N=3 and M=4 (the blank lines are added so that Eclipse's
	 * auto-formatter does not convert the example into a paragraph):
	 * 
	 * john 3 0 2 1
	 *
	 * jane 0 1 2 3
	 * 
	 * ann 1 2 3 0
	 */
	public static void readRankings(//
			String path, //
			List<String> names, //
			List<List<Integer>> rankings //
	) throws Exception {
		// Opening the file
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int numOfTokens = -1;
		while (line != null) {
			String[] tokens = line.split(" |,");
			assert tokens.length > 0;
			if (numOfTokens < 0)
				numOfTokens = tokens.length;
			else
				assert numOfTokens == tokens.length;
			names.add(tokens[0]);
			List<Integer> list = new ArrayList<>();
			for (int i = 1; i < tokens.length; i++) {
				int value = Integer.parseInt(tokens[i]);
				list.add(value);
			}
			rankings.add(list);
			line = br.readLine();
		}
	}

	/*
	 * This function will accexpt a list of Integer values as the input and return
	 * an Integer value of the inversions needed to sort the array.
	 */
	public static int getInversionCount(List<Integer> array) {
		int inversionCount = 0;
		for (int i = 0; i < array.size() - 1; i++)
			for (int j = i + 1; j < array.size(); j++)
				if (array.get(i) > array.get(j)) {
					inversionCount++;
				}

		return inversionCount;
	}

	/*
	 * This mode of the program assumes that the numbering of the movies are
	 * according to the rankings of a person X who is not in the input file. The
	 * goal is to consider all N rows of the input and decide which row is most
	 * consistent with the rankings of X. Stated algorithmically, the goal is the
	 * figure out which row of the N rows of input has the minimum number of
	 * inversions.
	 */
	public static void modeImplicit(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
		List<Integer> inversionCounts = new ArrayList<>();
		// loop over the 2d list and get the inversion count for every 1d list within.
		for (List<Integer> ranking : rankings) {
			int invertionCount = getInversionCount(ranking);
			// store the count in the list inversionCounts
			inversionCounts.add(invertionCount);
		}
		// return the index of the smallest inversion count
		int minIndex = inversionCounts.indexOf(Collections.min(inversionCounts));
		// output the name corresbonding to that index
		System.out.println(names.get(minIndex));
	}

	private static void modeFirst(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
		List<Integer> inversionCounts = new ArrayList<>();
		List<List<Integer>> rankingCopy = new ArrayList<>(rankings);
		List<String> nameCopy = new ArrayList<>(names);
		List<List<Integer>> modifiedRankings = getModifiedRanking(rankingCopy, 0);
		List<String> modifiedNames = getModifiedName(nameCopy, 0);

		// perform the solution to the mode implicit problem.
		for (int k = 0; k < modifiedRankings.size(); k++) {
			int invertionCount = getInversionCount(modifiedRankings.get(k));
			inversionCounts.add(invertionCount);
		}
		int minIndex = inversionCounts.indexOf(Collections.min(inversionCounts));
		System.out.println(modifiedNames.get(minIndex));
	}

	private static List<String> modeNth(List<String> names, List<List<Integer>> rankings, int n) {
		// ...YOUR WORK GOES HERE...
		List<Integer> inversionCounts = new ArrayList<>();
		List<List<Integer>> rankingCopy = new ArrayList<>(rankings);
		List<String> nameCopy = new ArrayList<>(names);
		List<List<Integer>> modifiedRankings = getModifiedRanking(rankingCopy, n);
		List<String> modifiedNames = getModifiedName(nameCopy, n);

		// perform the solution to the mode implicit problem.
		for (int k = 0; k < modifiedRankings.size(); k++) {
			int invertionCount = getInversionCount(modifiedRankings.get(k));
			inversionCounts.add(invertionCount);
		}
		int min = Collections.min(inversionCounts);
		int minIndex = inversionCounts.indexOf(min);
		List<String> result = new ArrayList<>(3);
		result.add(Integer.toString(min));
		result.add(names.get(n));
		result.add(modifiedNames.get(minIndex));
		return result;
	}

	private static List<List<Integer>> getModifiedRanking(List<List<Integer>> rankings, int n) {
		List<Integer> selectedList = rankings.remove(n);
		// create a map that maps every value in the first 1d list with its index
		Map<Integer, Integer> m = new TreeMap<>();
		for (Integer rank : selectedList) {
			m.put(rank, selectedList.indexOf(rank));
		}
		List<List<Integer>> rankingCopy = new ArrayList<>(rankings);
		for (int i = 0; i < rankingCopy.size(); i++) {
			List<Integer> ranking = rankingCopy.get(i);
			for (int j = 0; j < ranking.size(); j++) {
				Integer rank = ranking.get(j);
				ranking.set(j, m.get(rank));
			}
		}

		return rankingCopy;
	}

	private static List<String> getModifiedName(List<String> names, int n) {
		names.remove(n);
		return names;
	}

	/*
	 * Given N names and N rankings (both indexed from 0 to N-1), this mode of the
	 * program determines which two people are most consistent with each other.
	 */
	public static void modeAny(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
		List<List<String>> record = new ArrayList<>();
		int end = names.size();
		for (int i = 0; i < end - 1; i++) {
			record.add(modeNth(names, rankings, 0));
			names.remove(0);
			rankings.remove(0);
		}
		List<Integer> similarities = new ArrayList<>();
		for (int i = 0; i < record.size(); i++) {
			similarities.add(Integer.parseInt(record.get(i).get(0)));
		}
		int minIndex = similarities.indexOf(Collections.min(similarities));
		List<String> result = new ArrayList<>(2);
		result.add(record.get(minIndex).get(1));
		result.add(record.get(minIndex).get(2));
		Collections.sort(result);
		System.out.println(result.get(0) + " " + result.get(1));
		

	}

	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			String mode = args[0];
			String fileName = args[1];
			List<String> names = new ArrayList<>();
			List<List<Integer>> rankings = new ArrayList<List<Integer>>();
			readRankings(Defaults.pkg + File.separatorChar + fileName, names, rankings);

			if (mode.equals("implicit")) {
				modeImplicit(names, rankings);
			} else if (mode.equals("first")) {
				modeFirst(names, rankings);
			} else if (mode.equals("any")) {
				modeAny(names, rankings);
			} else {
				System.err.println("We should never see this!");
			}
		}
	}
}
