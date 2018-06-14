package pers.tavish.code.chapter4.undirectedgraphs;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

// 非稠密图的邻接表数据结构
public class Graph {

	// 获取系统属性--分隔符
	private static final String NEWLINE = System.getProperty("line.separator");
	// 顶点数
	private final int V;
	// 边数
	private int E;
	// 邻接表数组
	private Bag<Integer>[] adj;

	/*
	 * 构造函数
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		}
		this.V = V;
		this.E = 0;

		adj = (Bag<Integer>[]) new Bag[V];

		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	/*
	 * 构造函数：使用一个输入流构造图
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		try {
			this.V = in.readInt();
			if (V < 0) {
				throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			}

			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<>();
			}

			int E = in.readInt();
			if (E < 0) {
				throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			}
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}

	/*
	 * 构造函数：对图G进行深度复制
	 */
	public Graph(Graph G) {

		this(G.V());
		this.E = G.E();

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
	 * 返回图的顶点数
	 */
	public int V() {
		return V;
	}

	/*
	 * 返回图的边数
	 */
	public int E() {
		return E;
	}

	/*
	 * 在图中添加一条无向的边
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	/*
	 * 返回和v相邻的所有顶点
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/*
	 * 返回顶点的度数（其相邻的顶点总数）
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	// 判断结点是否符合规则
	private void validateVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
}
