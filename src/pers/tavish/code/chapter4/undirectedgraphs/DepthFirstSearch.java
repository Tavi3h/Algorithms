package pers.tavish.code.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.In;

// 深度优先搜索
public class DepthFirstSearch {

	private boolean[] marked; // marked[v]表示是否存在s-v的路径

	private int count; // 与s链接的顶点数

	/*
	 * 构造函数：计算图G中与起点s相连的顶点
	 */
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		validateVertex(s);
		dfs(G, s);
	}

	/*
	 * 顶点s与v之间是否存在路径
	 */
	public boolean marked(int v) {
		validateVertex(v);
		return marked[v];
	}

	/*
	 * 返回与s连接的顶点数
	 */
	public int count() {
		return count;
	}

	// 从v开始进行深度优先搜索
	private void dfs(Graph G, int v) {
		count++;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
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
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		DepthFirstSearch search = new DepthFirstSearch(G, s);
		for (int v = 0; v < G.V(); v++) {
			if (search.marked(v)) {
				System.out.print(v + " ");
			}
		}
		System.out.println();
		if (search.count() != G.V()) {
			System.out.println("NOT connected");
		} else {
			System.out.println("connected");
		}
		
	}

}
