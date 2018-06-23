package pers.tavish.ex.chapter4.directedgraphs.exercises;

import edu.princeton.cs.algs4.In;
import pers.tavish.code.chapter4.directedgraphs.Digraph;

// 练习题4.2.6
public class Ex426 {
	public static void main(String[] args) {
		// 读取tinyDGex2.txt
		Digraph G = new Digraph(new In(args[0]));
		
		System.out.println(G.V()); // 12
		System.out.println(G.E()); // 16
		
		System.out.println(G.hasEdge(2, 3)); // true
		System.out.println(G.hasEdge(3, 2)); // false
		
		System.out.println(G.indegree(3)); // 2
		
//		G.addEdge(2, 3); //  java.lang.IllegalArgumentException: Parallel Edge is not permitted.
		
//		G.addEdge(2, 2); // java.lang.IllegalArgumentException: Self Edge is not permitted.
		
		System.out.println(G.outdegree(0)); // 2
		
		
		/*
		 	12 vertices, 16 edges 
			0: 6 5 
			1: 
			2: 0 3 
			3: 10 6 
			4: 1 
			5: 10 2 
			6: 2 
			7: 8 11 
			8: 1 4 
			9: 
			10: 3 
			11: 8 
		 */
		System.out.println(G);
		
		G.addEdge(9, 1);
		
		/*
		 	12 vertices, 16 edges 
			0: 6 5 
			1: 
			2: 0 3 
			3: 10 6 
			4: 1 
			5: 10 2 
			6: 2 
			7: 8 11 
			8: 1 4 
			9: 1
			10: 3 
			11: 8 
		 */
		System.out.println(G);
	}
}
