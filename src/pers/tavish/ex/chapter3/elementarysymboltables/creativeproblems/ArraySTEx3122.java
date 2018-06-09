package pers.tavish.ex.chapter3.elementarysymboltables.creativeproblems;

// 提高题3.1.22
public class ArraySTEx3122<Key extends Comparable<? super Key>, Value> {
	private Key keys[];
	private Value vals[];
	private int N;

	@SuppressWarnings("unchecked")
	public ArraySTEx3122(int capacity) {
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
		for (int i = 0; i < N; i++) {
			if (keys[i].compareTo(key) == 0) {
				
				// 将命中的键值对移动到数组开头
				Key k = keys[i];
				Value v = vals[i];
				for (int j = i; j > 0; j--) {
					keys[j] = keys[j - 1];
					vals[j] = vals[j - 1];
				}
				keys[0] = k;
				vals[0] = v;
				
				return v;
			}
		}
		return null;
	}

	public void put(Key key, Value val) {
		for (int i = 0; i < N; i++) {
			if (keys[i].compareTo(key) == 0) {
				vals[i] = val;
				return;
			}
		}
		if (N == keys.length) {
			resize(2 * keys.length);
		}
		keys[N] = key;
		vals[N] = val;
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
}
