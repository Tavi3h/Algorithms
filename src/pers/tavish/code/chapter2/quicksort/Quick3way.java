package pers.tavish.code.chapter2.quicksort;

import edu.princeton.cs.algs4.StdRandom;

// 三向切分的快速排序
public class Quick3way {

	// This class should not be instantiated.
	private Quick3way() {
	}

	public static <T> void sort(Comparable<T>[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	// quicksort the subarray a[lo .. hi] using 3-way partitioning
	private static <T> void sort(Comparable<T>[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int lt = lo, gt = hi;
		Comparable<T> v = a[lo];
		int i = lo + 1;
		while (i <= gt) {
			@SuppressWarnings("unchecked")
			int cmp = a[i].compareTo((T) v);
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

	private static <T> void exch(Comparable<T>[] a, int i, int j) {
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		Integer[] a = { 5, 2, 1, 3, 4, 0 };
		sort(a);
		for (Integer integer : a) {
			System.out.print(integer + " ");
		}
	}
}
