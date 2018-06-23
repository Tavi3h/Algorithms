package pers.tavish.ex.chapter4.directedgraphs.exercises;

import pers.tavish.code.chapter4.directedgraphs.DepthFirstOrder;
import pers.tavish.code.chapter4.directedgraphs.Digraph;

// 练习题4.2.17
public class Ex4217 {
	public static void main(String[] args) {
		
		Digraph G = new Digraph(6);
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(2, 3);
		G.addEdge(2, 4);
		G.addEdge(4, 5);
		G.addEdge(5, 2);
		
		// G的反向图的逆后序排列
		Iterable<Integer> grrp = new DepthFirstOrder(G.reverse()).reversePost();
		
		// G的后序排列
		Iterable<Integer> gp = new DepthFirstOrder(G).post();
		
		
		for (int i : grrp) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : gp) {
			System.out.print(i + " ");
		}
	}
}
