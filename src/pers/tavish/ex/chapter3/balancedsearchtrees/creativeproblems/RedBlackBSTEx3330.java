package pers.tavish.ex.chapter3.balancedsearchtrees.creativeproblems;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

// 提高题 3.3.30，添加缓存
public class RedBlackBSTEx3330<Key extends Comparable<? super Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node<Key, Value> lastAcc;

	private Node<Key, Value> root; // 红黑树的根结点

	private static class Node<Key, Value> {

		private Key key; // 键
		private Value val; // 值
		private Node<Key, Value> left, right; // 左、右链接
		private boolean color; // 父结点的指向这个结点的链接的颜色
		private int size; // 这颗子树的结点总数

		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}

		// 测试用toString()
		@Override
		public String toString() {
			return "Node [key=" + key + ", val=" + val + ", color=" + color + ", size=" + size + "]";
		}
	}

	/*
	 * 存入键值对key-val
	 */
	public void put(Key key, Value val) {

		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}

		if (val == null) {
			delete(key);
			return;
		}

		// 如果key与缓存中的key相同
		if (lastAcc != null && key.compareTo(lastAcc.key) == 0) {
			lastAcc.val = val;
			return;
		}

		root = put(root, key, val);
		root.color = BLACK; // root结点的颜色一定是黑色
	}

	private Node<Key, Value> put(Node<Key, Value> h, Key key, Value val) {

		if (h == null) {
			Node<Key, Value> node = new Node<>(key, val, RED, 1);
			lastAcc = node; // 使用缓存记录新存入的键
			return node;
		}

		int cmp = key.compareTo(h.key);
		if (cmp < 0) {
			h.left = put(h.left, key, val);
		} else if (cmp > 0) {
			h.right = put(h.right, key, val);
		} else {
			// 发生更新，使用缓存记录被更新的结点
			lastAcc = h;
			h.val = val;
		}

		// 如果右子结点是红色而左子结点是黑色，进行左旋转
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		// 如果左子结点是红色的且它的左子结点也是红色的，进行右旋转
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		// 如果左右子结点均为红色，进行颜色转换
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}

		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	// 转换Node h与它的两个子结点的颜色
	private void flipColors(Node<Key, Value> h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	/*
	 * 左旋转Node h的右链接
	 */
	private Node<Key, Value> rotateLeft(Node<Key, Value> h) {
		Node<Key, Value> x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	/*
	 * 右旋转Node h的左链接
	 */
	private Node<Key, Value> rotateRight(Node<Key, Value> h) {
		Node<Key, Value> x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	/*
	 * 判断结点x的颜色
	 */
	private boolean isRed(Node<Key, Value> x) {
		return x == null ? false : x.color == RED;
	}

	/*
	 * 删除键值对
	 */
	public void delete(Key key) {

		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}

		// 不含有该键，直接返回，避免改变红黑树的结构
		if (!contains(key)) {
			return;
		}

		// 如果根节点的两个子结点都是黑色，设置根节点颜色为红色
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = delete(root, key);

		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	private Node<Key, Value> delete(Node<Key, Value> h, Key key) {

		if (key.compareTo(h.key) < 0) {
			if (!isRed(h.left) && !isRed(h.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, key);
		} else {
			if (isRed(h.left)) {
				h = rotateRight(h);
			}
			if (key.compareTo(h.key) == 0 && (h.right == null)) {
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			if (key.compareTo(h.key) == 0) {
				Node<Key, Value> x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				h.right = deleteMin(h.right);
			} else {
				h.right = delete(h.right, key);
			}
		}
		return balance(h);
	}

	/*
	 * 删除最大键
	 */
	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("BST underflow");
		}

		// 如果根结点的两个子结点的颜色都是黑色，将根结点设置为红色
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = deleteMax(root);

		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	private Node<Key, Value> deleteMax(Node<Key, Value> h) {
		if (isRed(h.left)) {
			h = rotateRight(h);
		}

		if (h.right == null) {
			return null;
		}

		if (!isRed(h.right) && !isRed(h.right.left)) {
			h = moveRedRight(h);
		}

		h.right = deleteMax(h.right);

		return balance(h);
	}

	/*
	 * 删除最小键
	 */
	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("BST underflow");
		}

		// 如果根结点的两个子结点的颜色都是黑色，将根结点设置为红色
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = deleteMin(root);

		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	private Node<Key, Value> deleteMin(Node<Key, Value> h) {
		if (h.left == null) {
			return null;
		}

		if (!isRed(h.left) && !isRed(h.left.left)) {
			h = moveRedLeft(h);
		}

		h.left = deleteMin(h.left);
		return balance(h);
	}

	// 假定结点h是红色，并且h.right和h.right.left是黑色，将h.right或它的一个子结点转为红色
	private Node<Key, Value> moveRedRight(Node<Key, Value> h) {
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	// 假定结点h是红色并且h.left和h.left.left是黑色，将h.left或它的一个子结点转为红色
	private Node<Key, Value> moveRedLeft(Node<Key, Value> h) {

		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// 修复红黑树的性质
	private Node<Key, Value> balance(Node<Key, Value> h) {

		if (isRed(h.right)) {
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}

		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	/*****************************************************************
	 *********** 以下方法不涉及结点的颜色，与二叉查找树实现相同***************
	 ****************************************************************/

	/*
	 * 判断红黑树是否为空树
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * 返回这颗红黑树的结点总数
	 */
	public int size() {
		return size(root);
	}

	private int size(Node<Key, Value> x) {
		return x == null ? 0 : x.size;
	}

	/*
	 * 根据key来获取val
	 */
	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}

		// 如果key与lastAcc的key相同
		if (lastAcc != null && key.compareTo(lastAcc.key) == 0) {
			return lastAcc.val;
		}

		return get(root, key);
	}

	private Value get(Node<Key, Value> x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {
				x = x.right;
			} else {
				// 使用缓存记录被访问的结点
				lastAcc = x;
				return x.val;
			}
		}
		return null;
	}

	/*
	 * 测试树中是否包含指定的key
	 */
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/*
	 * 返回最小的key
	 */
	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls min() with empty symbol table");
		}
		return min(root).key;
	}

	private Node<Key, Value> min(Node<Key, Value> x) {
		return x.left == null ? x : min(x.left);
	}

	/*
	 * 返回最大的key
	 */
	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls max() with empty symbol table");
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

	/*
	 * 向上取整
	 */
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

	/*
	 * 返回给定键的排名
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
	 * 返回排名第k的键
	 */
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
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
	 * 返回树的高度
	 */
	public int height() {
		return height(root);
	}

	private int height(Node<Key, Value> x) {
		return x == null ? -1 : 1 + Math.max(height(x.left), height(x.right));
	}

	/*
	 * 返回所有key
	 */
	public Iterable<Key> keys() {
		if (isEmpty()) {
			return new Queue<Key>();
		}
		return keys(min(), max());
	}

	/*
	 * 返回lo...hi之间的key
	 */
	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null) {
			throw new IllegalArgumentException("first argument to keys() is null");
		}

		if (hi == null) {
			throw new IllegalArgumentException("second argument to keys() is null");
		}

		Queue<Key> queue = new Queue<Key>();

		if (isEmpty() || lo.compareTo(hi) > 0) {
			return queue;
		}

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

	public static void main(String[] args) {
		RedBlackBSTEx3330<Integer, Integer> rbbst = new RedBlackBSTEx3330<>();
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (Integer i : ints) {
			rbbst.put(i, 0);
			System.out.print(rbbst.height() + " ");
		}
	}
}
