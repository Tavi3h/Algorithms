package pers.tavish.ex.chapter2.sortingapplications.exercises;

import edu.princeton.cs.algs4.StdRandom;

// 练习题 2.5.6
public class Ex256 {

	// 递归实现的select
	public static <T> Comparable<T> select_2(Comparable<T>[] a, int k) {
		StdRandom.shuffle(a);
		return select_2(a, k, 0, a.length - 1);
	}

	private static <T> Comparable<T> select_2(Comparable<T>[] a, int k, int lo, int hi) {
		if (hi <= lo) {
			return a[k];
		} else {
			int j = partition(a, lo, hi);
			if (j > k) {
				return select_2(a, k, lo, j - 1);
			} else if (j < k) {
				return select_2(a, k, j + 1, hi);
			} else {
				return a[k];
			}
		}
	}

	// 原select方法
	public static <T> Comparable<T> select(Comparable<T>[] a, int k) {
		StdRandom.shuffle(a);
		int lo = 0, hi = a.length - 1;
		while (hi > lo) {
			int j = partition(a, lo, hi);
			if (j == k)
				return a[k];
			else if (j > k)
				hi = j - 1;
			else if (j < k)
				lo = j + 1;
		}
		return a[k];
	}

	private static <T> int partition(Comparable<T>[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable<T> v = a[lo];
		while (true) {

			// find item on lo to swap
			while (less(a[++i], v)) {
				if (i == hi)
					break;
			}

			// find item on hi to swap
			while (less(v, a[--j])) {
				if (j == lo)
					break; // redundant since a[lo] acts as sentinel
			}

			// check if pointers cross
			if (i >= j)
				break;

			exch(a, i, j);
		}

		// put partitioning item v at a[j]
		exch(a, lo, j);

		// now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
		if (v == w)
			return false; // optimization when reference equals
		return v.compareTo((T) w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { -1, 1, 2, 3, 7, 23, 10, 2, 0, 4, 2, 3 };
		Integer[] arr2 = new Integer[] { -1, 1, 2, 3, 7, 23, 10, 2, 0, 4, 2, 3 };
		System.out.println(select(arr, 0));
		System.out.println(select_2(arr2, 0));
	}
}
