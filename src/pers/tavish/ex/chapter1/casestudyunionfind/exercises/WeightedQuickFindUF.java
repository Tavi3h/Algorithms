package pers.tavish.ex.chapter1.casestudyunionfind.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickFindUF {

	private int[] id; // 分量id，以触点为索引
	private int[] sz; // 记录每个连通分量的大小
	private int count; // 分量数量

	public WeightedQuickFindUF(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
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

	/*
	 * quick-find算法 该算法的运行时间对于最终只能得到少数连通分量的一般应用是平方级别的
	 */
	private int find(int p) {
		return id[p];
	}

	public void union(int p, int q) {
		// 将p和q归并到相同分量中
		int pID = find(p);
		int qID = find(q);

		// 如果p和q已经在同一个分量中则不需要采取任何行动
		if (pID == qID) {
			return;
		}

		// 将连通分量数量较小的归并到较大的中
		if (sz[pID] > sz[qID]) {
			for (int i = 0; i < id.length; i++) {
				if (id[i] == qID) {
					id[i] = pID;
				}
			}
			// 较大的连通分量数量加上较小的连通分量的数量
			sz[pID] += sz[qID];
		} else {
			for (int i = 0; i < id.length; i++) {
				if (id[i] == pID) {
					id[i] = qID;
				}
			}
			sz[qID] += sz[pID];
		}
		count--;
	}
	public static void main(String[] args) {
		int N = StdIn.readInt(); // 读取触点数量
		WeightedQuickFindUF uf = new WeightedQuickFindUF(N); // 创建对象，初始化分量
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
