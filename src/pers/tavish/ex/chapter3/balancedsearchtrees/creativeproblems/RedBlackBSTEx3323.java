package pers.tavish.ex.chapter3.balancedsearchtrees.creativeproblems;

import edu.princeton.cs.algs4.StdRandom;

// 提高题3.3.23 非平衡2-3树的简单实现
public class RedBlackBSTEx3323<Key extends Comparable<? super Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

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

		root = put(root, key, val, null);
		root.color = BLACK; // root结点的颜色一定是黑色
	}

	private Node<Key, Value> put(Node<Key, Value> h, Key key, Value val, Node<Key, Value> parent) {

		if (h == null) {

			// 针对特殊情况root结点
			if (parent == null) {
				return new Node<>(key, val, RED, 1);
			}

			// parent是三结点的其中一个结点，用黑链接连接新结点
			if (isRed(parent.left) || isRed(parent.right) || isRed(parent)) {
				return new Node<>(key, val, BLACK, 1);
			} else {
				return new Node<>(key, val, RED, 1);
			}
		}

		int cmp = key.compareTo(h.key);
		if (cmp < 0) {
			h.left = put(h.left, key, val, h);
		} else if (cmp > 0) {
			h.right = put(h.right, key, val, h);
		} else {
			h.val = val;
		}

		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	/*
	 * 判断结点x的颜色
	 */
	private boolean isRed(Node<Key, Value> x) {
		return x == null ? false : x.color == RED;
	}

	/*****************************************************************
	 *********** 以下方法不涉及结点的颜色，与二叉查找树实现相同***************
	 ****************************************************************/

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
	 * 计算内部路径长度
	 */
	public int internalPathLength() {
		if (root == null) {
			throw new IllegalArgumentException();
		}
		return internalPathLength(root, 0);
	}

	private int internalPathLength(Node<Key, Value> x, int i) {
		if (x == null) {
			return 0;
		}
		int path = i;
		path += internalPathLength(x.left, i + 1);
		path += internalPathLength(x.right, i + 1);
		return path;
	}

	public static void main(String[] args) {
		
		RedBlackBSTEx3323<Integer, Integer> rbbst = null;
		int N = 0;
		int L = 0;
		
		for (int i = 10; i <= 100000; i *= 10) { 
			rbbst = new RedBlackBSTEx3323<>();
			
			// 存入随机键
			while (rbbst.size() != i) {
				int r = StdRandom.uniform(100000);
				rbbst.put(r, r);
			}
			
			N = rbbst.size();
			L = rbbst.internalPathLength();
			System.out.println("N = " + N + ", L = " + L + ", avg = " + (double)L / N + 1);
		}
	}
}
