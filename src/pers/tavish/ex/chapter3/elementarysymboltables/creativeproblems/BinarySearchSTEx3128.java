package pers.tavish.ex.chapter3.elementarysymboltables.creativeproblems;

import edu.princeton.cs.algs4.Queue;

// 提高题3.1.28
public class BinarySearchSTEx3128<Key extends Comparable<? super Key>, Value> {
	
	private Key[] keys;
	private Value[] vals;
	private int N;

	@SuppressWarnings("unchecked")
	public BinarySearchSTEx3128(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Value get(Key key) {
		if (isEmpty()) {
			return null;
		}
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		}
		return null;
	}

	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
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

	public void put(Key key, Value val) {
		
		// 如果最大键比key小，此时直接将key-val插入到位置N
		if (keys[N - 1].compareTo(key) < 0) {
			// 判断是否需要resize
			if (N == keys.length) {
				resize(2 * keys.length);
			}
			keys[N] = key;
			vals[N] = val;
			N++;
			return;
		}
		
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
		Key[] tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[N - 1];
	}

	public Key select(int k) {
		return keys[k];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}

	// 练习3.1.17
	public Key floor(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to floor() is null");
		}
		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) == 0) {
			return keys[i];
		}
		if (i == 0) {
			return null;
		} else {
			return keys[i - 1];
		}
	}

	// 练习3.1.16
	public void delete(Key key) {
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

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<>();
		for (int i = rank(lo); i < rank(hi); i++) {
			q.enqueue(keys[i]);
		}
		if (contains(hi)) {
			q.enqueue(keys[rank(hi)]);
		}
		return q;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			sb.append("[" + keys[i] + ", " + vals[i] + "] ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
