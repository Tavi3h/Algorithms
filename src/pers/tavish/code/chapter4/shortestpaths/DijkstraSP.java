package pers.tavish.code.chapter4.shortestpaths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

// 最短路径的Dijkstra算法
public class DijkstraSP {

	private double[] distTo; // distTo[v] = distance of shortest s->v path
	private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
	private IndexMinPQ<Double> pq; // priority queue of vertices

	/*
	 * 构造函数，读入加权有向图G和起点s，计算最短路径
	 */
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		// 判断边的权重是否有小于0
		for (DirectedEdge e : G.edges()) {
			if (e.weight() < 0) {
				throw new IllegalArgumentException("edge " + e + " has negative weight");
			}
		}

		// 初始化数组
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];

		validateVertex(s);

		// 初始化起点到各个顶点的距离
		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;

		// relax vertices in order of distance from s
		pq = new IndexMinPQ<>(G.V());
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdge e : G.adj(v)) {
				relax(e);
			}
		}
	}

	/*
	 * 返回起点s到顶点v的距离
	 */
	public double distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	/*
	 * 判断是否存在从起点s到v的距离
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/*
	 * 返回起点s到v的最短路径
	 */
	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<DirectedEdge> path = new Stack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

	// 松弛加权有向边e
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			} else {
				pq.insert(w, distTo[w]);
			}
		}
	}

	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}

	public static void main(String[] args) {

		// 读取tinyEWD.txt
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));

		int s = 0;
		
		DijkstraSP dsp = new DijkstraSP(G, 0);

		for (int t = 0; t < G.V(); t++) {
			if (dsp.hasPathTo(t)) {
				System.out.printf("%d to %d (%.2f)  ", s, t, dsp.distTo(t));
				for (DirectedEdge e : dsp.pathTo(t)) {
					System.out.print(e + "   ");
				}
				System.out.println();
			} else {
				System.out.printf("%d to %d         no path\n", s, t);
			}
		}
	}
	/*
	 	0 to 0 (0.00)  
		0 to 1 (1.05)  0->4  0.38   4->5  0.35   5->1  0.32   
		0 to 2 (0.26)  0->2  0.26   
		0 to 3 (0.99)  0->2  0.26   2->7  0.34   7->3  0.39   
		0 to 4 (0.38)  0->4  0.38   
		0 to 5 (0.73)  0->4  0.38   4->5  0.35   
		0 to 6 (1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   
		0 to 7 (0.60)  0->2  0.26   2->7  0.34   
	 */
}
