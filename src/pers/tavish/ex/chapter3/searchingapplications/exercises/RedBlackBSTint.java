package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

// 练习题3.5.5
public class RedBlackBSTint {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root; // 红黑树的根结点

	private static class Node {

		private int key; // 键
		private Node left, right; // 左、右链接
		private boolean color; // 父结点的指向这个结点的链接的颜色
		private int size; // 这棵子树的结点总数

		public Node(int key, boolean color, int size) {
			this.key = key;
			this.color = color;
			this.size = size;
		}
	}

	/*
	 * 存入键值对key-val
	 */
	public void put(int key) {

		root = put(root, key);
		root.color = BLACK; // root结点的颜色一定是黑色
	}

	private Node put(Node h, int key) {

		if (h == null) {
			return new Node(key, RED, 1);
		}

		int cmp = key - h.key;
		if (cmp < 0) {
			h.left = put(h.left, key);
		} else if (cmp > 0) {
			h.right = put(h.right, key);
		} else {
			// 空闲
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
	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	/*
	 * 左旋转Node h的右链接
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
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
	private Node rotateRight(Node h) {
		Node x = h.left;
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
	private boolean isRed(Node x) {
		return x == null ? false : x.color == RED;
	}

	/*
	 * 删除键值对
	 */
	public void delete(int key) {

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

	private Node delete(Node h, int key) {

		int cmp = key - h.key;

		if (cmp < 0) {
			if (!isRed(h.left) && !isRed(h.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, key);
		} else {
			if (isRed(h.left)) {
				h = rotateRight(h);
			}
			if (cmp == 0 && (h.right == null)) {
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			if (cmp == 0) {
				Node x = min(h.right);
				h.key = x.key;
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

	private Node deleteMax(Node h) {
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

	private Node deleteMin(Node h) {
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
	private Node moveRedRight(Node h) {
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	// 假定结点h是红色并且h.left和h.left.left是黑色，将h.left或它的一个子结点转为红色
	private Node moveRedLeft(Node h) {

		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// 重新平衡以h为根的子树
	private Node balance(Node h) {

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

	private int size(Node x) {
		return x == null ? 0 : x.size;
	}

	/*
	 * 测试树中是否包含指定的key
	 */
	public boolean contains(int key) {
		Node x = root;
		while (x != null) {
			int cmp = key - x.key;
			if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {
				x = x.right;
			} else {
				return true;
			}
		}
		return false;
	}

	/*
	 * 返回最小的key
	 */
	public int min() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls min() with empty symbol table");
		}
		return min(root).key;
	}

	private Node min(Node x) {
		return x.left == null ? x : min(x.left);
	}

	/*
	 * 返回最大的key
	 */
	public int max() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls max() with empty symbol table");
		}
		return max(root).key;
	}

	private Node max(Node x) {
		return x.right == null ? x : max(x.right);
	}

	/*
	 * 向下取整
	 */
	public int floor(int key) {
		if (isEmpty()) {
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}

		Node x = floor(root, key);

		return x == null ? Integer.MIN_VALUE : x.key;
	}

	private Node floor(Node x, int key) {
		if (x == null) {
			return null;
		}
		int cmp = key - x.key;
		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			return floor(x.left, key);
		}

		Node t = floor(x.right, key);

		return t == null ? x : t;
	}

	/*
	 * 向上取整
	 */
	public int ceiling(int key) {
		if (isEmpty()) {
			throw new NoSuchElementException("calls ceiling() with empty symbol table");
		}

		Node x = ceiling(root, key);

		return x == null ? Integer.MAX_VALUE : x.key;
	}

	private Node ceiling(Node x, int key) {
		if (x == null) {
			return null;
		}
		int cmp = key - x.key;
		if (cmp == 0) {
			return x;
		}
		if (cmp > 0) {
			return ceiling(x.right, key);
		}

		Node t = ceiling(x.left, key);

		return t == null ? x : t;
	}

	/*
	 * 返回给定键的排名
	 */
	public int rank(int key) {
		return rank(key, root);
	}

	private int rank(int key, Node x) {
		if (x == null) {
			return 0;
		}
		int cmp = key - x.key;
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
	public int select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		return select(root, k).key;
	}

	private Node select(Node x, int k) {
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

	private int height(Node x) {
		return x == null ? -1 : 1 + Math.max(height(x.left), height(x.right));
	}

	/*
	 * 返回所有key
	 */
	public Iterable<Integer> keys() {
		if (isEmpty()) {
			return new Queue<Integer>();
		}
		return keys(min(), max());
	}

	/*
	 * 返回lo...hi之间的key
	 */
	public Iterable<Integer> keys(int lo, int hi) {

		Queue<Integer> queue = new Queue<>();

		if (isEmpty() || lo - hi > 0) {
			return queue;
		}

		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, Queue<Integer> queue, int lo, int hi) {
		if (x == null) {
			return;
		}
		int cmplo = lo - x.key;
		int cmphi = hi - x.key;
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
}
