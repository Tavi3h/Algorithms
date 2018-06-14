package pers.tavish.code.chapter2.elementarysorts;

import edu.princeton.cs.algs4.StdIn;

// 选择排序
public class SelectionT {

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
