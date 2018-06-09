package pers.tavish.ex.chapter2.mergesort.experiments;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import pers.tavish.code.chapter2.mergesort.MergeBUT;
import pers.tavish.code.chapter2.mergesort.MergeTDT;

public class SortCompareEx2227 {
	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("MergeTDT")) {
			MergeTDT.sort(a);
		}
		if (alg.equals("MergeBUT")) {
			MergeBUT.sort(a);
		}
		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, int N, int T) {
		double total = 0d;
		Double[] a = new Double[N];
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				a[i] = StdRandom.uniform();
			}
			total += time(alg, a);
		}
		return total;
	}

	public static void main(String[] args) {

		String alg1 = "MergeTDT";
		String alg2 = "MergeBUT";

		for (int i = 1000; i <= 1000000; i *= 10) {
			double t1 = timeRandomInput(alg1, i, 20);
			double t2 = timeRandomInput(alg2, i, 20);
			
			StdOut.printf("For %d random Doubles\n    %s is", i, alg1);
			StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
		}
	}
}
