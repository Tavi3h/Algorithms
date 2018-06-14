package pers.tavish.code.chapter4.undirectedgraphs;

// 使用深度优先搜索找出图中的所有联通分量
public class CC {

	private boolean[] marked; // marked[v] = has vertex v been marked?
	private int[] id; // id[v] = id of connected component containing v
	private int count; // number of connected components
	private int[] size; // size[id] = number of vertices in given component

	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		size = new int[G.V()];
		for (int s = 0; s < G.V(); s++) {
			if (!marked[s]) {
				dfs(G, s);
				count++;
			}
		}
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}

	public int count() {
		return count;
	}

	public int id(int v) {
		return id[v];
	}
	
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
        	throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}
