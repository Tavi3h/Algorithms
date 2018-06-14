package pers.tavish.code.chapter2.priorityqueues;

// 堆排序
// 数组的0角标不能参与排序
public class HeapSortT {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length - 1;
		// 堆的构造，最终得到有序堆
		for (int k = N / 2; k >= 1; k--) {
			sink(a, k, N);
		}
		// 在有序堆的基础上进行下沉排序
		while (N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}

	public static <T extends Comparable<? super T>> void sink(T[] a, int i, int N) {
		/*
		 * 一个三元素堆，堆顶元素为i，两个堆底元素分别为j和j+1
		 */
		while (2 * i <= N) {
			int j = i * 2;
			// 找出最大的堆底元素的角标
			if (j < N && less(a[j], a[j + 1])) {
				j++;
			}
			// 判断堆顶元素i是否比堆底元素的最大元素大
			if (!less(a[i], a[j])) {
				// 堆顶元素i大于两个堆底元素的最大元素
				break;
			}
			// 堆顶元素i小于堆底元素的最大元素，进行交换
			exch(a, i, j);
			i = j;
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

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 3, 4, 6, 1, 2, 7, 0, 5 };
		sort(arr);
		for (Integer i : arr) {
			System.out.print(arr[i] + " ");
		}
	}
}
