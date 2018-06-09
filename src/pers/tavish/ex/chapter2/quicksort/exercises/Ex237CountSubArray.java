package pers.tavish.ex.chapter2.quicksort.exercises;

import edu.princeton.cs.algs4.StdRandom;

class QuickTEx237 {

	// 统计长度为0、1、2的子数组的个数
	private static int cnt0;
	private static int cnt1;
	private static int cnt2;

	public static int getCnt0() {
		return cnt0;
	}

	public static int getCnt1() {
		return cnt1;
	}

	public static int getCnt2() {
		return cnt2;
	}
	
	public static void resetCnt() {
		cnt0 = 0;
		cnt1 = 0;
		cnt2 = 0;
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

		int subArr1length = j - lo;
		if (subArr1length == 0) {
			cnt0++;
		} else if (subArr1length == 1) {
			cnt1++;
		} else if (subArr1length == 2) {
			cnt2++;
		}

		int subArr2length = hi - j;
		if (subArr2length == 0) {
			cnt0++;
		} else if (subArr2length == 1) {
			cnt1++;
		} else if (subArr2length == 2) {
			cnt2++;
		}

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

	public static void main(String[] args) {
		Integer[] a = { 5, 2, 1, 3, 4, 0 };
		sort(a);
		for (Integer integer : a) {
			System.out.print(integer + " ");
		}
	}
}

public class Ex237CountSubArray {
	public static void main(String[] args) {

		for (int N = 100; N <= 1000000; N *= 10) {
			// 创建N个不重复的元素
			Integer[] arr = new Integer[N];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i;
			}
			StdRandom.shuffle(arr);

			QuickTEx237.sort(arr);
			System.out.println("数组长度为" + N + "大小为0的子数数量：" + QuickTEx237.getCnt0());
			System.out.println("数组长度为" + N + "大小为1的子数数量：" + QuickTEx237.getCnt1());
			System.out.println("数组长度为" + N + "大小为2的子数数量：" + QuickTEx237.getCnt2());

			QuickTEx237.resetCnt();
			System.out.println();
		}
	}
}
