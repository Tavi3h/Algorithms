package pers.tavish.ex.chapter1.casestudyunionfind.experiments;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class ErdosRenyiWQUEx1526 {

	private int[] id; // 父链接数组（由触点索引）
	private int[] sz; // （由触点索引的）各个根节点所对应的分量的大小
	private int count; // 连通分量的数量
	private int total; // 记录访问数组的总次数
	private int cost; // 本次操作访问数组次数

	public ErdosRenyiWQUEx1526(int N) {
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
			cost += 2;
			p = id[p];
		}
		cost++;
		total += cost;
		return p;
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
		cost++;
		total += cost;
		count--;
	}

	// 重置本次操作的数组访问次数
	public void resetCost() {
		cost = 0;
	}

	public int getCost() {
		return cost;
	}

	public int getTotal() {
		return total;
	}

	// 画图的方法
	public static void draw(int cost, int total, int num) {

		StdDraw.setXscale(0, 2000);
		StdDraw.setYscale(0, 50);
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.point(num, cost);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(num, total / num);

	}

	public static void main(String[] args) {

		int N = StdIn.readInt();

		ErdosRenyiWQUEx1526 uf = new ErdosRenyiWQUEx1526(N); // 创建对象，初始化分量

		int num = 0; // 记录读取的连接对的数量

		StdDraw.setCanvasSize(500, 500);

		while (uf.count() != 1) {
			int p = StdRandom.uniform(0, N);
			int q = StdRandom.uniform(0, N);
			if (!uf.connected(p, q)) { // 如果未连通
				uf.union(p, q); // 归并分量
			}
			draw(uf.getCost(), uf.getTotal(), ++num);
			uf.resetCost();
		}
	}
}
