package date_07_27;

import edu.princeton.cs.algs4.Graph;

public class HelloGraphs {
	public static void main(String[] args) {
		int V = 10;
		Graph g = new Graph(V);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(1, 3);
		g.addEdge(5, 7);
		g.addEdge(5, 8);
		for (int i = 0; i < V; i++) {
			System.out.print(i + ": ");
			for (int w : g.adj(i))
				System.out.print(w + " " ) ;
			System.out.println();
		}
	}
}
