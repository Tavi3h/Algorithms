package pers.tavish.ex.chapter4.experiments;

import edu.princeton.cs.algs4.StdRandom;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;

// 提高题4.1.38
public class ErdosRenyiGraph {
	public static void main(String[] args) {
		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		
		Graph G = new Graph(V);
		
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			G.addEdge(v, w);
		}
	}
}

