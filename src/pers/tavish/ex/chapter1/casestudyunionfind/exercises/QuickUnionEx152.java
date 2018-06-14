package pers.tavish.ex.chapter1.casestudyunionfind.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionEx152 {

	private int[] id; // 分量id，以触点为索引
	private int count; // 分量数量
	
	@SuppressWarnings("unused")
	private int visitArrayCount; // 记录访问数组的次数

	public QuickUnionEx152(int N) {
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

	/*
	 * quick-union算法
	 */
	private int find(int p) {
		// 找出分量的名称
		while (p != id[p]) {
			p = id[p];
			visitArrayCount++;
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
		visitArrayCount++;
		count--;
	}
	
	// 打印数组的方法
	public void printTree() {
		// 待实现
	}

	public static void main(String[] args) {
		int N = StdIn.readInt(); // 读取触点数量
		QuickUnionEx152 uf = new QuickUnionEx152(N); // 创建对象，初始化分量
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt(); // 读取整数对
			if (!uf.connected(p, q)) { // 如果未连通
				uf.union(p, q); // 归并分量
			}
			uf.printTree();
		}
		StdOut.println(uf.count + " components");
	}
}
