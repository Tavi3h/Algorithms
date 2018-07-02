package pers.tavish.ex.chapter4.shortestpaths.exercises;

import pers.tavish.code.chapter4.shortestpaths.DijkstraSP;
import pers.tavish.code.chapter4.shortestpaths.DirectedEdge;
import pers.tavish.code.chapter4.shortestpaths.EdgeWeightedDigraph;

// 练习题4.4.4
public class Ex444 {
	public static void main(String[] args) {
		// 删除顶点7后的图
		/*
		 	7
			10
			4 5 0.35
			5 4 0.35
			5 1 0.32
			0 4 0.38
			0 2 0.26
			1 3 0.29
			6 2 0.40
			3 6 0.52
			6 0 0.58
			6 4 0.93
		 */
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(7);
		G.addEdge(new DirectedEdge(4, 5, 0.35));
		G.addEdge(new DirectedEdge(5, 4, 0.35));
		G.addEdge(new DirectedEdge(5, 1, 0.32));
		G.addEdge(new DirectedEdge(0, 4, 0.38));
		G.addEdge(new DirectedEdge(0, 2, 0.26));
		G.addEdge(new DirectedEdge(1, 3, 0.29));
		G.addEdge(new DirectedEdge(6, 2, 0.40));
		G.addEdge(new DirectedEdge(3, 6, 0.52));
		G.addEdge(new DirectedEdge(6, 0, 0.58));
		G.addEdge(new DirectedEdge(6, 4, 0.93));

		int s = 0;
		DijkstraSP dsp = new DijkstraSP(G, s);
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
}
