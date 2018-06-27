package pers.tavish.code.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

// 最小生成树的Kruskal算法
public class KruskalMST {

	private double weight; // 图的总权重
	private Queue<Edge> mst; // 最小生成树的所有边

	public KruskalMST(EdgeWeightedGraph G) {

		mst = new Queue<>();
		MinPQ<Edge> pq = new MinPQ<>();
		for (Edge e : G.edges()) {
			pq.insert(e);
		}

		// run greedy algorithm
		UF uf = new UF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (!uf.connected(v, w)) { // v-w does not create a cycle
				uf.union(v, w); // merge v and w components
				mst.enqueue(e); // add edge e to mst
				weight += e.weight();
			}
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

	public double weight() {
		return weight;
	}

	// tinyEWG.txt
	public static void main(String[] args) {
		KruskalMST mst = new KruskalMST(new EdgeWeightedGraph(new In(args[0])));
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.printf("%.5f\n", mst.weight());
    }
    
    /*
     	0-7 0.16000
		2-3 0.17000
		1-7 0.19000
		0-2 0.26000
		5-7 0.28000
		4-5 0.35000
		6-2 0.40000
		1.81000
     */
}
