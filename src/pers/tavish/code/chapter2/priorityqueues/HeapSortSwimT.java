package pers.tavish.code.chapter2.priorityqueues;

import java.lang.reflect.Array;

// 堆排序--使用swim()方法和一个辅助数组进行排序，时间和空间复杂度均比正常堆排序高
// 数组0角标可以参与排序
public class HeapSortSwimT {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		
		// 创建辅助数组
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + 1);
		
		// 使用swim()方法构造有序堆
		for (int i = 0; i < a.length; i++) {
			arr[i + 1] = a[i];
			swim(arr, i + 1);
		}
		
		// 在有序堆的基础上进行下沉排序
		int N = arr.length - 1;
		while (N > 1) {
			exch(arr, 1, N--);
			sink(arr, 1, N);
		}
		
		// 将排好序的辅助数组复制回数组a
		System.arraycopy(arr, 1, a, 0, a.length);
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

	private static <T extends Comparable<? super T>> void swim(T[] a, int k) {
		while (k > 1 && less(a[k / 2], a[k])) {
			exch(a, k / 2, k);
			k = k / 2;
		}
	}
	
	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 3, 4, 6, 1, 2, 7, 0, 5 };
		sort(arr);
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
	}
}
