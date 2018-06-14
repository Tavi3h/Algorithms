package pers.tavish.ex.chapter2.quicksort.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

class QuickTEx238 {

	private static int cnt; // 记录比较次数

	public static int getCnt() {
		return cnt;
	}

	public static void resetCnt() {
		cnt = 0;
	}

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 打乱数组，消除对输入的依赖
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
		// 将数组分为a[lo...i-1]，a[i]，a[i+1...hi]
		int i = lo, j = hi + 1;
		T v = a[lo];
		while (true) {
			// 扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v)) {
				cnt++;
				if (i == hi) {
					cnt--;
					break;
				}
			}
			cnt++;
			while (less(v, a[--j])) {
				cnt++;
				if (j == lo) {
					cnt--;
					break;
				}
			}
			cnt++;
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

public class Ex238SameElm {
	public static void main(String[] args) {
		int N = StdIn.readInt();
		Integer[] arr = new Integer[N];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}

		QuickTEx238.sort(arr);
		int cnt = QuickTEx238.getCnt();
		System.out.println("Cn = " + cnt);
		QuickTEx238.resetCnt();
	}
}
