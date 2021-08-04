package date_07_22;

import java.util.PriorityQueue;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BSTInteger {
	Node root = null;

	public BSTInteger() {
	}

	public BSTInteger(Integer nums[]) {
		for (int i = 0; i < nums.length; i++) {
			insert(nums[i]);
		}
	}

	public void insert(Integer k) {
		if (root == null)
			root = new Node(k);
		else
			insert(k, root);
	}

	private void insert(Integer k, Node n) {
		if (k.compareTo(n.key) < 0) {
			if (n.left == null)
				n.left = new Node(k);
			else
				insert(k, n.left);
		} else if (k.compareTo(n.key) > 0) {
			if (n.right == null)
				n.right = new Node(k);
			else
				insert(k, n.right);
		}
	}

	public Queue<Node> traverseR() {
		Queue<Node> traversal = new Queue<>();
		traverseR(traversal, root);
		return traversal;
	}

	private void traverseR(Queue<Node> traversal, Node node) {
		if (node != null) {
			traverseR(traversal, node.left);
			traverseR(traversal, node.right);
			traversal.enqueue(node);
		}
	}

	public static String decToHex(int i, int N) {
		double percentageDone = i / (double) N;
		String hexCode = Integer.toString((int) (percentageDone * 255.0), 16);
		if (hexCode.length() < 2)
			return "0" + hexCode;
		else
			return hexCode;
	}

	public static void display(Queue<Node> traversal) {
		// Header lines for graphviz
		System.out.println("digraph d {");
		System.out.println("\tnode[style=filled]");

		// Listing of the nodes in the tree
		int i = 0;
		int N = traversal.size();
		for (Node n : traversal) {
			String red = decToHex(N - i, N);
			String blue = decToHex(i, N);
			System.out.println("\t" + n + " [color=\"#" + red + "00" + blue + "\"]");
			i++;
		}

		// Listing of the edges/connections in the tree
		for (Node n : traversal) {
			if (n.left != null)
				System.out.println("\t" + n + " -> " + n.left.key + " [label=L]");
			if (n.right != null)
				System.out.println("\t" + n + " -> " + n.right.key + " [label=R]");
		}

		// Last line for the graphviz rendered tree
		System.out.println("}");
	}

	public static void displayNG(Queue<Node> traversal) {
		// Header stuff to start a graphviz rendered tree
		System.out.println("digraph d {");
		System.out.println("\tnode[style=filled]");

		PriorityQueue<Node> pq = new PriorityQueue<>();

		// Listing of the nodes in the tree
		int i = 0;
		int N = traversal.size();
		for (Node n : traversal) {
			String red = decToHex(N - i, N);
			String blue = decToHex(i, N);
			String color = "\"#" + red + "00" + blue + "\"";
			n.color = color;
			pq.add(n);
			i++;
		}

		while (!pq.isEmpty()) {
			Node n = pq.remove();
			System.out.println("\t" + n + " [color=" + n.color + "]");
		}

		// Listing of the edges/connections in the tree
		for (Node n : traversal) {
			if (n.left != null)
				System.out.println("\t" + n + " -> " + n.left.key + " [label=L]");
			if (n.right != null)
				System.out.println("\t" + n + " -> " + n.right.key + " [label=R]");
		}

		// Last line for the graphviz rendered tree
		System.out.println("}");
	}

	public static void main(String[] args) {
		BSTInteger bst = new BSTInteger(IntegerArrays.N10);
		Queue<Node> traversal = bst.traverseR();
		displayNG(traversal);
	}
}
