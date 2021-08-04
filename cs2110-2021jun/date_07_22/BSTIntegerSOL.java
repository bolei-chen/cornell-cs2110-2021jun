package date_07_22;

import java.util.PriorityQueue;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BSTIntegerSOL {
	Node root = null;

	public BSTIntegerSOL() {
	}

	public BSTIntegerSOL(Integer nums[]) {
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

	public Queue<Node> traverseS() {
		Stack<Node> nodesWeAreYetToGo = new Stack<>();
		Queue<Node> traversal = new Queue<>();
		nodesWeAreYetToGo.push(root);
		while (!nodesWeAreYetToGo.isEmpty()) {
			Node node = nodesWeAreYetToGo.pop();
			traversal.enqueue(node);
			if (node.right != null)
				nodesWeAreYetToGo.push(node.right);
			if (node.left != null)
				nodesWeAreYetToGo.push(node.left);
		}
		return traversal;
	}

	public Queue<Node> traverseQ() {
		Queue<Node> nodesWeAreYetToGo = new Queue<>();
		Queue<Node> traversal = new Queue<>();
		nodesWeAreYetToGo.enqueue(root);
		while (!nodesWeAreYetToGo.isEmpty()) {
			Node node = nodesWeAreYetToGo.dequeue();
			traversal.enqueue(node);
			if (node.right != null)
				nodesWeAreYetToGo.enqueue(node.right);
			if (node.left != null)
				nodesWeAreYetToGo.enqueue(node.left);
		}
		return traversal;
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
			if (n.right != null)
				System.out.println("\t" + n + " -> " + n.right.key + " [label=R]");
			if (n.left != null)
				System.out.println("\t" + n + " -> " + n.left.key + " [label=L]");
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
			if (n.right != null)
				System.out.println("\t" + n + " -> " + n.right.key);
			if (n.left != null)
				System.out.println("\t" + n + " -> " + n.left.key);
		}

		// Last line for the graphviz rendered tree
		System.out.println("}");
	}

	public static void main(String[] args) {
		BSTIntegerSOL bst = new BSTIntegerSOL(IntegerArrays.N10);
		Queue<Node> traversal = bst.traverseQ();
		displayNG(traversal);
	}
}
