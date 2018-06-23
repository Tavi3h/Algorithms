package pers.tavish.ex.chapter4.directedgraphs.exercises;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import pers.tavish.code.chapter4.directedgraphs.Digraph;
import pers.tavish.code.chapter4.directedgraphs.KosarajuSCC;

// 练习题4.2.16
public class Ex4216 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Digraph G = new Digraph(5);
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(0, 3);
		G.addEdge(0, 4);
		
		KosarajuSCC scc = new KosarajuSCC(G);

		// number of connected components
		int m = scc.count();
		StdOut.println(m + " strong components");

		// compute list of vertices in each strong component
		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
		for (int i = 0; i < m; i++) {
			components[i] = new Queue<Integer>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[scc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < m; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}
