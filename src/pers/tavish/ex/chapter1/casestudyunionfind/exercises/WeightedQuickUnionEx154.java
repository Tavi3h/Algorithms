package pers.tavish.ex.chapter1.casestudyunionfind.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionEx154 {
	private int[] id; // 父链接数组（由触点索引）
	private int[] sz; // （由触点索引的）各个根节点所对应的分量的大小
	private int count; // 连通分量的数量

	private int costID; // 记录访问id数组的次数
	private int costSZ; // 记录访问Sz数组的次数

	public WeightedQuickUnionEx154(int N) {
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
			costID += 2; // 若进入while循环，则判断条件一次，赋值一次
		}
		costID++; // 若未进入while循环，则只有判断条件的一次
		return p;
	}

	public void resetCost() {
		costID = 0;
		costSZ = 0;
	}

	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) {
			return;
		}

		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] = sz[i] + sz[j];
		} else {
			id[j] = i;
			sz[i] = sz[j] + sz[i];
		}

		costID++; // 赋值一次
		costSZ += 5; // 判断语句2次，赋值语句3次
		count--;
	}

	public void printInf() {

		System.out.print("id[]: ");
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + " ");
		}
		System.out.println();

		System.out.print("sz[]: ");
		for (int i = 0; i < sz.length; i++) {
			System.out.print(sz[i] + " ");
		}
		System.out.println();

		System.out.println("访问id数组次数：" + costID);
		System.out.println("访问sz数组次数：" + costSZ);
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedQuickUnionEx154 uf = new WeightedQuickUnionEx154(N); // 创建对象，初始化分量
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
