package pers.tavish.code.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

// 有向图的广度优先搜索查找图中的路径
public class DirectedBFP {
	
	private static final int INFINITY = Integer.MAX_VALUE; // initial distance between s and v
	private boolean[] marked; // marked[v] = is there an s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private int[] distTo; // distTo[v] = number of edges shortest s-v path
	
	/*
	 * 构造函数：计算起始点s与图G中每个顶点的最短路径
	 */
	public DirectedBFP(Digraph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		validateVertex(s);
		bfs(G, s);
	}
	
	/*
	 * 判断起点s与v之间是否存在路径
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}
	
	/*
	 * 返回起点s与v之间最短路径的边数
	 */
	public int distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}
	
	/*
	 * 返回起点s与v之间的最短路径
	 */
	public Iterable<Integer> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<Integer> path = new Stack<>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		return path;
	}
	
	// 广度优先算法
	private void bfs(Digraph G, int s) {
		Queue<Integer> q = new Queue<>();
		for (int v = 0; v < G.V(); v++) {
			distTo[v] = INFINITY;
		}
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
	
	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
	
	public static void main(String[] args) {

		Digraph G = new Digraph(12);
		G.addEdge(8, 4);
		G.addEdge(2, 3);
		G.addEdge(1, 11);
		G.addEdge(0, 6);
		G.addEdge(3, 6);
		G.addEdge(10, 3);
		G.addEdge(7, 11);
		G.addEdge(7, 8);
		G.addEdge(11, 8);
		G.addEdge(2, 0);
		G.addEdge(6, 2);
		G.addEdge(5, 2);
		G.addEdge(5, 10);
		G.addEdge(5, 0);
		G.addEdge(8, 1);
		G.addEdge(4, 1);

		DirectedBFP bfp = new DirectedBFP(G, 0);

		for (int v = 0; v < G.V(); v++) {
			System.out.print(0 + " to " + v + ": ");
			if (bfp.hasPathTo(v)) {
				for (int x : bfp.pathTo(v)) {
					if (x == 0) {
						System.out.print(x);
					} else {
						System.out.print("-" + x);
					}
				}
				System.out.print(" dist " + bfp.distTo(v));
			}
			System.out.println();
		}
	}
}
