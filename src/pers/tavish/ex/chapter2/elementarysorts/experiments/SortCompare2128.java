package pers.tavish.ex.chapter2.elementarysorts.experiments;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

// 实验题 2.1.28
public class SortCompare2128 {

	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("Insertion")) {
			Insertion.sort(a);
		}
		if (alg.equals("Selection")) {
			Selection.sort(a);
		}
		if (alg.equals("Shell")) {
			Shell.sort(a);
		}
		if (alg.equals("Merge")) {
			Merge.sort(a);
		}
		if (alg.equals("Quick")) {
			Quick.sort(a);
		}
		if (alg.equals("Heap")) {
			Heap.sort(a);
		}
		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, int N) {
		double total = 0d;
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			if(StdRandom.bernoulli()) {
				a[i] = 0d;
			} else {
				a[i] = 1d;
			}
		}
		total += time(alg, a);
		return total;
	}

	public static void main(String[] args) {
		String alg1 = args[0];
		String alg2 = args[1];

		int N = Integer.parseInt(args[2]);

		for (int i = N, k = 1; k < 10; i *= 2, k++) {
			double t1 = timeRandomInput(alg1, i);
			double t2 = timeRandomInput(alg2, i);
			System.out.println("ArrayLength = " + i);
			System.out.println(alg1 + "耗时：" + t1);
			System.out.println(alg2 + "耗时：" + t2);
			System.out.println();
		}
	}
}
