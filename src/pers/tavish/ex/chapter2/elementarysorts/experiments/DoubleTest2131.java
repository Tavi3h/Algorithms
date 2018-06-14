package pers.tavish.ex.chapter2.elementarysorts.experiments;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

// 实验题 2.1.31
public class DoubleTest2131 {

	public static void insertionSort(Double[] a) {
		Insertion.sort(a);
	}

	public static void selectionSort(Double[] a) {
		Selection.sort(a);
	}

	public static void shellSort(Double[] a) {
		Shell.sort(a);
	}

	public static double timeTrialInsertion(Double[] a) {
		Stopwatch timer = new Stopwatch();
		insertionSort(a);
		return timer.elapsedTime();
	}

	public static double timeTrialSelection(Double[] a) {
		Stopwatch timer = new Stopwatch();
		selectionSort(a);
		return timer.elapsedTime();
	}

	public static double timeTrialShell(Double[] a) {
		Stopwatch timer = new Stopwatch();
		shellSort(a);
		return timer.elapsedTime();
	}

	public static Double[] createRandomDoubles(int N) {
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		return a;
	}

	public static void main(String[] args) {
		
		Double[] arr = createRandomDoubles(50);
		Double[] backup = new Double[arr.length];
		System.arraycopy(arr, 0, backup, 0, arr.length);
		double prev = timeTrialShell(backup);

		for (int i = 100; i <= 1000000; i *= 2) {

			arr = createRandomDoubles(i);
			backup = new Double[arr.length];
			System.arraycopy(arr, 0, backup, 0, arr.length);
			double time = timeTrialShell(backup);
			System.out.printf("%6d %7.2f ", i, time);
			System.out.printf("%7.2f\n", time / prev);
			prev = time;
		}
	}
}
