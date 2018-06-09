package pers.tavish.ex.chapter2.quicksort.exercises;

import edu.princeton.cs.algs4.StdRandom;

public class Ex235Quick3way {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	// quicksort the subarray a[lo .. hi] using 3-way partitioning
	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int lt = lo, gt = hi;
		T v = a[lo];
		int i = lo + 1;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) {
				exch(a, lt++, i++);
			} else if (cmp > 0) {
				exch(a, i, gt--);
			} else {
				i++;
			}
		}

		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	private static <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		Integer[] a = { 5, 5, 1, 5, 1, 5 };
		sort(a);
		for (Integer integer : a) {
			System.out.print(integer + " ");
		}
	}
}
