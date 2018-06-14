package pers.tavish.ex.chapter2.priorityqueues.exercises;

// 练习题2.4.3 无序数组实现优先队列
public class PQUnsortedArray<T extends Comparable<? super T>> {

	private int N; // 元素个数
	private T[] arr; // 用于保存元素的数组
	private static final int DEFAULT_SIZE = 10; // 默认数组长度

	// public PQUnsortedArray(Class<T> type) {
	// this(type, DEFAULT_SIZE);
	// }
	//
	// @SuppressWarnings("unchecked")
	// public PQUnsortedArray(Class<T> type, int size) {
	// arr = (T[]) Array.newInstance(type, size);
	// }

	public PQUnsortedArray() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public PQUnsortedArray(int capacity) {
		arr = (T[]) new Comparable[capacity];
	}

	// 插入一个元素
	// 时间复杂度O(1)
	public void insert(T t) {
		if (N == arr.length) {
			throw new UnsupportedOperationException("Array Overflow..");
		}

		arr[N++] = t;
	}

	// 删除并返回最大元素
	// 由于getMaxIndex()方法，时间复杂度O(N)
	public T delMax() {
		if (N == 0) {
			throw new UnsupportedOperationException("Array Underflow..");
		}

		int idx = getMaxIndex();

		T t = arr[idx]; // 得到最大元素
		exch(idx, --N); // 将最大元素与末尾元素交换
		arr[N] = null; // 防止对象游离

		return t;

	}

	// 获取最大元素的角标
	// 时间复杂度O(N)
	private int getMaxIndex() {

		if (N == 1) {
			return 0;
		}

		int maxIdx = 0;

		for (int i = 1; i < N; i++) {
			if (less(maxIdx, i)) {
				maxIdx = i;
			}
		}

		return maxIdx;
	}

	private boolean less(int i, int j) {
		return arr[i].compareTo(arr[j]) < 0;
	}

	private void exch(int i, int j) {
		T t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public T[] getArr() {
		return arr;
	}
}
