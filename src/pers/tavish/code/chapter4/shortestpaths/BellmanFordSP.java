package pers.tavish.code.chapter4.shortestpaths;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BellmanFordSP {

	private double[] distTo; // 从起点到某个顶点的路径长度
	private DirectedEdge[] edgeTo; // 从起点到某个顶点的最后一条边
	private boolean[] onQueue; // 该顶点是否存在于队列中
	private Queue<Integer> queue; // 正在被放松的顶点
	private int cost; // relax()方法被调用的次数
	private Iterable<DirectedEdge> cycle; // edgeTo[]中是否有负权重边

	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQueue = new boolean[G.V()];
		queue = new Queue<>();

		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0d;

		queue.enqueue(s);
		onQueue[s] = true;
		while (!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.dequeue();
			onQueue[v] = false;
			relax(G, v);
		}
	}

	/*
	 * 是否存在负权重环
	 */
	public boolean hasNegativeCycle() {
		return cycle != null;
	}

	/*
	 * 返回负权重环
	 */
	public Iterable<DirectedEdge> negativeCycle() {
		return cycle;
	}

	/*
	 * 返回起点距顶点v的距离
	 */
	public double distTo(int v) {
		validateVertex(v);
		if (hasNegativeCycle()) {
			throw new UnsupportedOperationException("Negative cost cycle exists");
		}
		return distTo[v];
	}

	/*
	 * 是否存在从起点到顶点v的路径
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/*
	 * 返回从起点到顶点v的路径
	 */
	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertex(v);
		if (hasNegativeCycle()) {
			throw new UnsupportedOperationException("Negative cost cycle exists");
		}
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<DirectedEdge> path = new Stack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

	private void relax(EdgeWeightedDigraph G, int v) {
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQueue[w]) {
					queue.enqueue(w);
					onQueue[w] = true;
				}
			}
			if (cost++ % G.V() == 0) {
				findNegativeCycle();
				if (hasNegativeCycle()) {
					return; // found a negative cycle
				}
			}
		}
	}

	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
		for (int v = 0; v < V; v++) {
			if (edgeTo[v] != null) {
				spt.addEdge(edgeTo[v]);
			}
		}
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
		cycle = finder.cycle();
	}
	
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
        	throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}
