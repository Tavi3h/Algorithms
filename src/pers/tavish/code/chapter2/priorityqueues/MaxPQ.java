package pers.tavish.code.chapter2.priorityqueues;


public class MaxPQ<Key extends Comparable<? super Key>> {

	private Key[] pq; // 基于堆的完全二叉树
	private int N = 0; // 存储与pq[1...N]中
	private Key min; // 该优先队列的最小元素

	@SuppressWarnings("unchecked")
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN + 1];
	}

	@SuppressWarnings("unchecked")
	public MaxPQ(Key[] arr) {
		pq = (Key[]) new Comparable[arr.length + 1];
		min = arr[0];
		for (int i = 0; i < arr.length; i++) {
			min = arr[i].compareTo(min) < 0 ? arr[i] : min;
			pq[i + 1] = arr[i];
			swim(i + 1);
		}
		N = arr.length;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Key min() {
		return min;
	}

	public void insert(Key v) {

		if (N == pq.length - 1) {
			resize(pq.length * 2);
		}
		
		min = (min == null ? v : (min.compareTo(v) < 0 ? min : v));

		pq[++N] = v;
		swim(N);
	}

	public Key delMax() {
		
		if (N == 0) {
			throw new UnsupportedOperationException("MaxPQ Underflow...");
		}
		
		Key max = pq[1]; // 从根结点得到最大元素
		exch(1, N--); // 将其和最后一个结点交换
		pq[N + 1] = null; // 防止对象游离
		sink(1); // 恢复堆的有序性
		if (N < pq.length / 4) {
			resize(pq.length / 2);
		}
		if (N == 0) {
			min = null;
		}
		return max;
	}

	// 调整pq的长度到size
	@SuppressWarnings("unchecked")
	private void resize(int size) {
		Key[] tmp = (Key[]) new Comparable[size];
		for (int i = 1; i <= N; i++) {
			tmp[i] = pq[i];
		}
		pq = tmp;
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1)) {
				j++;
			}
			if (!less(k, j)) {
				break;
			}
			exch(k, j);
			k = j;
		}
	}

	public void show() {
		for (Key key : pq) {
			if (key == null) {
				continue;
			}
			System.out.print(key + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
	}
}
