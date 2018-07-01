package pers.tavish.test;

import edu.princeton.cs.algs4.IndexMinPQ;

public class TestMinPQ {
	public static void main(String[] args) {
		IndexMinPQ<Double> imp = new IndexMinPQ<>(100);
		imp.insert(4, 0.38);
		imp.insert(7, 0.34);
		imp.decreaseKey(4, 0.1);
		System.out.println(imp.minKey()); // 0.1
		System.out.println(imp.delMin()); // 4
	}
}
