package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

import edu.princeton.cs.algs4.StdIn;

// 提高题 1.5.20
public class ResizingArrayWeightUnionFind {
	
	private int[] id;
	private int[] sz;
	
	public ResizingArrayWeightUnionFind() {
		
		// 初始化时设定数组长度为20
		int N = 20;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	private void resize(int times) {
		
		int size = id.length * times;
		
		int[] tmpId = new int[size];
		int[] tmpSz = new int[size];
		
		// 将原有值赋值给临时数组
		for (int i = 0; i < id.length; i++) {
			tmpId[i] = id[i];
			tmpSz[i] = sz[i];
		}
		
		// 新增的位置与初始化时相同，id数组初始为与角标相同，sz数组初始为1
		for (int i = id.length; i < size; i++) {
			tmpId[i] = i;
			tmpSz[i] = 1;
		}
		
		id = tmpId;
		sz = tmpSz;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int p) {
		// 如果p超出数组
		if (p > id.length - 1) {
			// 按倍数调整数组大小
			resize(1 + p / (id.length - 1));
		}
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

		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
	
	public static void main(String[] args) {
		
		ResizingArrayWeightUnionFind rawuf = new ResizingArrayWeightUnionFind();
		
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!rawuf.connected(p, q)) {
				rawuf.union(p, q);
				System.out.println(p + " link " + q);
			} else {
				System.out.println(p + " already link " + q);
			}
		}
	}
}
