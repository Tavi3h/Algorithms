package pers.tavish.ex.chapter2.mergesort.experiments;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import pers.tavish.code.chapter2.mergesort.MergeBUT;
import pers.tavish.code.chapter2.mergesort.MergeTDT;

public class SortCompareEx2227SameArray {
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

	public static double timeRandomInput(String alg, Double[] a) {
		return time(alg, a);
	}

	public static void main(String[] args) {

		String alg1 = "MergeTDT";
		String alg2 = "MergeBUT";

		Double[] a = null;
		Double[] b = null;
		
		for (int i = 1000; i <= 1000000; i *= 10) {
			
			double t1 = 0d;
			double t2 = 0d;
			for (int k = 0; k < 20; k++) {
				
				a = new Double[i];
				b = new Double[i];
				
				for (int j = 0; j < i; j++) {
					a[j] = StdRandom.uniform();
				}
				System.arraycopy(a, 0, b, 0, i);
				
				t1 += timeRandomInput(alg1, a);
				t2 += timeRandomInput(alg2, b);
			}

			StdOut.printf("For %d random Doubles\n    %s is", i, alg1);
			StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
		}
	}
}
