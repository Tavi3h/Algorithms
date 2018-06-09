package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

import edu.princeton.cs.algs4.Merge;

// 练习题 3.1.12
public class BinarySearchSTItem<Key extends Comparable<? super Key>, Value> {

	private Item<Key, Value>[] entry;

	private int N;

	private static class Item<Key extends Comparable<? super Key>, Value> implements Comparable<Item<Key, Value>> {

		Key key;
		Value value;

		public Item(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "[" + key + ", " + value + "]";
		}

		@Override
		public int compareTo(Item<Key, Value> o) {
			return key.compareTo(o.key);
		}
	}

	@SuppressWarnings("unchecked")
	public BinarySearchSTItem(int capacity) {
		this.entry = new Item[capacity];
	}

	public BinarySearchSTItem(Item<Key, Value>[] arr) {
		Merge.sort(arr);
		entry = arr;
		N = arr.length;
	}

	public void show() {
		for (Item<Key, Value> item : entry) {
			if (item != null) {
				System.out.print(item + " ");
			}
		}
		System.out.println();
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
		if (i < N && entry[i].key.compareTo(key) == 0) {
			return entry[i].value;
		}
		return null;
	}

	public void put(Key key, Value val) {
		int i = rank(key);
		if (i < N && entry[i].key.compareTo(key) == 0) {
			entry[i].value = val;
			return;
		}

		if (N == entry.length) {
			resize(2 * entry.length);
		}

		for (int j = N; j > i; j--) {
			entry[j] = entry[j - 1];
		}
		entry[i] = new Item<>(key, val);
		N++;
	}

	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(entry[mid].key);
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
		Item<Key, Value>[] temp = new Item[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = entry[i];
		}
		entry = temp;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		Item<String, Integer>[] items = new Item[] {
				new Item<>("D", 1),
				new Item<>("E", 1),
				new Item<>("C", 1),
				new Item<>("B", 1),
				new Item<>("F", 1),
				};
		
		BinarySearchSTItem<String, Integer> bssti = new BinarySearchSTItem<>(items);
		bssti.show();
		bssti.put("A", 2);
		bssti.show();
		bssti.put("F", 100);
		bssti.show();
	}
}