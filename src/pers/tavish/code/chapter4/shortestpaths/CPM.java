package pers.tavish.code.chapter4.shortestpaths;

import edu.princeton.cs.algs4.StdIn;

// 优先级限制下的并行任务调度问题的关键路径方法
public class CPM {
	
	// 读取jobPC.txt
	public static void main(String[] args) {

		// number of jobs
		int n = StdIn.readInt();

		// source and sink
		int source = 2 * n;
		int sink = 2 * n + 1;

		// build network
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);
		for (int i = 0; i < n; i++) {
			double duration = StdIn.readDouble();
			G.addEdge(new DirectedEdge(source, i, 0.0));
			G.addEdge(new DirectedEdge(i + n, sink, 0.0));
			G.addEdge(new DirectedEdge(i, i + n, duration));

			// precedence constraints
			int m = StdIn.readInt();
			for (int j = 0; j < m; j++) {
				int precedent = StdIn.readInt();
				G.addEdge(new DirectedEdge(n + i, precedent, 0.0));
			}
		}

		// compute longest path
		AcyclicLP lp = new AcyclicLP(G, source);

		// print results
		System.out.println(" job   start  finish");
		System.out.println("--------------------");
		for (int i = 0; i < n; i++) {
			System.out.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i + n));
		}
		System.out.printf("Finish time: %7.1f\n", lp.distTo(sink));
	}
	/*
	 job   start  finish
	--------------------
	   0     0.0    41.0
	   1    41.0    92.0
	   2   123.0   173.0
	   3    91.0   127.0
	   4    70.0   108.0
	   5     0.0    45.0
	   6    70.0    91.0
	   7    41.0    73.0
	   8    91.0   123.0
	   9    41.0    70.0
	Finish time:   173.0
	 */
}
