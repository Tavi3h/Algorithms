package pers.tavish.code.chapter4.directedgraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

// 有向图的深度优先搜索
// 解决可达性
public class DirectedDFS {
	
	// marked[v] = true if v is reachable from source or sources
	private boolean[] marked;  
	
	// 起点s可以到达的顶点数
	private int count;
	
	/*
	 * 构造函数：读取一副图和起点s
	 * 找出图中所有s可达的顶点
	 */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }
    
    /*
     * 构造函数：读取一幅图和若干个起点sources
     * 找出图中可以由sources中所有顶点可达的所有顶点
     * 由于解决“是否存在一条从集合中的任意顶点到达给定顶点v的有向路径？”问题。
     */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        validateVertices(sources);
        for (int v : sources) {
            if (!marked[v]) {
            	dfs(G, v);
            }
        }
    }
    
    /*
     * 判断顶点v是否和起点s相连
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }
    
    /*
     * 返回起点（source or sources）可达的顶点的数量
     */
    public int count() {
        return count;
    }
    
    // 深度优先算法
    private void dfs(Digraph G, int v) { 
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
            	dfs(G, w);
            }
        }
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
        	throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
    
    public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));
		Bag<Integer> sources = new Bag<>();
		
		for (int i = 1; i < args.length; i++) {
			sources.add(Integer.parseInt(args[i]));
		}
		
		DirectedDFS reachable = new DirectedDFS(G, sources);
		
		for (int v = 0; v < G.V(); v++) {
			if (reachable.marked(v)) {
				System.out.print(v + " ");
			}
		}
		System.out.println();
		
	}
}
