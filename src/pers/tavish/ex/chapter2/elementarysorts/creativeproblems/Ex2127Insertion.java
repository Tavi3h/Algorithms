package pers.tavish.ex.chapter2.elementarysorts.creativeproblems;

import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ex2127Insertion {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// 将a[i]插入到a[i - 1]、a[i - 2]、a[i - 3]...之中
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
				show(a);
			}
		}
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	private static <T> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T> void show(T[] a) {
		StdDraw.clear();
		int n = a.length;
		for (int i = 0; i < n; i++) {
			double x = 1.0 * i / n;
			double y = (double) a[i] / 2.0;
			double rw = 0.5 / n;
			double rh = (double) a[i] / 2.0;
			// x 代表线在那个位置 y代表这个线的高度
			// rw rh 理解成平面坐标参数
			StdDraw.filledRectangle(x, y, rw, rh);
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		// 测试数组元素是否有序
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int n = 50;
		Double[] doubles = new Double[n];
		for (int i = 0; i < n; i++) {
			// 用随机函数生成随机数
			doubles[i] = StdRandom.uniform();
		}
		sort(doubles);
	}
}
