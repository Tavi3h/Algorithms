package pers.tavish.ex.chapter2.elementarysorts.creativeproblems;

import java.util.Arrays;

// 提高题 2.1.16
public class Ex2116 {

	// 检查Arrays.sort()方法
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> boolean checkArraySort(T[] a) {

		// 创建一个与a等长的Object数组
		Object[] backup = new Object[a.length];

		// 将数组的每一个元素转为T
		for (int i = 0; i < backup.length; i++) {
			backup[i] = (T) backup[i];
		}

		// 拷贝a数组的元素到backup数组
		System.arraycopy(a, 0, backup, 0, a.length);

		// 对数组a进行排序
		Arrays.sort(a);

		// 检查a数组中的每个元素是否与backup数组中的元素对应
		labelA: for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < backup.length; j++) {
				// 如果查到对应元素，则跳出内层for循环，继续外层循环
				if (a[i] == backup[j]) {
					continue labelA;
				}
			}
			// 内循环正常结束，说明没有找到对应元素
			return false;
		}
		return true;
	}

	// 检查选择排序
	public static boolean checkSelectionSort(String[] a) {
		String[] backup = new String[a.length];
		System.arraycopy(a, 0, backup, 0, a.length);
		sort(a);

		labelA: for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < backup.length; j++) {
				// 如果查到对应元素，则跳出内层for循环，继续外层循环
				if (a[i] == backup[j]) {
					continue labelA;
				}
			}
			// 内循环正常结束，说明没有找到对应元素
			return false;
		}
		return true;
	}

	// 用于对字符串进行排序的选择排序
	public static void sort(String[] a) {
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

	private static boolean less(String v, String w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(String[] a, int i, int j) {
		// 创建一个新的String对象
		String t = new String(a[i]);
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		String[] test = new String[] { "d", "c", "b", "a" };
		String[] test2 = new String[] { "d", "c", "b", "a" };
		System.out.println(checkArraySort(test));
		System.out.println(checkSelectionSort(test2));
	}
}
