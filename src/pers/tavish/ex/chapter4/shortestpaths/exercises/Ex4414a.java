package pers.tavish.ex.chapter4.shortestpaths.exercises;

import pers.tavish.code.chapter4.shortestpaths.DijkstraSP;
import pers.tavish.code.chapter4.shortestpaths.DirectedEdge;
import pers.tavish.code.chapter4.shortestpaths.EdgeWeightedDigraph;

// 练习题4.4.14，尝试1
public class Ex4414a {
	public static void main(String[] args) {
		/*
			8
			15
			4 5  0.35
			5 4  0.35
			4 7  0.37
			5 7  0.28
			7 5  0.28
			5 1  0.32
			0 4  0.38
			0 2  0.26
			7 3  0.39
			1 3  0.29
			2 7  0.34
			6 2 -1.20
			3 6  0.52
			6 0 -1.40
			6 4 -1.25
		 */
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
		// 对所有权重增加1.50
		G.addEdge(new DirectedEdge(4, 5, 1.85));
		G.addEdge(new DirectedEdge(5, 4, 1.85));
		G.addEdge(new DirectedEdge(4, 7, 1.87));
		G.addEdge(new DirectedEdge(5, 7, 1.78));
		G.addEdge(new DirectedEdge(7, 5, 1.78));
		G.addEdge(new DirectedEdge(5, 1, 1.82));
		G.addEdge(new DirectedEdge(0, 4, 1.88));
		G.addEdge(new DirectedEdge(2, 0, 1.76));
		G.addEdge(new DirectedEdge(7, 3, 1.89));
		G.addEdge(new DirectedEdge(1, 3, 1.79));
		G.addEdge(new DirectedEdge(2, 7, 1.84));
		G.addEdge(new DirectedEdge(6, 2, 0.30));
		G.addEdge(new DirectedEdge(3, 6, 2.02));
		G.addEdge(new DirectedEdge(6, 0, 0.10));
		G.addEdge(new DirectedEdge(6, 4, 0.25));
		
		// 使用Dijkstra算法
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
