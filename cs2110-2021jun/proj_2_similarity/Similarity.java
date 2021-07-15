package proj_2_similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
	 * This mode of the program assumes that the numbering of the movies are
	 * according to the rankings of a person X who is not in the input file. The
	 * goal is to consider all N rows of the input and decide which row is most
	 * consistent with the rankings of X. Stated algorithmically, the goal is the
	 * figure out which row of the N rows of input has the minimum number of
	 * inversions.
	 */
	private static void modeImplicit(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
	}

	/*
	 * This mode of the program assumes that person X is in position 0 (i.e. the
	 * first line of the input file) and that the numbering of the movies is not
	 * based on the rankings of person X.
	 */
	private static void modeFirst(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
	}

	/*
	 * Given N names and N rankings (both indexed from 0 to N-1), this mode of the
	 * program determines which two people are most consistent with each other.
	 */
	private static void modeAny(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
	}

	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			String mode = args[0];
			String fileName = args[1];
			List<String> names = new ArrayList<>();
			List<List<Integer>> rankings = new ArrayList<List<Integer>>();
			readRankings(Defaults.pkg + File.separatorChar + fileName, names, rankings);
			System.out.println();

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
