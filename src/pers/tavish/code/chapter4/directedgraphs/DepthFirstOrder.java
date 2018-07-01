package pers.tavish.code.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import pers.tavish.code.chapter4.shortestpaths.DirectedEdge;
import pers.tavish.code.chapter4.shortestpaths.EdgeWeightedDigraph;

// 有向图中基于深度优先搜索的顶点排序
public class DepthFirstOrder {
	private boolean[] marked;
	private int[] pre; // pre[v] = preorder number of v
	private int[] post; // post[v] = postorder number of v
	private Queue<Integer> preorder; // 所有顶点的前序排列
	private Queue<Integer> postorder; // 所有顶点的后序排列
	private int preCounter; // counter or preorder numbering
	private int postCounter; // counter for postorder numbering

	/*
	 * 接受有向图
	 */
	public DepthFirstOrder(Digraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		postorder = new Queue<>();
		preorder = new Queue<>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
	}

	/*
	 * 接受加权有向图
	 */
	public DepthFirstOrder(EdgeWeightedDigraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		postorder = new Queue<>();
		preorder = new Queue<>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}

	private void dfs(EdgeWeightedDigraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}

	public int pre(int v) {
		validateVertex(v);
		return pre[v];
	}

	public Iterable<Integer> pre() {
		return preorder;
	}

	public int post(int v) {
		validateVertex(v);
		return post[v];
	}

	public Iterable<Integer> post() {
		return postorder;
	}

	public Iterable<Integer> reversePost() {
		Stack<Integer> reverse = new Stack<Integer>();
		for (int v : postorder)
			reverse.push(v);
		return reverse;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
}
