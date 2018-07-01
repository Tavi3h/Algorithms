package pers.tavish.code.chapter4.shortestpaths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import pers.tavish.code.chapter4.directedgraphs.Topological;

// 无环加权优先图的最长路径
public class AcyclicLP {
	private double[] distTo; // distTo[v] = distance of shortest s->v path
	private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path

	public AcyclicLP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];

		validateVertex(s);

		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.NEGATIVE_INFINITY;
		}
		distTo[s] = 0.0;

		// visit vertices in toplogical order
		Topological topological = new Topological(G);
		if (!topological.isDAG()) {
			throw new IllegalArgumentException("Digraph is not acyclic.");
		}
		for (int v : topological.order()) {
			for (DirectedEdge e : G.adj(v)) {
				relax(e);
			}
		}
	}

	public double distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<DirectedEdge> path = new Stack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] < distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}

	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}

	public static void main(String[] args) {
		
		// 读取tinyEWDAG.txt
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));

		int s = 5;
		// find shortest path from s to each other vertex in DAG
		AcyclicLP sp = new AcyclicLP(G, s);
		for (int v = 0; v < G.V(); v++) {
			if (sp.hasPathTo(v)) {
				System.out.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
				for (DirectedEdge e : sp.pathTo(v)) {
					System.out.print(e + "   ");
				}
				System.out.println();
			} else {
				System.out.printf("%d to %d         no path\n", s, v);
			}
		}
	}
	/*
		5 to 0 (2.44)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   4->0  0.38   
		5 to 1 (0.32)  5->1  0.32   
		5 to 2 (2.77)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   4->7  0.37   7->2  0.34   
		5 to 3 (0.61)  5->1  0.32   1->3  0.29   
		5 to 4 (2.06)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   
		5 to 5 (0.00)  
		5 to 6 (1.13)  5->1  0.32   1->3  0.29   3->6  0.52   
		5 to 7 (2.43)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   4->7  0.37  
	 */
}
