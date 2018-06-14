package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Iterator;

import pers.tavish.code.chapter3.hashtables.LinearProbingHashST;

// 练习3.5.1
// 用无序符号表（线性探测散列表）实现的集合
public class HashSET<Key> implements Iterable<Key> {

	private static final int DUMMY = 0;

	private LinearProbingHashST<Key, Integer> lphst;
	
	public HashSET() {
		lphst = new LinearProbingHashST<>();
	}

	public void add(Key key) { 
		if (key == null) { 
			throw new IllegalArgumentException("called add() with a null key");
		}
		lphst.put(key, DUMMY);
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called contains() with a null key");
		}
		return lphst.contains(key);
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called delete() with a null key");
		}
		lphst.delete(key);
	}

	public int size() {
		return lphst.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<Key> iterator() {
		return lphst.keys().iterator();
	}

	/*
	 * 并集操作
	 */
	public HashSET<Key> union(HashSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		HashSET<Key> c = new HashSET<>();
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
	public HashSET<Key> intersects(HashSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		HashSET<Key> c = new HashSET<>();
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
		HashSET<Key> other = (HashSET<Key>) obj;
		if (lphst == null) {
			if (other.lphst != null) {
				return false;
			}
		} else if (!lphst.equals(other.lphst)) {
			return false;
		}
		return true;
	}
}
