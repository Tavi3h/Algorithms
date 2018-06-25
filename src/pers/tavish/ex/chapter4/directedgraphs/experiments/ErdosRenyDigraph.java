package pers.tavish.ex.chapter4.directedgraphs.experiments;

import edu.princeton.cs.algs4.StdRandom;
import pers.tavish.code.chapter4.directedgraphs.Digraph;

// 实验题4.2.32
public class ErdosRenyDigraph {
	public static void main(String[] args) {
		
		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		
		Digraph G = new Digraph(V);
		
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			G.addEdge(v, w);
		}
	}
}
