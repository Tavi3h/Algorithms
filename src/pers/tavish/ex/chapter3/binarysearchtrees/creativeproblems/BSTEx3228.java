package pers.tavish.ex.chapter3.binarysearchtrees.creativeproblems;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

// 二叉查找树
// 提高题3.2.28，增加了缓存lastAcc
public class BSTEx3228<Key extends Comparable<? super Key>, Value> {

	private Node<Key, Value> root; // 二叉查找树的根结点

	private Node<Key, Value> lastAcc; // 上一次被put()、get()访问的结点

	private static class Node<Key, Value> {
		private Key key; // 键
		private Value val; // 值
		private Node<Key, Value> left, right; // 指向子树的链接
		private int N; // 以该结点为根的子树中的结点总数

		public Node(Key key, Value val, int n) {
			this.key = key;
			this.val = val;
			N = n;
		}
	}

	// 二叉查找树是否为空
	public boolean isEmpty() {
		return size() == 0;
	}

	// 返回二叉查找树的结点总数
	public int size() {
		return size(root);
	}

	// 返回x的结点总数
	private int size(Node<Key, Value> x) {
		return x == null ? 0 : x.N;
	}

	// 返回指定区间[lo, hi]内的结点数量
	public int size(Key lo, Key hi) {
		if (lo == null) {
			throw new IllegalArgumentException("first argument to size() is null");
		}
		if (hi == null) {
			throw new IllegalArgumentException("second argument to size() is null");
		}

		if (lo.compareTo(hi) > 0) {
			return 0;
		}
		if (contains(hi)) {
			return rank(hi) - rank(lo) + 1;
		} else {
			return rank(hi) - rank(lo);
		}
	}

	// 判断树中是否包含key
	public boolean contains(Key key) {
		
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		
		if (lastAcc != null && key.compareTo(lastAcc.key) == 0) {
			return true;
		}
		
		return get(key) != null;
	}

	// 返回key对应的值
	public Value get(Key key) {
		// 如果缓存命中，则从缓存读取
		if (lastAcc != null && key.compareTo(lastAcc.key) == 0) {
			return lastAcc.val;
		}
		return get(root, key);
	}

	// 递归查找key对应的值
	private Value get(Node<Key, Value> x, Key key) {

		if (x == null) {
			return null;
		}

		lastAcc = x;

		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x.val;
		}
	}

	// 向二叉查找树中插入键值对
	public void put(Key key, Value val) {
		
		// 如果缓存命中，则直接存入缓存
		if (lastAcc != null && key.compareTo(lastAcc.key) == 0) {
			lastAcc.val = val;
			return;
		}
		
		root = put(root, key, val);
	}

	private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val) {

		// 如果结点为null，则创建一个新结点
		if (x == null) {
			Node<Key, Value> node = new Node<>(key, val, 1);
			lastAcc = node;
			return node;
		}

		lastAcc = x;

		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}

		// 每次插入或更新后更新当前结点的结束器
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	// 返回最小的key
	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls min() with empty symbol table");
		}
		return min(root).key;
	}

	private Node<Key, Value> min(Node<Key, Value> x) {
		return x.left == null ? x : min(x.left);
	}

	// 返回最大的key
	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls max() with empty symbol table");
		}
		return max(root).key;
	}

	private Node<Key, Value> max(Node<Key, Value> x) {
		return x.right == null ? x : max(x.right);
	}

	// 向下取整
	public Key floor(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to floor() is null");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node<Key, Value> x = floor(root, key);

		return x == null ? null : x.key;
	}

	private Node<Key, Value> floor(Node<Key, Value> x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			return floor(x.left, key);
		}
		Node<Key, Value> t = floor(x.right, key);

		return t == null ? x : t;
	}

	// 向上取整
	public Key ceiling(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to ceiling() is null");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("calls ceiling() with empty symbol table");
		}
		Node<Key, Value> x = ceiling(root, key);

		return x == null ? null : x.key;
	}

	private Node<Key, Value> ceiling(Node<Key, Value> x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp > 0) {
			return ceiling(x.right, key);
		}
		Node<Key, Value> t = ceiling(x.left, key);

		return t == null ? x : t;
	}

	// 返回给定键的排名
	public int rank(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to rank() is null");
		}
		return rank(key, root);
	}

	// Number of keys in the subtree less than key.
	private int rank(Key key, Node<Key, Value> x) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return rank(key, x.left);
		} else if (cmp > 0) {
			return 1 + size(x.left) + rank(key, x.right);
		} else {
			return size(x.left);
		}
	}

	// 返回排名第k的key
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		Node<Key, Value> x = select(root, k);
		return x.key;
	}

	// Return key of rank k.
	private Node<Key, Value> select(Node<Key, Value> x, int k) {
		if (x == null) {
			return null;
		}
		int t = size(x.left);
		if (t > k) {
			return select(x.left, k);
		} else if (t < k) {
			return select(x.right, k - t - 1);
		} else {
			return x;
		}
	}

	// 删除最小key
	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table underflow");
		}
		root = deleteMin(root);
	}

	private Node<Key, Value> deleteMin(Node<Key, Value> x) {
		if (x.left == null) {
			if (x == lastAcc) {
				lastAcc = null;
			}
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	// 删除最大key
	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table underflow");
		}
		root = deleteMax(root);
	}

	private Node<Key, Value> deleteMax(Node<Key, Value> x) {
		if (x.right == null) {
			if (x == lastAcc) {
				lastAcc = null;
			}
			return x.left;
		}
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	// 删除键值对
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("calls delete() with a null key");
		}

		if (lastAcc != null && key.compareTo(lastAcc.key) == 0) {
			lastAcc = null;
		}

		root = delete(root, key);
	}

	private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
		if (x == null) {
			return null;
		}

		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			Node<Key, Value> t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	// 返回树的高度
	public int height() {
		return height(root);
	}

	private int height(Node<Key, Value> x) {
		return x == null ? -1 : 1 + Math.max(height(x.left), height(x.right));
	}

	// 范围查找操作
	public Iterable<Key> keys() {
		if (isEmpty()) {
			return new Queue<Key>();
		}
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null) {
			throw new IllegalArgumentException("first argument to keys() is null");
		}
		if (hi == null) {
			throw new IllegalArgumentException("second argument to keys() is null");
		}

		Queue<Key> queue = new Queue<>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null) {
			return;
		}
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0) {
			keys(x.left, queue, lo, hi);
		}
		if (cmplo <= 0 && cmphi >= 0) {
			queue.enqueue(x.key);
		}
		if (cmphi > 0) {
			keys(x.right, queue, lo, hi);
		}
	}

	// 练习题3.2.21
	public Key randomKey() {
		return select(StdRandom.uniform(0, size() - 1));
	}

	public static void main(String[] args) {
		String[] strings = "20 10 30 7 15 25 40 12 17".split(" ");
		BSTEx3228<Integer, Integer> bst = new BSTEx3228<>();
		for (int i = 0; i < strings.length; i++) {
			bst.put(Integer.parseInt(strings[i]), i);
		}
	}
}
