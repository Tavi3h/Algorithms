package pers.tavish.code.chapter1.bagsqueuesandstacks;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 完整的实现，参见algs4.jar
public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] a; // stack entries
	private int N; // size

	@SuppressWarnings("unchecked")
	public ResizingArrayStack(int cap) {
		this.a = (Item[]) new Object[cap];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(Item item) {
		if (N == a.length) {
			resize(2 * a.length);
		}
		a[N++] = item;
	}

	public Item pop() {
		Item item = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length / 4) {
			resize(a.length / 2);
		}
		return item;
	}

	private void resize(int max) {
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	public static void main(String[] args) {
		ResizingArrayStack<String> s = new ResizingArrayStack<>(100);
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				s.push(item);
			} else if (!s.isEmpty()) {
				StdOut.println(s.pop() + " ");
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}

	@Override
	public Iterator<Item> iterator() {

		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item> {

		private int i = N;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Item next() {
			return a[--i];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
