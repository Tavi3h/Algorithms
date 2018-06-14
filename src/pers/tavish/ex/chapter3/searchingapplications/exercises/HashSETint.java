package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Iterator;

// 练习题3.5.6
public class HashSETint implements Iterable<Integer> {

	private LinearProbingHashSTint lphstint;

	public HashSETint(int capacity) {
		lphstint = new LinearProbingHashSTint(capacity);
	}

	public HashSETint() {
		lphstint = new LinearProbingHashSTint();
	}

	public void add(int key) {
		lphstint.put(key);
	}

	public boolean contains(int key) {
		return lphstint.contains(key);
	}

	public void delete(int key) {
		lphstint.delete(key);
	}

	public int size() {
		return lphstint.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<Integer> iterator() {
		return lphstint.keys().iterator();
	}

	/*
	 * 并集操作
	 */
	public HashSETint union(HashSETint that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		HashSETint c = new HashSETint();
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
	public HashSETint intersects(HashSETint that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		HashSETint c = new HashSETint();
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
		HashSETint other = (HashSETint) obj;
		if (lphstint == null) {
			if (other.lphstint != null) {
				return false;
			}
		} else if (!lphstint.equals(other.lphstint)) {
			return false;
		}
		return true;
	}
}
