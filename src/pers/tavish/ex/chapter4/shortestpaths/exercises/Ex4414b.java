package pers.tavish.ex.chapter4.shortestpaths.exercises;

import edu.princeton.cs.algs4.In;
import pers.tavish.code.chapter4.shortestpaths.DijkstraSP;
import pers.tavish.code.chapter4.shortestpaths.DirectedEdge;
import pers.tavish.code.chapter4.shortestpaths.EdgeWeightedDigraph;

// 练习题4.4.14，尝试1
public class Ex4414b {
	public static void main(String[] args) {

		// 直接读取tinyEWDn.txt
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
		
		// 使用Dijkstra算法
		int s = 6;
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
