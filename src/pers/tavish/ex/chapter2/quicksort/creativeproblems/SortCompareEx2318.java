package pers.tavish.ex.chapter2.quicksort.creativeproblems;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import pers.tavish.code.chapter2.quicksort.QuickT;

public class SortCompareEx2318 {
	
	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("QuickT")) {
			QuickT.sort(a);
		}
		if (alg.equals("QuickTMedian3")) {
			QuickTMedian3.sort(a);
		}
		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, Double[] a) {
		return time(alg, a);
	}

	public static void main(String[] args) {

		String alg1 = "QuickTMedian3";
		String alg2 = "QuickT";

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
