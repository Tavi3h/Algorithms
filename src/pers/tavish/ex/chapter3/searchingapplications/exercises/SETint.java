package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

// 练习题3.5.7
public class SETint implements Iterable<Integer> {

	private RedBlackBSTint rbbstint;

	public SETint() {
		rbbstint = new RedBlackBSTint();
	}

	public void add(int key) {
		rbbstint.put(key);
	}

	public boolean contains(int key) {
		return rbbstint.contains(key);
	}

	public void delete(int key) {
		rbbstint.delete(key);
	}

	public void deleteMax() {
		rbbstint.deleteMax();
	}

	public void deleteMin() {
		rbbstint.deleteMin();
	}

	public int size() {
		return rbbstint.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<Integer> iterator() {
		return rbbstint.keys().iterator();
	}

	public int max() {
		if (isEmpty()) {
			throw new NoSuchElementException("called max() with empty set");
		}
		return rbbstint.max();
	}

	public int min() {
		if (isEmpty()) {
			throw new NoSuchElementException("called min() with empty set");
		}
		return rbbstint.min();
	}

	public int ceiling(int key) {
		int k = rbbstint.ceiling(key);
		if (k == Integer.MAX_VALUE) {
			throw new NoSuchElementException("all keys are less than " + key);
		}
		return k;
	}

	public int floor(int key) {
		int k = rbbstint.floor(key);
		if (k == Integer.MIN_VALUE) {
			throw new NoSuchElementException("all keys are greater than " + key);
		}
		return k;
	}

	/*
	 * 并集操作
	 */
	public SETint union(SETint that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		SETint c = new SETint();
		for (int x : this) {
			c.add(x);
		}
		for (int x : that) {
			c.add(x);
		}
		return c;
	}

	/*
	 * 交集操作
	 */
	public SETint intersects(SETint that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		SETint c = new SETint();
		if (this.size() < that.size()) {
			for (int x : this) {
				if (that.contains(x)) {
					c.add(x);
				}
			}
		} else {
			for (int x : that) {
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
		SETint other = (SETint) obj;
		if (rbbstint == null) {
			if (other.rbbstint != null) {
				return false;
			}
		} else if (!rbbstint.equals(other.rbbstint)) {
			return false;
		}
		return true;
	}
}
