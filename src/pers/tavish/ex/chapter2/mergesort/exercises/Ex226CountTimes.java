package pers.tavish.ex.chapter2.mergesort.exercises;

import java.lang.reflect.Array;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ex226CountTimes {

	public static void main(String[] args) {

		Double[] arr = null;
		Double[] backup = null;

		StdDraw.setXscale(0, 600);
		StdDraw.setYscale(0, 6 * 512 * Math.log10(512) / Math.log10(2));
		StdDraw.setPenRadius(.005);

		for (int i = 1; i <= 512; i++) {
			// 创建数组
			arr = new Double[i];
			backup = new Double[i];
			for (int j = 0; j < i; j++) {
				arr[j] = StdRandom.uniform();
			}
			System.arraycopy(arr, 0, backup, 0, i);

			// 排序并统计访问数组次数
			MergeTD.sort(arr);
			MergeBU.sort(backup);
			int cntTD = MergeTD.getCnt();
			int cntBU = MergeBU.getCnt();

			// 输出访问次数
			System.out.println("i = " + i + ", cntTD = " + cntTD + ", cntBU = " + cntBU);

			// 画图
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.point(i, cntTD);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.point(i, cntBU);

			// 重置cnt
			MergeTD.resetCnt();
			MergeBU.resetCnt();
		}
		System.out.println("理论访问数组次数：" + 6 * 512 * Math.log10(512) / Math.log10(2));
	}
}

class MergeBU {

	private static int cnt;

	public static int getCnt() {
		return cnt;
	}

	public static void resetCnt() {
		cnt = 0;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 进行lgN次两两归并
		int N = a.length;
		T[] aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		for (int sz = 1; sz < N; sz = sz + sz) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {

		// 将a[lo...hi]复制到aux[lo...hi]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		cnt += 2 * (hi - lo + 1);

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
			cnt += 2;
		}
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		cnt += 2;
		return v.compareTo(w) < 0;
	}
}

class MergeTD {

	private static int cnt;

	public static int getCnt() {
		return cnt;
	}

	public static void resetCnt() {
		cnt = 0;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		T[] aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		sort(a, aux, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int lo, int hi) {
		// 将数组a[lo...hi]排序
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
		// 将a[lo...mid]和a[mid + 1...hi]归并
		int i = lo;
		int j = mid + 1;

		// 将a[lo...hi]复制到aux[lo...hi]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		cnt += 2 * (hi - lo + 1);

		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
			cnt += 2;
		}
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		cnt += 2;
		return v.compareTo(w) < 0;
	}
}
