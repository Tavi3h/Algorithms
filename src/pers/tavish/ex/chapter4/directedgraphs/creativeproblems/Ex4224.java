package pers.tavish.ex.chapter4.directedgraphs.creativeproblems;

import java.util.Iterator;

import pers.tavish.code.chapter4.directedgraphs.Digraph;
import pers.tavish.code.chapter4.directedgraphs.Topological;
import pers.tavish.code.chapter4.directedgraphs.TransitiveClosure;

// 提高题4.2.24
public class Ex4224 {

	public static boolean hasHamiltonianPath(Digraph G) {
		Topological top = new Topological(G);
		if (!top.isDAG()) {
			return false;
		}

		TransitiveClosure tc = new TransitiveClosure(G);

		Iterator<Integer> iterator = top.order().iterator();

		Integer v = null;
		Integer w = null;
		
		// 逐个两两比较拓扑排序上的顶点
		while (iterator.hasNext()) {
			v = v == null ? iterator.next() : w;
			if (iterator.hasNext()) {
				w = iterator.next();
			}
			if (!tc.reachable(v, w)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Digraph G = new Digraph(6);
		G.addEdge(0, 2);
		G.addEdge(2, 3);
		G.addEdge(2, 5);
		G.addEdge(3, 1);
		G.addEdge(5, 4);
		G.addEdge(4, 1);
		
		System.out.println(hasHamiltonianPath(G)); // false
		
		G.addEdge(3, 5);
		
		System.out.println(hasHamiltonianPath(G)); // true
	}
}
