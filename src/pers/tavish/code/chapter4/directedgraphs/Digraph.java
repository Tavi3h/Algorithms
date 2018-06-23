package pers.tavish.code.chapter4.directedgraphs;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

// 有向图数据结构
public class Digraph {

	// 获取系统换行符
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V; // 图顶点数
	private int E; // 图边数
	private Bag<Integer>[] adj; // 顶点的邻接表
	private int[] indegree; // 顶点的入度

	/*
	 * 构造函数
	 */
	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		}
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	/*
	 * 构造函数：读取输入流，构造有向图
	 */
	@SuppressWarnings("unchecked")
	public Digraph(In in) {
		try {
			this.V = in.readInt();
			if (V < 0) {
				throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
			}
			indegree = new int[V];
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<>();
			}
			int E = in.readInt();
			if (E < 0) {
				throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
			}
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
		}
	}

	/*
	 * 构造函数：读取一副图，深度复制并构造一副有向图
	 */
	public Digraph(Digraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < V; v++) {
			this.indegree[v] = G.indegree(v);
		}
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	/*
	 * 返回顶点数
	 */
	public int V() {
		return V;
	}

	/*
	 * 返回边数
	 */
	public int E() {
		return E;
	}

	/*
	 * 在v和w之间添加一条v指向w的有向边
	 */
	public void addEdge(int v, int w) {

//		// 防止自环
//		if (v == w) {
//			throw new IllegalArgumentException("Self Edge is not permitted.");
//		}
//
//		// 防止平行边
//		if (hasEdge(v, w)) {
//			throw new IllegalArgumentException("Parallel Edge is not permitted.");
//		}
		
		validateVertex(v);
		validateVertex(w);
		
		adj[v].add(w);
		indegree[w]++;
		E++;
	}

	/*
	 * 返回顶点v的邻接表
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/*
	 * 返回顶点v的出度
	 */
	public int outdegree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/*
	 * 返回顶点v的入度
	 */
	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}

	/*
	 * 返回该图的一个反向副本
	 */
	public Digraph reverse() {
		Digraph reverse = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}

	/*
	 * toString()方法
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for (int w : adj[v]) {
				s.append(String.format("%d ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	/*
	 * 判断是否存在v->w
	 */
	public boolean hasEdge(int v, int w) {
		
		validateVertex(v);
		validateVertex(w);
		
		for (int element : adj(v)) {
			if (element == w) {
				return true;
			}
		}
		return false;
	}

	// 判断顶点v是否合法
	private void validateVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
}
