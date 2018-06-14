package pers.tavish.code.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // marked[v] = is there an s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private int[] distTo; // distTo[v] = number of edges shortest s-v path

	/*
	 * 构造函数：计算起始点s与图G中每个顶点的最短路径
	 */
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		validateVertex(s);
		bfs(G, s);
	}

	/*
	 * 判断起始点与v之间是否存在路径
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}

	/*
	 * 返回起始点与v之间最短路径的边数
	 */
	public int distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	/*
	 * 返回起始点与v之间的最短路径
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

	// 广度优先搜索查找图中的路径
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
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
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

}
