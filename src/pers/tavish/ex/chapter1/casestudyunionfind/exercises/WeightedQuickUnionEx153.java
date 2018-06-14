package pers.tavish.ex.chapter1.casestudyunionfind.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 练习题1.5.3
public class WeightedQuickUnionEx153 {
	private int[] id; // 父链接数组（由触点索引）
	private int[] sz; // （由触点索引的）各个根节点所对应的分量的大小
	private int count; // 连通分量的数量

	private int cost; // 记录访问id数组的次数

	public WeightedQuickUnionEx153(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			sz[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int p) {
		while (p != id[p]) {
			p = id[p];
			cost += 2; // 若进入while循环，则判断条件一次，赋值一次
		}
		cost++; // 若未进入while循环，则只有判断条件的一次
		return p;
	}

	public void resetCost() {
		cost = 0;
	}

	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) {
			return;
		}

		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
		cost++; // 对id数组赋值
		count--;
	}

	public void printInf() {
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + " ");
		}
		System.out.println("访问数组次数：" + cost);
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedQuickUnionEx153 uf = new WeightedQuickUnionEx153(N); // 创建对象，初始化分量
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
