package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;

// 练习题3.5.3
// 删除BinarySearchST（有序数组符号表）中关于值的操作以实现集合
public class BinarySearchSET<Key extends Comparable<? super Key>> implements Iterable<Key> {

	private static final int DEFAULT_CAPACITY = 10;
	private Key[] keys;
	private int N;
	
	@SuppressWarnings("unchecked")
	public BinarySearchSET(int capacity) {
		keys = (Key[]) new Comparable[capacity];
	}
	
	public BinarySearchSET() {
		this(DEFAULT_CAPACITY);
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0; 
	}
	
	public void add(Key key) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return;
		}

		if (N == keys.length) {
			resize(2 * keys.length);
		}

		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
		}
		keys[i] = key;
		N++;
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
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Key[] tempk = (Key[]) new Comparable[capacity];
		for (int i = 0; i < N; i++) {
			tempk[i] = keys[i];
		}
		keys = tempk;
	}
	
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		if (isEmpty()) {
			return false;
		}
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return true;
		}
		return false;
	}
	
	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[N - 1];
	}

	public Key select(int k) {
		return k < 0 || k > N - 1 ? null : keys[k];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return i == N ? null : keys[i];
	}
	
	public Key floor(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to floor() is null");
		}
		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) == 0) {
			return keys[i];
		}
		return i == 0 ? null : keys[i - 1];
	}
	
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
		}

		N--;
		keys[N] = null; // to avoid loitering

		// resize if 1/4 full
		if (N > 0 && N == keys.length / 4) {
			resize(keys.length / 2);
		}
	}
	
	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}
	
	public Iterable<Key> keys() {
		
		Key lo = keys[0];
		Key hi = keys[N - 1];
		
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
	public Iterator<Key> iterator() {
		return keys().iterator();
	}
	
	/*
	 * 并集操作
	 */
	public BinarySearchSET<Key> union(SET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		BinarySearchSET<Key> c = new BinarySearchSET<Key>();
		for (Key x : this) {
			c.add(x);
		}
		for (Key x : that) {
			c.add(x);
		}
		return c;
	}
	
	/*
	 * 交集操作
	 */
	public BinarySearchSET<Key> intersects(BinarySearchSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		BinarySearchSET<Key> c = new BinarySearchSET<>();
		if (this.size() < that.size()) {
			for (Key x : this) {
				if (that.contains(x)) {
					c.add(x);
				}
			}
		} else {
			for (Key x : that) {
				if (this.contains(x)) {
					c.add(x);
				}
			}
		}
		return c;
	}

	@Override
	public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BinarySearchSET<Key> other = (BinarySearchSET<Key>) obj;
		if (N != other.N) {
			return false;
		}
		if (!Arrays.equals(keys, other.keys)) {
			return false;
		}
		return true;
	}
}
