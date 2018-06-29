package pers.tavish.code.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.UF;

// 最小生成树的Boruvka算法
public class BoruvkaMST {
    private Bag<Edge> mst = new Bag<Edge>();    // edges in MST
    private double weight;                      // weight of MST

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public BoruvkaMST(EdgeWeightedGraph G) {
        UF uf = new UF(G.V());

        // repeat at most log V times or until we have V-1 edges
        for (int t = 1; t < G.V() && mst.size() < G.V() - 1; t = t + t) {

            // foreach tree in forest, find closest edge
            // if edge weights are equal, ties are broken in favor of first edge in G.edges()
			Edge[] closest = new Edge[G.V()];
			for (Edge e : G.edges()) {
				int v = e.either(), w = e.other(v);
				int i = uf.find(v), j = uf.find(w);
				if (i == j)
					continue; // same tree
				if (closest[i] == null || less(e, closest[i]))
					closest[i] = e;
				if (closest[j] == null || less(e, closest[j]))
					closest[j] = e;
			}

			// add newly discovered edges to MST
			for (int i = 0; i < G.V(); i++) {
				Edge e = closest[i];
				if (e != null) {
					int v = e.either(), w = e.other(v);
					// don't add the same edge twice
					if (!uf.connected(v, w)) {
						mst.add(e);
						weight += e.weight();
						uf.union(v, w);
					}
				}
			}
        }
    }
    
    public Iterable<Edge> edges() {
        return mst;
    }
    
    public double weight() {
        return weight;
    }

    // is the weight of edge e strictly less than that of edge f?
    private static boolean less(Edge e, Edge f) {
        return e.weight() < f.weight();
    }
}

