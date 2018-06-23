package pers.tavish.ex.chapter4.directedgraphs.exercises;

import edu.princeton.cs.algs4.Bag;
import pers.tavish.code.chapter4.directedgraphs.Digraph;

// 练习题4.2.7 
public class Degress {
	
	private Digraph G;
	
	public Degress(Digraph G) {
		this.G = G;
	}
	
	public int indegree(int v) {
		return G.indegree(v);
	}
	
	public int outdegree(int v) {
		return G.outdegree(v);
	}
	
	public Iterable<Integer> sources() {
		Bag<Integer> sources = new Bag<>();
		
		int V = G.V();
		for (int i = 0; i < V; i++) {
			if (G.indegree(i) == 0) {
				sources.add(i);
			}
		}
		
		return sources;
	}
	
	public Iterable<Integer> sinks() {
		Bag<Integer> sinks = new Bag<>();
		
		int V = G.V();
		for (int i = 0; i < V; i++) {
			if (G.outdegree(i) == 0) {
				sinks.add(i);
			}
		}
		
		return sinks;
	}
	
	public boolean isMap() {
		int V = G.V();
		for (int i = 0; i < V; i++) {
			if (G.outdegree(i) != 1) {
				return false;
			}
		}
		return true;
	}
}
