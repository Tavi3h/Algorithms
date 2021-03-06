package pers.tavish.ex.chapter1.casestudyunionfind.experiments;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class ErdosRenyiQUEx1526 {
	private int[] id; // 分量id，以触点为索引
	private int count; // 分量数量

	private int total; // 记录访问数组的总次数
	private int cost; // 本次操作访问数组次数

	public ErdosRenyiQUEx1526(int N) {
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
			cost += 2;
			p = id[p];
		}
		cost++;
		total += cost;
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
		StdDraw.setYscale(0, 1000);
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.point(num, cost);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(num, total / num);

	}

	public static void main(String[] args) {

		int N = StdIn.readInt();

		ErdosRenyiQUEx1526 uf = new ErdosRenyiQUEx1526(N); // 创建对象，初始化分量

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
