package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

// 练习题3.1.2 无序数组实现符号表
public class ArrayST<Key extends Comparable<? super Key>, Value> {

	private Key keys[];
	private Value vals[];
	private int N;

	@SuppressWarnings("unchecked")
	public ArrayST(int capacity) {
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
				return vals[i];
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
