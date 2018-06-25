package pers.tavish.code.chapter4.directedgraphs;

// 计算传递闭包，依赖于AdjMatrixDigraph
public class WarshallTC {
	private boolean[][] tc; // tc[v][w] = true iff path from v to w

	public WarshallTC(AdjMatrixDigraph G) {

		// initialize tc[][]
		int V = G.V();
		tc = new boolean[V][V];
		for (int v = 0; v < V; v++) {
			for (int w : G.adj(v)) {
				tc[v][w] = true;
			}
			tc[v][v] = true;
		}

		// Warshall's algorithm
		for (int i = 0; i < V; i++) {
			for (int v = 0; v < V; v++) {
				if (!tc[v][i])
					continue; // optimization
				for (int w = 0; w < V; w++) {
					if (tc[v][i] && tc[i][w])
						tc[v][w] = true;
				}
			}
		}

	}

	// is there a directed path from v to w?
	public boolean hasPath(int v, int w) {
		return tc[v][w];
	}
}
