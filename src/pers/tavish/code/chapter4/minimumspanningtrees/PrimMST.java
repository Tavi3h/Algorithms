package pers.tavish.code.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

// Prim算法的最小生成树
// 即时实现
public class PrimMST {

	private Edge[] edgeTo; // 如果顶点v不在树中但至少含有一条边和树相连，那么edgeTo[v]是将v和树连接的最短边
	private double[] distTo; // distTo[v] = edgeTo[v].weight()
	private boolean[] marked; // 如果v在树中则marked[v]为true
	private IndexMinPQ<Double> pq; // 有效的横切边

	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<>(G.V());
		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}

		// run from each vertex to find minimum spanning forest
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				prim(G, v);
			}
		}
	}

	// Prim算法
	private void prim(EdgeWeightedGraph G, int s) {
		distTo[s] = 0.0;
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			visit(G, v);
		}
	}

	// 将顶点v添加到树中，更新数据
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			if (marked[w]) {
				continue; // v-w is obsolete edge
			}
			if (e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if (pq.contains(w)) {
					pq.decreaseKey(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}

	/*
	 * 返回最小生成树
	 */
	public Iterable<Edge> edges() {
		Queue<Edge> mst = new Queue<>();
		for (int v = 0; v < edgeTo.length; v++) {
			Edge e = edgeTo[v];
			if (e != null) {
				mst.enqueue(e);
			}
		}
		return mst;
	}

	/*
	 * 返回图的总权重
	 */
	public double weight() {
		double weight = 0.0;
		for (Edge e : edges()) {
			weight += e.weight();
		}
		return weight;
	}

	// tinyEWG.txt
	public static void main(String[] args) {
		EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
		PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
	}
	/*
		1-7 0.19000
		0-2 0.26000
		2-3 0.17000
		4-5 0.35000
		5-7 0.28000
		6-2 0.40000
		0-7 0.16000
		1.81000
	 */
}
