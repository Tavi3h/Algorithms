package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindEx1516 {

	private int[] id; // 分量id，以触点为索引
	private int count; // 分量数量

	private int total; // 记录访问数组的总次数
	private int cost; // 本次操作访问数组次数

	public QuickFindEx1516(int N) {
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
	 * quick-find算法 该算法的运行时间对于最终只能得到少数连通分量的一般应用是平方级别的
	 */
	private int find(int p) {
		cost++;
		total += cost;
		return id[p];
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
		total += cost;
		count--;
	}

	// 画图的方法
	public static void draw(int cost, int total, int num) {
		
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1500);
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.point(num, cost);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(num, total / num);

	}

	public static void main(String[] args) {
		int N = StdIn.readInt(); // 读取触点数量
		QuickFindEx1516 uf = new QuickFindEx1516(N); // 创建对象，初始化分量

		int num = 0; // 记录读取的连接对的数量

		StdDraw.setCanvasSize(500, 500);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt(); // 读取整数对
			if (!uf.connected(p, q)) { // 如果未连通
				uf.union(p, q); // 归并分量
			}
			draw(uf.getCost(), uf.getTotal(), ++num);
			uf.resetCost();
		}
		StdOut.println(uf.count + " components");
	}
}
