package pers.tavish.code.chapter4.shortestpaths;

import edu.princeton.cs.algs4.Stack;

// 找出加权有向图中的有向环
public class EdgeWeightedDirectedCycle {
	private boolean[] marked; // marked[v] = has vertex v been marked?
	private DirectedEdge[] edgeTo; // edgeTo[v] = previous edge on path to v
	private boolean[] onStack; // onStack[v] = is vertex on the stack?
	private Stack<DirectedEdge> cycle; // directed cycle (or null if no such cycle)

	public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
	}

	private void dfs(EdgeWeightedDigraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();

			// short circuit if directed cycle found
			if (cycle != null)
				return;

			// found new vertex, so recur
			else if (!marked[w]) {
				edgeTo[w] = e;
				dfs(G, w);
			}

			// trace back directed cycle
			else if (onStack[w]) {
				cycle = new Stack<DirectedEdge>();

				DirectedEdge f = e;
				while (f.from() != w) {
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				cycle.push(f);

				return;
			}
		}
		onStack[v] = false;
	}
	
    public boolean hasCycle() {
        return cycle != null;
    }
    
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
