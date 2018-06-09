package pers.tavish.code.chapter1.casestudyunionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {

	private int[] id; // 分量id，以触点为索引
	private int count; // 分量数量

	public QuickUnionUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int p) {
		// 找出分量的名称
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		// 将p和q的根节点统一
		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot) {
			return;
		}

		id[pRoot] = qRoot;
		count--;

	}

	public static void main(String[] args) {
		int N = StdIn.readInt(); // 读取触点数量
		QuickUnionUF uf = new QuickUnionUF(N); // 创建对象，初始化分量
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt(); // 读取整数对
			if (uf.connected(p, q)) { // 如果已连通，则忽略
				continue;
			}
			uf.union(p, q); // 归并分量
			StdOut.println(p + " " + q); // 打印链接
		}
		StdOut.println(uf.count + " components");
	}
}
