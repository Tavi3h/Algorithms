package pers.tavish.ex.chapter2.quicksort.creativeproblems;

import edu.princeton.cs.algs4.StdRandom;

class QuickTEx2316 {

	public static <T extends Comparable<? super T>> boolean sort(T[] a) {
		// 打乱数组，消除对输入的依赖
		// StdRandom.shuffle(a);
		return sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> boolean sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			// 正常情况，返回true
			return true;
		}
		int j = partition(a, lo, hi); // 将数组切分
		// 如果两个子数组长度差超过1，返回false
		if (Math.abs(hi + lo - 2 * j) > 1) {
			return false;
		}
		return sort(a, lo, j - 1) && sort(a, j + 1, hi);
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

public class Ex2316 {

	public static void main(String[] args) {

		// 以N = 20为例
		Integer[] arr = new Integer[8];
		Integer[] backup = new Integer[8];

		while (true) {
			// 对arr赋值，并确保无重复元素
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i;
			}
			// 打乱arr
			StdRandom.shuffle(arr);
			// 将arr复制到backup
			System.arraycopy(arr, 0, backup, 0, arr.length);
			// 如果对arr的排序返回true，则说明此时arr数组满足要求
			if (QuickTEx2316.sort(arr)) {
				break;
			}
		}

		// 此时打印出的backup数组即为满足要求的数组
		for (Integer i : backup) {
			System.out.print(i + " ");
		}
	}
}
