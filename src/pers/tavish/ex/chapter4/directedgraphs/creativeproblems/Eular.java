package pers.tavish.ex.chapter4.directedgraphs.creativeproblems;

import edu.princeton.cs.algs4.Queue;
import pers.tavish.code.chapter4.directedgraphs.Digraph;
import pers.tavish.code.chapter4.directedgraphs.KosarajuSCC;

// 提高题4.2.20
public class Eular {
	public static Iterable<Integer> eular(Digraph G) {
		// 检查图G是否是连通的
		if (new KosarajuSCC(G).count() != 1) {
			throw new IllegalArgumentException("This directed graph is not strongly connected...");
		}

		int V = G.V();
		boolean[] marked = new boolean[V];
		Queue<Integer> pre = new Queue<>();
		
		for (int v = 0; v < V; v++) {
			if (!marked[v]) {
				dfs(G, v, marked, pre);
			}
		}
		pre.enqueue(0);
		return pre;
	}

	private static void dfs(Digraph G, int v, boolean[] marked, Queue<Integer> pre) {
		// 检查每一个顶点的出度和入度
		if (G.indegree(v) != G.outdegree(v)) {
			throw new IllegalArgumentException("Indegree or outdegree of vertice " + v + " is not 1...");
		}
		pre.enqueue(v);
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w, marked, pre);
			}
		}
	}

	public static void main(String[] args) {
		Digraph G = new Digraph(6);
		G.addEdge(0, 2);
		G.addEdge(2, 1);
		G.addEdge(1, 5);
		G.addEdge(5, 4);
		G.addEdge(4, 3);
		G.addEdge(3, 0);

		for (int i : eular(G)) {
			System.out.print(i + " "); // 0 2 1 5 4 3 0
		}
	}
}
