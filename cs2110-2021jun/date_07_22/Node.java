package date_07_22;

class Node implements Comparable<Node> {
	public Integer key;
	public Node left;
	public Node right;
	public String color;

	public Node(Integer key) {
		this.key = key;
		left = null;
		right = null;
	}

	public String toString() {
		return key + "";
	}

	public int compareTo(Node other) {
		return key.compareTo(other.key);
	}
}
