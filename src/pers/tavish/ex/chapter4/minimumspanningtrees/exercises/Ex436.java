package pers.tavish.ex.chapter4.minimumspanningtrees.exercises;

import pers.tavish.code.chapter4.minimumspanningtrees.Edge;
import pers.tavish.code.chapter4.minimumspanningtrees.EdgeWeightedGraph;
import pers.tavish.code.chapter4.minimumspanningtrees.LazyPrimMST;

// 练习题 4.3.6 
public class Ex436 {
	public static void main(String[] args) {
		
		EdgeWeightedGraph G = new EdgeWeightedGraph(7);
		/*  去掉含有顶点7的边及权重
		 * 	4 5 0.35
			1 5 0.32
			0 4 0.38
			2 3 0.17
			0 2 0.26
			1 2 0.36
			1 3 0.29
			6 2 0.40
			3 6 0.52
			6 0 0.58
			6 4 0.93
		 */
		G.addEdge(new Edge(4, 5, 0.35));
		G.addEdge(new Edge(1, 5, 0.32));
		G.addEdge(new Edge(0, 4, 0.38));
		G.addEdge(new Edge(2, 3, 0.17));
		G.addEdge(new Edge(0, 2, 0.26));
		G.addEdge(new Edge(1, 2, 0.36));
		G.addEdge(new Edge(1, 3, 0.29));
		G.addEdge(new Edge(6, 2, 0.40));
		G.addEdge(new Edge(3, 6, 0.52));
		G.addEdge(new Edge(6, 0, 0.58));
		G.addEdge(new Edge(6, 4, 0.93));
		
		LazyPrimMST lpm = new LazyPrimMST(G);
        for (Edge e : lpm.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", lpm.weight());
	}
}
