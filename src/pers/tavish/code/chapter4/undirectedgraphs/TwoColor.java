package pers.tavish.code.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.In;

// 使用深度优先搜索处理图，图G是否是二分图（双色问题）
public class TwoColor {

	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable = true;

	public TwoColor(Graph G) {
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for (int s = 0; s < G.V(); s++) {
			if (!marked[s]) {
				dfs(G, s);
			}
		}
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				color[w] = !color[v];
				dfs(G, w);
			} else if (color[w] == color[v]) {
				isTwoColorable = false;
			}
		}
	}

	public boolean isBipartite() {
		return isTwoColorable;
	}

	/*
	 * 如果可以双色处理，返回着色方案
	 */
	public boolean[] color() {
		return color;
	}

	public static void main(String[] args) {
		TwoColor tw = new TwoColor(new Graph(new In(args[0])));
		System.out.println(tw.isBipartite());
	}
}
