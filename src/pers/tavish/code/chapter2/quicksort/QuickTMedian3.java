package pers.tavish.code.chapter2.quicksort;

import edu.princeton.cs.algs4.StdRandom;

// 三取样切分
public class QuickTMedian3 {
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int j = partition(a, lo, hi); // 将数组切分
		sort(a, lo, j - 1); // 排序左半边
		sort(a, j + 1, hi); // 排序右半边
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {

		int n = hi - lo + 1;
		int m = median3(a, lo, lo + n / 2, hi); // 计算中位数
		exch(a, m, lo); // 将中位数上的元素与lo交换

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

	private static <T extends Comparable<? super T>> int median3(T[] a, int i, int j, int k) {
		return (less(a[i], a[j]) ? (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i)
				: (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
	}

	private static <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}
}
