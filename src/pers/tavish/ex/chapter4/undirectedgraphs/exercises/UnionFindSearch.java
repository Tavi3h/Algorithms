package pers.tavish.ex.chapter4.undirectedgraphs.exercises;

import pers.tavish.code.chapter1.casestudyunionfind.QuickUnionUF;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;

// 练习题4.1.8
// 使用union-find实现4.1.2.3中Search的API
public class UnionFindSearch {

	private QuickUnionUF uf; // union-find算法实现
	private int count; // 与起点连接的顶点的数量（含起点自己）
	private int s; // 起点
	private int N; // 图G的顶点总数

	public UnionFindSearch(Graph G, int s) {
		
		this.N = G.V();
		validateVertex(s);
		this.s = s;
		
		uf = new QuickUnionUF(N);
		dfs(G, s);
	}

	/*
	 * 顶点v是否与起点s连通
	 */
	public boolean marked(int v) {
		validateVertex(v);
		return uf.connected(v, s);
	}

	/*
	 * 与起点s连通的顶点总数（包含起点s自己）
	 */
	public int count() {
		return count;
	}

	// 使用union-find实现dfs算法
	private void dfs(Graph G, int v) {
		count++;
		for (int w : G.adj(v)) {
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				dfs(G, w);
			} 
		}
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= N) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (N - 1));
		}
	}
	
	public static void main(String[] args) {
		Graph G = new Graph(5);
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(0, 3);
		G.addEdge(1, 2);
		G.addEdge(1, 3);
		G.addEdge(3, 4);
		UnionFindSearch ufs = new UnionFindSearch(G, 0);
		System.out.println(ufs.count()); // 5
		System.out.println(ufs.marked(4)); // true
	}
}
