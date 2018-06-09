package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;

// 练习题3.5.2 
// 删除SequentialSearchST（无序单链表）中关于值的操作以实现集合
public class SequentialSearchSET<Key> implements Iterable<Key> {

	private Node<Key> first; // 链表首结点

	private int N;

	private static class Node<Key> {

		Key key;
		Node<Key> next;

		public Node(Key key, Node<Key> next) {
			this.key = key;
			this.next = next;
		}
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0; 
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		for (Node<Key> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return true; // 命中，返回
			}
		}
		return false;
	}

	public void add(Key key) {

		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}

		if (contains(key)) {
			return;
		}

		first = new Node<>(key, first); // 未命中，新建结点
		N++;
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		first = delete(first, key);
	}

	private Node<Key> delete(Node<Key> x, Key key) {
		if (x == null) {
			return null;
		}
		if (key.equals(x.key)) {
			N--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (Node<Key> x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}

	@Override
	public Iterator<Key> iterator() {
		return keys().iterator();
	}

	/*
	 * 并集操作
	 */
	public SequentialSearchSET<Key> union(SequentialSearchSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		SequentialSearchSET<Key> c = new SequentialSearchSET<>();
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
	public SequentialSearchSET<Key> intersects(SequentialSearchSET<Key> that) {
		if (that == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}
		SequentialSearchSET<Key> c = new SequentialSearchSET<>();
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

		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SequentialSearchSET<Key> other = (SequentialSearchSET<Key>) obj;
		if (N != other.N) {
			return false;
		}
		if (first == null) {
			if (other.first != null) {
				return false;
			}
		} else if (!first.equals(other.first)) {
			return false;
		}
		return true;
	}
}
