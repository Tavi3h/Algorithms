package pers.tavish.ex.chapter4.shortestpaths.exercises;

import edu.princeton.cs.algs4.In;
import pers.tavish.code.chapter4.shortestpaths.DijkstraAllPairsSP;
import pers.tavish.code.chapter4.shortestpaths.EdgeWeightedDigraph;

// 练习题4.4.8
public class Ex448 {
	public static void main(String[] args) {
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0])); // 读取图
		DijkstraAllPairsSP dapsp = new DijkstraAllPairsSP(G);
		
		int V = G.V();
		double d = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				double dist = dapsp.dist(i, j);
				if (i == j || dist == Double.POSITIVE_INFINITY) {
					continue;
				}
				d = dist > d ? dist : d;
			}
		}
		System.out.println(d);
	}
}
