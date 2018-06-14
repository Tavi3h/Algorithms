package pers.tavish.ex.chapter1.bagsqueuesandstacks.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 习题1.3.8
public class DoublingStackOfStrings implements Iterable<String> {
	private String[] a;
	private int N;

	public DoublingStackOfStrings() {
		a = new String[2];
		N = 0;
	}

	public boolean isEmpty() {
		return (N == 0);
	}

	private void resize(int capacity) {
		String[] temp = new String[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	public void push(String item) {
		if (N == a.length) {
			resize(2 * a.length);
		}
		a[N++] = item;
	}

	public String pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack underflow error");
		}
		String item = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length / 4) {
			resize(a.length / 2);
		}
		return item;
	}

	public Iterator<String> iterator() {
		return new ReverseArrayIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ReverseArrayIterator implements Iterator<String> {
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return a[--i];
		}
	}

	public static void main(String[] args) {
		DoublingStackOfStrings s = new DoublingStackOfStrings();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				s.push(item);
			} else if (s.isEmpty()) {
				StdOut.println("BAD INPUT");
			} else
				StdOut.print(s.pop() + " ");
		}
	}
}