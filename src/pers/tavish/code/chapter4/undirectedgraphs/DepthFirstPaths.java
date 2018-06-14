package pers.tavish.code.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.Stack;

// 深度优先搜索查找图中的路径
public class DepthFirstPaths {
	private boolean[] marked; // marked[v]表示是否存在s-v的路径
	private int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
	private final int s; // 起点
	
	/*
	 * 构造函数：指定起点s和图G，计算路径
	 */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }
    
    /*
     * 起点和v之间是否存在路径
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }
    
    /*
     * 返回起点到v的路径
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
        	path.push(x);
        }
        path.push(s);
        return path;
    }
    
    // 从v开始进行深度优先搜索
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    
	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
}
