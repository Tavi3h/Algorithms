package pers.tavish.code.chapter4.shortestpaths;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

// 加权无向图数据结构
public class EdgeWeightedDigraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V; // 顶点总数
	private int E; // 边的总数
	private Bag<DirectedEdge>[] adj; // 邻接表
	private int[] indegree; // 顶点v的入度

	/*
	 * 生成一个具有V个顶点的空有向图
	 */
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		}
		this.V = V;
		this.E = 0;
		this.indegree = new int[V];
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	/*
	 * 随机生成具有V个顶点和E条边的加权有向图
	 */
	public EdgeWeightedDigraph(int V, int E) {
		this(V);
		if (E < 0) {
			throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
		}
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double weight = 0.01 * StdRandom.uniform(100);
			DirectedEdge e = new DirectedEdge(v, w, weight);
			addEdge(e);
		}
	}

	/*
	 * 读取输入流，生成一张加权有向图
	 */
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		if (E < 0) {
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		}
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			validateVertex(v);
			validateVertex(w);
			double weight = in.readDouble();
			addEdge(new DirectedEdge(v, w, weight));
		}
	}

	/*
	 * 返回节点总数
	 */
	public int V() {
		return V;
	}

	/*
	 * 返回边的总数
	 */
	public int E() {
		return E;
	}

	/*
	 * 添加一条边
	 */
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		indegree[w]++;
		E++;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}

	/*
	 * 返回顶点v指向的所有的顶点
	 */
	public Iterable<DirectedEdge> adj(int v) {
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
	 * 返回图的所有有向边
	 */
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> list = new Bag<>();
		for (int v = 0; v < V; v++) {
			for (DirectedEdge e : adj(v)) {
				list.add(e);
			}
		}
		return list;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (DirectedEdge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}
