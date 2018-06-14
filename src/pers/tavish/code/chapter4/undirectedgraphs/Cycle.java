package pers.tavish.code.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

// 使用深度优先搜索处理图，判断图G是否为无环图
public class Cycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	public Cycle(Graph G) {
		if (hasSelfLoop(G)) {
			return;
		}
		if (hasParallelEdges(G)) {
			return;
		}
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, -1, v);
			}
		}
	}

	/*
	 * 返回图G是否具有环
	 */
	public boolean hasCycle() {
		return cycle != null;
	}

	/*
	 * 返回图中的一个环
	 */
	public Iterable<Integer> cycle() {
		return cycle;
	}

	// 深度优先算法
	private void dfs(Graph G, int u, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {

			// short circuit if cycle already found
			if (cycle != null) {
				return;
			}

			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, v, w);
			}

			// check for cycle (but disregard reverse of edge leading to v)
			else if (w != u) {
				cycle = new Stack<>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
	}

	// 判断图G是否有自环
	private boolean hasSelfLoop(Graph G) {
		for (int v = 0; v < G.V(); v++) {
			for (int w : G.adj(v)) {
				if (v == w) {
					cycle = new Stack<>();
					cycle.push(v);
					cycle.push(v);
					return true;
				}
			}
		}
		return false;
	}

	// 判断图G是否有平行边
	private boolean hasParallelEdges(Graph G) {
		marked = new boolean[G.V()];

		for (int v = 0; v < G.V(); v++) {

			// check for parallel edges incident to v
			for (int w : G.adj(v)) {
				if (marked[w]) {
					cycle = new Stack<>();
					cycle.push(v);
					cycle.push(w);
					cycle.push(v);
					return true;
				}
				marked[w] = true;
			}

			// reset so marked[v] = false for all v
			for (int w : G.adj(v)) {
				marked[w] = false;
			}
		}
		return false;
	}
}
