package pers.tavish.ex.chapter2.elementarysorts.exercises;

import edu.princeton.cs.algs4.StdRandom;

// 练习题 2.1.12
public class ShellEx2112 {

	private static int cnt;

	public static <T> void sort(Comparable<T>[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			// 将数组变为h有序
			for (int i = h; i < N; i++) {
				// 将a[i]插入到a[i - h]、a[i - 2 * h]、a[i - 3 * h]...之中
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
			h = h / 3;
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
		cnt++;
		return v.compareTo((T) w) < 0;
	}

	private static <T> void exch(Comparable<T>[] a, int i, int j) {
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static int getCnt() {
		return cnt;
	}

	public static void resetCnt() {
		cnt = 0;
	}

	public static void main(String[] args) {
		int size = 100;
		for (int i = 0; i < 5; i++) {
			System.out.print("实验 " + i);
			Double[] a = new Double[size];
			for (int j = 0; j < size; j++) {
				a[j] = StdRandom.uniform() * 100;
			}
			System.out.println(", ArraySize: " + size);
			sort(a);
			System.out.printf("%s %5.3f\n", "cnt/size = ", (double) getCnt() / size);
			resetCnt();
			size *= 10;
		}
	}
}
