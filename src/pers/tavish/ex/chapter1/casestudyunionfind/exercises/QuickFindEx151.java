package pers.tavish.ex.chapter1.casestudyunionfind.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindEx151 {

	private int[] id; // 分量id，以触点为索引
	private int count; // 分量数量

	private int cost; // 用于记录访问数组的次数

	public QuickFindEx151(int N) {
		count = N;
		cost = 0;
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
	 * quick-find算法
	 */
	private int find(int p) {
		cost++; // 调用find()方法会访问一次数组
		return id[p];
	}
	
	public void resetCost() {
		cost = 0;
	}

	public void union(int p, int q) {
		// 将p和q归并到相同分量中
		int pID = find(p);
		int qID = find(q);

		// 如果p和q已经在同一个分量中则不需要采取任何行动
		if (pID == qID) {
			return;
		}

		// 将p的分量重命名为q的名称
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pID) {
				cost++; 
				id[i] = qID;
			}
		}
		cost += id.length;
		count--;
	}

	// 打印当前数组和访问次数的方法
	public void printInf() {
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + " ");
		}
		System.out.println("访问数组次数：" + cost);
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickFindEx151 uf = new QuickFindEx151(N); // 创建对象，初始化分量
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt(); // 读取整数对
			if (!uf.connected(p, q)) { // 如果未连通
				uf.union(p, q); // 归并分量
			}
			uf.printInf();
			uf.resetCost();
		}
		StdOut.println(uf.count + " components");
	}
}
