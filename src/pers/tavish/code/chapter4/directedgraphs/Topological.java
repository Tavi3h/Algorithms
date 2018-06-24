package pers.tavish.code.chapter4.directedgraphs;

// 拓扑排序
public class Topological {
	
	private Iterable<Integer> order; // 顶点的拓扑排序
	
	public Topological(Digraph G) {
		DirectedCycle cycleFinder = new DirectedCycle(G);
		if (!cycleFinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
	public boolean isDAG() {
		return order != null;
	}
	
	public static void main(String[] args) {
		Digraph G = new Digraph(6);
		G.addEdge(0, 2);
		G.addEdge(2, 3);
		G.addEdge(2, 5);
		G.addEdge(3, 5);
		G.addEdge(3, 1);
		G.addEdge(5, 4);
		G.addEdge(4, 1);
		Topological topo = new Topological(G);
		for (int i : topo.order) {
			System.out.print(i + " ");
		}
	}
}
