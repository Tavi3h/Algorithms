package pers.tavish.ex.chapter4.shortestpaths.exercises;

import pers.tavish.code.chapter4.shortestpaths.DijkstraSP;
import pers.tavish.code.chapter4.shortestpaths.DirectedEdge;
import pers.tavish.code.chapter4.shortestpaths.EdgeWeightedDigraph;

// 练习题4.4.5
public class Ex445 {
	public static void main(String[] args) {
		// 改变tinyEWD.txt中0->2的方向
		/*
		   	8
			15
			4 5 0.35
			5 4 0.35
			4 7 0.37
			5 7 0.28
			7 5 0.28
			5 1 0.32
			0 4 0.38
			2 0 0.26
			7 3 0.39
			1 3 0.29
			2 7 0.34
			6 2 0.40
			3 6 0.52
			6 0 0.58
			6 4 0.93
		 */
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
		G.addEdge(new DirectedEdge(4, 5, 0.35));
		G.addEdge(new DirectedEdge(5, 4, 0.35));
		G.addEdge(new DirectedEdge(4, 7, 0.37));
		G.addEdge(new DirectedEdge(5, 7, 0.28));
		G.addEdge(new DirectedEdge(7, 5, 0.28));
		G.addEdge(new DirectedEdge(5, 1, 0.32));
		G.addEdge(new DirectedEdge(0, 4, 0.38));
		G.addEdge(new DirectedEdge(2, 0, 0.26));
		G.addEdge(new DirectedEdge(7, 3, 0.39));
		G.addEdge(new DirectedEdge(1, 3, 0.29));
		G.addEdge(new DirectedEdge(2, 7, 0.34));
		G.addEdge(new DirectedEdge(6, 2, 0.40));
		G.addEdge(new DirectedEdge(3, 6, 0.52));
		G.addEdge(new DirectedEdge(6, 0, 0.58));
		G.addEdge(new DirectedEdge(6, 4, 0.93));
		
		int s = 2;
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
