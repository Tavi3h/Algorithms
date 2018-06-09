package pers.tavish.code.chapter3.balancedsearchtrees;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

// AVL树实现的符号表
public class AVLTreeST<Key extends Comparable<Key>, Value> {

	private Node<Key, Value> root;

	private static class Node<Key, Value> {

		private Key key; // 键
		private Value val; // 值
		private int height; // 以该结点为根的树的高度
		private int size; // 以该结点为根的结点总数
		private Node<Key, Value> left, right; // 左右链接

		public Node(Key key, Value val, int height, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
			this.height = height;
		}
	}

	/*
	 * 判断符号表是否为空
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * 返回树的结点总数
	 */
	public int size() {
		return size(root);
	}

	/*
	 * 返回以x为根的树的结点总数
	 */
	private int size(Node<Key, Value> x) {
		if (x == null)
			return 0;
		return x.size;
	}

	/*
	 * 返回树的高度
	 */
	public int height() {
		return height(root);
	}

	/*
	 * 返回x的高度
	 */
	private int height(Node<Key, Value> x) {
		return x == null ? -1 : x.height;
	}

	/*
	 * get方法
	 */
	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		Node<Key, Value> x = get(root, key);
		return x == null ? null : x.val;
	}

	private Node<Key, Value> get(Node<Key, Value> x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x;
		}
	}

	/*
	 * 是否包含key
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/*
	 * put方法
	 */
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val) {
		if (x == null)
			return new Node<>(key, val, 0, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
			return x;
		}
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	/*
	 * 重新按照AVL树的条件平衡x
	 */
	private Node<Key, Value> balance(Node<Key, Value> x) {
		if (balanceFactor(x) < -1) {
			if (balanceFactor(x.right) > 0) {
				x.right = rotateRight(x.right);
			}
			x = rotateLeft(x);
		} else if (balanceFactor(x) > 1) {
			if (balanceFactor(x.left) < 0) {
				x.left = rotateLeft(x.left);
			}
			x = rotateRight(x);
		}
		return x;
	}

	/*
	 * 返回x的平衡因子
	 */
	private int balanceFactor(Node<Key, Value> x) {
		return height(x.left) - height(x.right);
	}

	/*
	 * 右旋转x
	 */
	private Node<Key, Value> rotateRight(Node<Key, Value> x) {
		Node<Key, Value> y = x.left;
		x.left = y.right;
		y.right = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	/*
	 * 左旋转x
	 */
	private Node<Key, Value> rotateLeft(Node<Key, Value> x) {
		Node<Key, Value> y = x.right;
		x.right = y.left;
		y.left = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	/*
	 * 删除key
	 */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		if (!contains(key)) {
			return;
		}
		root = delete(root, key);
	}

	private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.left == null) {
				return x.right;
			} else if (x.right == null) {
				return x.left;
			} else {
				Node<Key, Value> y = x;
				x = min(y.right);
				x.right = deleteMin(y.right);
				x.left = y.left;
			}
		}
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	/*
	 * 删除最小key
	 */
	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("called deleteMin() with empty symbol table");
		}
		root = deleteMin(root);
	}

	private Node<Key, Value> deleteMin(Node<Key, Value> x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	/*
	 * 删除最大key
	 */
	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("called deleteMax() with empty symbol table");
		}
		root = deleteMax(root);
	}

	private Node<Key, Value> deleteMax(Node<Key, Value> x) {
		if (x.right == null) {
			return x.left;
		}
		x.right = deleteMax(x.right);
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	/*
	 * 返回最小key
	 */
	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("called min() with empty symbol table");
		}
		return min(root).key;
	}

	private Node<Key, Value> min(Node<Key, Value> x) {
		return x.left == null ? x : min(x.left);
	}

	/*
	 * 返回最大key
	 */
	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("called max() with empty symbol table");
		}
		return max(root).key;
	}

	private Node<Key, Value> max(Node<Key, Value> x) {
		return x.right == null ? x : max(x.right);
	}

	/*
	 * 向下取整
	 */
	public Key floor(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to floor() is null");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("called floor() with empty symbol table");
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
		Node<Key, Value> y = floor(x.right, key);

		return y != null ? y : x;
	}

	/*
	 * 向上取整
	 */
	public Key ceiling(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to ceiling() is null");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("called ceiling() with empty symbol table");
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
		Node<Key, Value> y = ceiling(x.left, key);
		return y != null ? y : x;
	}

	/*
	 * 返回第k小的key
	 */
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
		}
		return select(root, k).key;
	}

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

	/*
	 * 返回比key小的键的数量
	 */
	public int rank(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to rank() is null");
		}
		return rank(key, root);
	}

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

	/*
	 * 按顺序遍历
	 */
	public Iterable<Key> keys() {
		return keysInOrder();
	}

	public Iterable<Key> keysInOrder() {
		Queue<Key> queue = new Queue<Key>();
		keysInOrder(root, queue);
		return queue;
	}

	private void keysInOrder(Node<Key, Value> x, Queue<Key> queue) {
		if (x == null) {
			return;
		}
		keysInOrder(x.left, queue);
		queue.enqueue(x.key);
		keysInOrder(x.right, queue);
	}

	/*
	 * 按层遍历
	 */
	public Iterable<Key> keysLevelOrder() {
		Queue<Key> queue = new Queue<>();
		if (!isEmpty()) {
			Queue<Node<Key, Value>> queue2 = new Queue<>();
			queue2.enqueue(root);
			while (!queue2.isEmpty()) {
				Node<Key, Value> x = queue2.dequeue();
				queue.enqueue(x.key);
				if (x.left != null) {
					queue2.enqueue(x.left);
				}
				if (x.right != null) {
					queue2.enqueue(x.right);
				}
			}
		}
		return queue;
	}

	/*
	 * 按顺序遍历lo和hi之间的key
	 */
	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null) {
			throw new IllegalArgumentException("first argument to keys() is null");
		}
		if (hi == null) {
			throw new IllegalArgumentException("second argument to keys() is null");
		}
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
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

	/*
	 * 返回lo和hi之间结点的数量
	 */
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

	public static void main(String[] args) {

	}
}
