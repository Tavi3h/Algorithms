package pers.tavish.ex.chapter2.quicksort.experiments;

import edu.princeton.cs.algs4.StdRandom;

// 实验题 2.3.25
public class QuickTWithInsertion {

	private static final int INSERTION_SORT_CUTOFF = 8;

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 打乱数组，消除对输入的依赖
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}

		// 判断是否为小数组
		int n = hi - lo + 1;
		if (n <= INSERTION_SORT_CUTOFF) {
			insertionSort(a, lo, hi + 1);
			return;
		}

		int j = partition(a, lo, hi); // 将数组切分
		sort(a, lo, j - 1); // 排序左半边
		sort(a, j + 1, hi); // 排序右半边
	}

	// 对小数组使用插入排序，排序[lo...hi)
	private static <T extends Comparable<? super T>> void insertionSort(T[] a, int lo, int hi) {
		for (int i = lo; i < hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
		// 将数组分为a[lo...i-1]，a[i]，a[i+1...hi]
		int i = lo, j = hi + 1;
		T v = a[lo];
		while (true) {
			// 扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}
			while (less(v, a[--j])) {
				// 对于左边界的检查实际是冗余的
				// 因为v不可能小于a[lo]
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			exch(a, i, j);
		}

		exch(a, lo, j); // 将v = a[j]放入正确的位置
		return j; // 达成 a[lo..j-1] <= a[j] <= a[j+1..hi]
	}

	private static <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		StdRandom.shuffle(arr);
		sort(arr);
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
	}
}
