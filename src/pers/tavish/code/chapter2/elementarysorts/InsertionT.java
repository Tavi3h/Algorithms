package pers.tavish.code.chapter2.elementarysorts;

import edu.princeton.cs.algs4.StdIn;

// 插入排序
public class InsertionT {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// 将a[i]插入到a[i - 1]、a[i - 2]、a[i - 3]...之中
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
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
		// 在单行中打印数组
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
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
		// 从标准输入读取字符串，将它们排序并输出
		String[] a = StdIn.readAllStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
