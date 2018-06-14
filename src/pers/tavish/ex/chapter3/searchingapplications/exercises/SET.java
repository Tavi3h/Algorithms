package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 练习3.5.1
// 用有序符号表（红黑树）实现的集合，删除对值操作的代码
public class SET<Key extends Comparable<? super Key>> implements Iterable<Key> {

	private static final int DUMMY = 0;

	private RedBlackBST<Key, Integer> rbbst;

	public SET() {
		rbbst = new RedBlackBST<>();
	}

	public void add(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called add() with a null key");
		}
		rbbst.put(key, DUMMY);
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called contains() with a null key");
		}
		return rbbst.contains(key);
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called delete() with a null key");
		}
		rbbst.delete(key);
	}

	public void deleteMax() {
		rbbst.deleteMax();
	}

	public void deleteMin() {
		rbbst.deleteMin();
	}

	public int size() {
		return rbbst.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<Key> iterator() {
		return rbbst.keys().iterator();
	}

	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("called max() with empty set");
		}
		return rbbst.max();
	}

	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("called min() with empty set");
		}
		return rbbst.min();
	}

	public Key ceiling(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called ceiling() with a null key");
		}
		Key k = rbbst.ceiling(key);
		if (k == null) {
			throw new NoSuchElementException("all keys are less than " + key);
		}
		return k;
	}

	public Key floor(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called floor() with a null key");
		}
		Key k = rbbst.floor(key);
		if (k == null) {
			throw new NoSuchElementException("all keys are greater than " + key);
		}
		return k;
	}

	/*
	 * 并集操作
	 */
	public SET<Key> union(SET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		SET<Key> c = new SET<>();
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
	public SET<Key> intersects(SET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		SET<Key> c = new SET<>();
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

	/*
	 * 不支持hashCode()方法
	 */
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
		SET<Key> other = (SET<Key>) obj;
		if (rbbst == null) {
			if (other.rbbst != null) {
				return false;
			}
		} else if (!rbbst.equals(other.rbbst)) {
			return false;
		}
		return true;
	}
}
