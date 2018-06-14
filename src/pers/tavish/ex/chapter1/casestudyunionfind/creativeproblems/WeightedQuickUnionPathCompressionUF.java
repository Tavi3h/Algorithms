package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

// 提高题1.5.13
public class WeightedQuickUnionPathCompressionUF {

	private int[] parent;
	private int[] size;
	private int count;

	public WeightedQuickUnionPathCompressionUF(int N) {
		count = N;
		parent = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	// validate that p is a valid index
	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
		}
	}

	public int find(int p) {
		validate(p);
		int root = p;
		while (root != parent[root]) {
			root = parent[root];
		}
		// 路径压缩
		while (p != root) {
			int newp = parent[p];
			parent[p] = root;
			p = newp;
		}
		return root;
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make smaller root point to larger one
		if (size[rootP] < size[rootQ]) {
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP];
		} else {
			parent[rootQ] = rootP;
			size[rootP] += size[rootQ];
		}
		count--;
	}
}
