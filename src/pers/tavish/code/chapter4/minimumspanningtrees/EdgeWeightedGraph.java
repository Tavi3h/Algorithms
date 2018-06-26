package pers.tavish.code.chapter4.minimumspanningtrees;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

// 加权无向图数据结构
// 依赖加权边（Edge）
public class EdgeWeightedGraph {

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		}
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	/*
	 * 生成一个随机的加权无向图
	 */
	public EdgeWeightedGraph(int V, int E) {
		this(V);
		if (E < 0) {
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		}
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
			addEdge(new Edge(v, w, weight));
		}
	}

	/*
	 * 读取输入流，构造加权无向图
	 */
	public EdgeWeightedGraph(In in) {
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
			addEdge(new Edge(v, w, weight));
		}
	}

	/*
	 * 读取一幅加权无向图，构造这个图的副本
	 */
	public EdgeWeightedGraph(EdgeWeightedGraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Edge> reverse = new Stack<>();
			for (Edge e : G.adj[v]) {
				reverse.push(e);
			}
			for (Edge e : reverse) {
				adj[v].add(e);
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
	 * 添加一条边
	 */
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}

	/*
	 * 返回顶点v所在的所有加权边
	 */
	public Iterable<Edge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/*
	 * 返回与顶点v相连的顶点的数量
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/*
	 * 返回图的所有边
	 */
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// add only one copy of each self loop (self loops will be consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0) {
						list.add(e);
					}
					selfLoops++;
				}
			}
		}
		return list;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}
