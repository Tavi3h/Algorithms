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
}
