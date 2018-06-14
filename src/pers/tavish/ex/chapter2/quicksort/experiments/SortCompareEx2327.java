package pers.tavish.ex.chapter2.quicksort.experiments;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

// 实验题2.3.27
class QuickTIgnoreSubarray {

	private static final int INSERTION_SORT_CUTOFF = 8;

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 打乱数组，消除对输入的依赖
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
		insertionSort(a); // 快排之后运行一次插入排序
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}

		// 判断是否为小数组
		int n = hi - lo + 1;
		if (n <= INSERTION_SORT_CUTOFF) {
			// 忽略小数组
			return;
		}

		int j = partition(a, lo, hi); // 将数组切分
		sort(a, lo, j - 1); // 排序左半边
		sort(a, j + 1, hi); // 排序右半边
	}

	// 插入排序，排序[lo...hi)
	private static <T extends Comparable<? super T>> void insertionSort(T[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// 将a[i]插入到a[i - 1]、a[i - 2]、a[i - 3]...之中
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
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
}

public class SortCompareEx2327 {
	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("QuickTIgnoreSubarray")) {
			QuickTIgnoreSubarray.sort(a);
		}
		if (alg.equals("QuickTWithInsertion")) {
			QuickTWithInsertion.sort(a);
		}
		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, Double[] a) {
		return time(alg, a);
	}

	public static void main(String[] args) {

		String alg1 = "QuickTWithInsertion";
		String alg2 = "QuickTIgnoreSubarray";

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
