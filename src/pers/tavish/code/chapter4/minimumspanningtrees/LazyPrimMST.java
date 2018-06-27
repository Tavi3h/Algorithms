package pers.tavish.code.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

// Prim算法的最小生成树
// 延时实现
public class LazyPrimMST {

	private double weight; // 最小生成树的总权重
	private Queue<Edge> mst; // 最小生成树的边
	private boolean[] marked; // 最小生成树的顶点
	private MinPQ<Edge> pq; // 横切边（包括失效的边）

	/*
	 * 构造函数，读取一幅加权图
	 */
	public LazyPrimMST(EdgeWeightedGraph G) {
		mst = new Queue<>();
		pq = new MinPQ<>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) { // run Prim from all vertices to
			if (!marked[v]) {
				prim(G, v); // get a minimum spanning forest
			}
		}
	}

	// Prim算法
	private void prim(EdgeWeightedGraph G, int s) {
		visit(G, s);
		while (!pq.isEmpty()) { // better to stop when mst has V-1 edges
			Edge e = pq.delMin(); // 从pq中得到权重最小的边
			int v = e.either(), w = e.other(v); // two endpoints
			if (marked[v] && marked[w]) {
				continue; // 跳过失效的边
			}
			mst.enqueue(e); // 将边添加到树中
			weight += e.weight();
			if (!marked[v]) {
				visit(G, v); // v becomes part of tree
			}
			if (!marked[w]) {
				visit(G, w); // w becomes part of tree
			}
		}
	}

	// 标记顶点v并将所有连接v和未标记顶点的边加入pq
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			if (!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}

	/*
	 * 返回最小生成树
	 */
	public Iterable<Edge> edges() {
		return mst;
	}

	/*
	 * 返回最小生成树的总权重
	 */
	public double weight() {
		return weight;
	}

	// tinyEWG.txt
	public static void main(String[] args) {
		EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
		LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
	}
	/*
		0-7 0.16000
		1-7 0.19000
		0-2 0.26000
		2-3 0.17000
		5-7 0.28000
		4-5 0.35000
		6-2 0.40000
		1.81000
	 */
}
