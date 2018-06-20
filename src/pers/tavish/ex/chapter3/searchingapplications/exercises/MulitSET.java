package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

// 练习题 3.5.11
public class MulitSET<Key extends Comparable<? super Key>> implements Iterable<Key>{
	
	private static final int DUMMY = 0;
	
	private BSTEx359<Key, Integer> bst;
	

	public MulitSET() {
		bst = new BSTEx359<>();
	}
	
	public void add(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called add() with a null key");
		}
		bst.put(key, DUMMY);
	}
	
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called contains() with a null key");
		}
		return bst.contains(key);
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called delete() with a null key");
		}
		bst.delete(key);
	}
	
	public void deleteMax() {
		bst.deleteMax();
	}
	
	public void deleteMin() {
		bst.deleteMin();
	}
	
	public int size() {
		return bst.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<Key> iterator() {
		return bst.keys().iterator();
	}
	
	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("called max() with empty set");
		}
		return bst.max();
	}

	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("called min() with empty set");
		}
		return bst.min();
	}

	public Key ceiling(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called ceiling() with a null key");
		}
		Key k = bst.ceiling(key);
		if (k == null) {
			throw new NoSuchElementException("all keys are less than " + key);
		}
		return k;
	}
	
	public Key floor(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("called floor() with a null key");
		}
		Key k = bst.floor(key);
		if (k == null) {
			throw new NoSuchElementException("all keys are greater than " + key);
		}
		return k;
	}
	
	/*
	 * 并集操作
	 */
	public MulitSET<Key> union(MulitSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		MulitSET<Key> c = new MulitSET<>();
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
	public MulitSET<Key> intersects(MulitSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		MulitSET<Key> c = new MulitSET<>();
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
}
