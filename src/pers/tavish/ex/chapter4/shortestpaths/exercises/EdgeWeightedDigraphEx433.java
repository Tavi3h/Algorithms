package pers.tavish.ex.chapter4.shortestpaths.exercises;

import pers.tavish.code.chapter4.shortestpaths.DirectedEdge;

// 练习题4.3.3
public class EdgeWeightedDigraphEx433 {
	
	private final int V; // 顶点总数
	private int E; // 边的总数
	private Double[][] weight;
	
	public EdgeWeightedDigraphEx433(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		}
		this.V = V;
		weight = new Double[V][V];
		this.E = 0;
	}
	
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		weight[v][w] = e.weight();
		E++;
	}
	
	public int V() {
		return V;
	}

	public int E() {
		return E;
	}
}
