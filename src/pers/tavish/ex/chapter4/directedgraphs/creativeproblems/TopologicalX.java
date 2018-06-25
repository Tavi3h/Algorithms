package pers.tavish.ex.chapter4.directedgraphs.creativeproblems;

import edu.princeton.cs.algs4.Queue;
import pers.tavish.code.chapter4.directedgraphs.Digraph;

// 提高题4.2.30
public class TopologicalX {
	private Queue<Integer> order; // vertices in topological order
	private int[] ranks; // ranks[v] = order where vertex v appears in order

	/**
	 * Determines whether the digraph {@code G} has a topological order and, if so,
	 * finds such a topological order.
	 * 
	 * @param G
	 *            the digraph
	 */
	public TopologicalX(Digraph G) {

		// indegrees of remaining vertices
		int[] indegree = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			indegree[v] = G.indegree(v);
		}

		// initialize
		ranks = new int[G.V()];
		order = new Queue<>();
		int count = 0;

		// initialize queue to contain all vertices with indegree = 0
		Queue<Integer> queue = new Queue<>();
		for (int v = 0; v < G.V(); v++) {
			if (indegree[v] == 0) {
				queue.enqueue(v);
			}
		}

		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			order.enqueue(v);
			ranks[v] = count++;
			for (int w : G.adj(v)) {
				indegree[w]--;
				if (indegree[w] == 0) {
					queue.enqueue(w);
				}
			}
		}

		// there is a directed cycle in subgraph of vertices with indegree >= 1.
		if (count != G.V()) {
			order = null;
		}

	}

	/**
	 * Returns a topological order if the digraph has a topologial order, and
	 * {@code null} otherwise.
	 * 
	 * @return a topological order of the vertices (as an interable) if the digraph
	 *         has a topological order (or equivalently, if the digraph is a DAG),
	 *         and {@code null} otherwise
	 */
	public Iterable<Integer> order() {
		return order;
	}

	/**
	 * Does the digraph have a topological order?
	 * 
	 * @return {@code true} if the digraph has a topological order (or equivalently,
	 *         if the digraph is a DAG), and {@code false} otherwise
	 */
	public boolean hasOrder() {
		return order != null;
	}

	/**
	 * The the rank of vertex {@code v} in the topological order; -1 if the digraph
	 * is not a DAG
	 *
	 * @param v
	 *            vertex
	 * @return the position of vertex {@code v} in a topological order of the
	 *         digraph; -1 if the digraph is not a DAG
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public int rank(int v) {
		validateVertex(v);
		return hasOrder() ? ranks[v] : -1;
	}

	private void validateVertex(int v) {
		int V = ranks.length;
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
}
