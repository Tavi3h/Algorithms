package pers.tavish.ex.chapter2.priorityqueues.exercises;

import edu.princeton.cs.algs4.Insertion;

// 练习题2.4.3 有序数组实现优先队列
public class PQSortedArray<T extends Comparable<? super T>> {

	private int N; // 元素个数
	private T[] arr; // 用于保存元素的数组
	private static final int DEFAULT_SIZE = 10; // 默认数组长度

	// public PQSortedArray(Class<T> type) {
	// this(type, DEFAULT_SIZE);
	// }
	//
	// @SuppressWarnings("unchecked")
	// public PQSortedArray(Class<T> type, int size) {
	// arr = (T[]) Array.newInstance(type, size);
	// }

	public PQSortedArray() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public PQSortedArray(int capacity) {
		arr = (T[]) new Comparable[capacity];
	}

	// 插入一个元素
	// 由于每次插入元素都进行插入排序，所以插入排序的复杂度与最好情况类似，为O(N)
	public void insert(T t) {
		if (N == arr.length) {
			throw new UnsupportedOperationException("Array Overflow..");
		}

		arr[N++] = t;

		// 插入元素后对数组进行排序
		Insertion.sort(arr, 0, N);
	}

	// 删除并返回最大元素
	// 复杂度O(1)
	public T delMax() {
		if (N == 0) {
			throw new UnsupportedOperationException("Array Underflow..");
		}

		T t = arr[N - 1]; // 得到最大元素
		arr[--N] = null; // 防止对象游离
		return t;
	}
}
