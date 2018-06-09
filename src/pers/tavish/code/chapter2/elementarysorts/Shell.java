package pers.tavish.code.chapter2.elementarysorts;

import edu.princeton.cs.algs4.StdIn;

// 希尔排序
public class Shell {

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
		return v.compareTo((T) w) < 0;
	}

	private static <T> void exch(Comparable<T>[] a, int i, int j) {
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T> void show(Comparable<T>[] a) {
		// 在单行中打印数组
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static <T> boolean isSorted(Comparable<T>[] a) {
		// 测试数组元素是否有序
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// 从标准输入读取字符串，将它们排序并输出
		String[] a = StdIn.readAllStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
