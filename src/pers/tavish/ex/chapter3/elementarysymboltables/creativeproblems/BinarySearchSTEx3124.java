package pers.tavish.ex.chapter3.elementarysymboltables.creativeproblems;

import edu.princeton.cs.algs4.StdRandom;

// 提高题 3.1.24，Key的类型直接使用Integer
public class BinarySearchSTEx3124<Value> {
	private Integer[] keys;
	private Value[] vals;
	private int N;

	@SuppressWarnings("unchecked")
	public BinarySearchSTEx3124(int capacity) {
		keys = new Integer[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Value get(Integer key) {
		if (isEmpty()) {
			return null;
		}
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		}
		return null;
	}

	// 插值查找
	public int rank(Integer key) {

		int lo = 0, hi = N - 1;

		while (lo <= hi) {

			// 如果lo==hi，直接令mid=lo
			int mid = (lo == hi ? lo : lo + (key - keys[lo]) / (keys[hi] - keys[lo]) * (hi - lo));

			// 如果mid经计算不在lo~hi范围，则取mid为lo和hi的中间值
			// 同时防止数组角标越界
			if (mid < lo || mid > hi) {
				System.out.println(mid);
				mid = lo + (hi - lo) / 2;
			}

			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	public void put(Integer key, Value val) {

		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}

		if (N == keys.length) {
			resize(2 * keys.length);
		}

		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Integer[] tempk = new Integer[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
	}

	// 练习3.1.16
	public void delete(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		if (isEmpty()) {
			return;
		}

		// compute rank
		int i = rank(key);

		// key not in table
		if (i == N || keys[i].compareTo(key) != 0) {
			return;
		}

		for (int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}

		N--;
		keys[N] = null; // to avoid loitering
		vals[N] = null;

		// resize if 1/4 full
		if (N > 0 && N == keys.length / 4) {
			resize(keys.length / 2);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			sb.append("[" + keys[i] + ", " + vals[i] + "]\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		BinarySearchSTEx3124<Integer> st = new BinarySearchSTEx3124<>(10);
		for (int i = 0; i < 100; i++) {
			st.put(StdRandom.uniform(-100, 100), i);
		}
		System.out.println(st);
		System.out.println(st.get(80));
	}
}
