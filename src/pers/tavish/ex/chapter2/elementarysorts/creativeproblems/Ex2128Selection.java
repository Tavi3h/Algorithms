package pers.tavish.ex.chapter2.elementarysorts.creativeproblems;

import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ex2128Selection {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length; // 数组长度
		for (int i = 0; i < N; i++) {
			int min = i; // 最小元素索引
			for (int j = i + 1; j < N; j++) {
				// 在a[i + 1]...a[N - 1]之间查找比a[min]小的元素
				if (less(a[j], a[min])) {
					// 如果找到，则重新记录最小元素索引
					min = j;
				}
			}
			// 内循环结束，交换i与min的值
			show(a, i, min);
			exch(a, i, min);
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

	private static <T> void show(T[] a, int i, int min) {
		StdDraw.clear();
		int n = a.length;
		for (int k = 0; k < n; k++) {
			if (k < i) {
				StdDraw.setPenColor(StdDraw.GRAY);
			} else if (k == min) {
				StdDraw.setPenColor(StdDraw.RED);
			} else if ((k >= i && k < min) || k > min ){
				StdDraw.setPenColor(StdDraw.BLACK);
			}
			double x = 1.0 * k / n;
			double y = (double) a[k] / 2.0;
			double rw = 0.5 / n;
			double rh = (double) a[k] / 2.0;
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
