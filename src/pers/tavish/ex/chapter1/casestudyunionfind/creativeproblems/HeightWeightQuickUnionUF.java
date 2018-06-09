package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

// 提高题1.5.14
public class HeightWeightQuickUnionUF {
	
	private int[] id; // 父链接数组（由触点索引）
	private int[] height; // 记录每棵树的高度
	private int count; // 连通分量的数量

	public HeightWeightQuickUnionUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		height = new int[N];
		for (int i = 0; i < N; i++) {
			height[i] = 0; // 由于记录的是高度，所以初始化为0
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
		}
		return p;
	}

	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) {
			return;
		}

		// 小树归并到大树时，大树的高度不变
		if (height[i] < height[j]) {
			id[i] = j;
		} else if (height[i] > height[j]){
			id[j] = i;
		} else {
			// 只有当两颗高度相同的树进行归并时，才将树的高度增加一
			// 这里将q所在树归并到p所在树，并增加p所在树的高度
			id[j] = i;
			height[i]++;
		}
		count--;
	}
}
