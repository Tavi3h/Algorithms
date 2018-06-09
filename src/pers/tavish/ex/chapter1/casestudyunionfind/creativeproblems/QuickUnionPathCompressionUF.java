package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

// 提高题1.5.12
public class QuickUnionPathCompressionUF {
	private int[] id;
	private int count;

	public QuickUnionPathCompressionUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public int find(int p) {
		int root = p;
		while (root != id[root]) {
			root = id[root];
		}
		// 对路径进行压缩
		// 找到根节点后，再访问一遍p到根节点这条路径上的所有结点，将它们直接和根节点相连。
		while (p != root) {
			int newp = id[p];
			id[p] = root;
			p = newp;
		}
		return root;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ) {
			return;
		}
		id[rootP] = rootQ;
		count--;
	}
}
