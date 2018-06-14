package pers.tavish.ex.chapter3.hashtables.exercises;

import edu.princeton.cs.algs4.Queue;

// 练习题3.4.2
// 不使用内部封装SequentialSearchST的方法，直接在内部封装Node组成的链表
public class SeparateChainHashSTEx342<Key, Value> {

	private Node<Key, Value>[] chains;
	private int m; // 链表的条数
	private int n; // 散列表的键值对数

	private static class Node<Key, Value> {
		Key key;
		Value val;
		Node<Key, Value> next;

		public Node(Key key, Value val, Node<Key, Value> next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	@SuppressWarnings("unchecked")
	public SeparateChainHashSTEx342(int m) {
		if (m <= 0) {
			throw new IllegalArgumentException();
		}
		chains = new Node[m];
		this.m = m;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void put(Key key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		if (val == null) {
			delete(key);
			return;
		}

		int h = hash(key);

		for (Node<Key, Value> x = chains[h]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		
		chains[h] = new Node<>(key, val, chains[h]);
		n++;
		
		// 如果n/m > 10，增加链表的数量
		if (n >= 10 * m) {
			resize(2 * m);
		}
	}

	// 调整链表的数目
	private void resize(int newM) {
		SeparateChainHashSTEx342<Key, Value> tmp = new SeparateChainHashSTEx342<>(newM);
		for (Key key : keys()) {
			tmp.put(key, get(key));
		}

		this.m = tmp.m;
		this.n = tmp.n;
		this.chains = tmp.chains;
	}

	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}

		int h = hash(key);

		for (Node<Key, Value> x = chains[h]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		int h = hash(key);

		if (chains[h] == null) {
			return;
		}

		chains[h] = delete(chains[h], key);

		// 如果链表条数过多，缩小链表数目
		if (m > 4 && n <= 2 * m) {
			resize(m / 2);
		}
	}

	private Node<Key, Value> delete(Node<Key, Value> x, Key key) {

		if (x == null) {
			return null;
		}

		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	public Iterable<Key> keys() {

		Queue<Key> queue = new Queue<>();

		for (int i = 0; i < chains.length; i++) {
			if (chains[i] != null) {
				for (Node<Key, Value> x = chains[i]; x != null; x = x.next) {
					queue.enqueue(x.key);
				}
			}
		}
		return queue;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	public static void main(String[] args) {

	}
}
