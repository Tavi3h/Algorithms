package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.UF;

// 提高题1.5.17 
public class ErdosRenyi {

	public static int count(int N) {

		UF uf = new UF(N);
		int linkCount = 0;
		int pairCount = 0; // 1.5.21 修改

		while (uf.count() != 1) {
			int p = StdRandom.uniform(0, N);
			int q = StdRandom.uniform(0, N);
			pairCount++; // 1.5.21 修改
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				linkCount++;
				// System.out.println(p + " link " + q);
			}
		}
		System.out.println("实际生成整数对数量：" + pairCount + " ，理论所需数量：" + (N * Math.log(N) / 2)); // 1.5.21 修改
		return linkCount;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		System.out.println(count(N));
	}
}
