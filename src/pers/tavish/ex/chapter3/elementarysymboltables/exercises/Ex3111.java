package pers.tavish.ex.chapter3.elementarysymboltables.exercises;


class BinarySearchSTEx3111<Key extends Comparable<? super Key>, Value> {

	private Key[] keys;
	private Value[] vals;
	private int N;

	private int count;

	public int getCount() {
		return count;
	}

	@SuppressWarnings("unchecked")
	public BinarySearchSTEx3111(int capacity) {
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
			count++;
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
		int i = rank(key);
		count++;
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
		show();
	}

	public void show() {
		for (int i = 0; i < N; i++) {
			System.out.print(keys[i] + " ");
		}
		System.out.println();
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

public class Ex3111 {
	
	public static void main(String[] args) {
		BinarySearchSTEx3111<String, Integer> st = new BinarySearchSTEx3111<>(20);
		String string = "E A S Y Q U E S T I O N";
		for (String s : string.split(" ")) {
			st.put(s, 0);
		}
		System.out.println(st.getCount());
	}
}
