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
	 * 构造函数：构造一个只有定点，但是所有顶点均为连接（不含有边）的图
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
	 * 构造函数：从输入流读取邻接表，并构造图 参数isAdjFile只是用来与其他构造函数进行区分，这个参数必须传入true。
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in, boolean isAdjFile) {

		if (!isAdjFile) {
			throw new IllegalArgumentException("Argument isAdjFile must be true.");
		}

		try {

			/*
			 * 读取第一行：13 vertices, 13 edges 得到V = 13, E = 13
			 */
			String[] ve = in.readLine().split(", ");
			this.V = Integer.parseInt(ve[0].substring(0, ve[0].indexOf(" ")));
			this.E = Integer.parseInt(ve[1].substring(0, ve[1].indexOf(" ")));

			// 根据V创建邻接表数组
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<>();
			}

			// 遍历每一个元素
			for (int i = 0; i < V; i++) {
				
				
				/*
				 * 读取行：0: 6 5 2 1
				 */
				String[] datas = in.readLine().split(": ");
				if (datas.length == 1) {
					// length = 1，说明该行的元素是孤立的，例如“12: ”，跳过该行
					continue;
				}

				int v = Integer.parseInt(datas[0]); // 获取到元素，0
				validateVertex(v); // 检查顶点v是否合法
				
				String[] adjs = datas[1].split(" "); // 获取其邻接元素，6、5、2、1
				Stack<Integer> reverse = new Stack<>();
				
				// 压入栈
				for (int j = 0; j < adjs.length; j++) {
					int w = Integer.parseInt(adjs[j]);
					validateVertex(w); // 检查顶点w是否合法
					reverse.push(w);
				}
				// 从栈中取出，此时add的顺序为1、2、5、6
				for (Integer w : reverse) {
					// 防止自环
					if (v == w) {
						throw new IllegalArgumentException("Self Edge is not permitted.");
					}

					// 防止平行边
					if (hasEdge(v, w)) {
						throw new IllegalArgumentException("Parallel Edge is not permitted.");
					}
					adj[v].add(w);
				}
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
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
		
		// 防止自环
		if (v == w) {
			throw new IllegalArgumentException("Self Edge is not permitted.");
		}

		// 防止平行边
		if (hasEdge(v, w)) {
			throw new IllegalArgumentException("Parallel Edge is not permitted.");
		}

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

	/*
	 * （练习4.1.4）判断在v和w之间是否存在一条边
	 */
	public boolean hasEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);

		for (Integer i : adj(v)) {
			if (i == w) {
				return true;
			}
		}
		return false;
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

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]), true);
		System.out.println(G);
	}
}
