package pers.tavish.ex.chapter3.searchingapplications.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

// 二叉查找树，允许重复的键
// 当遇到重复键时，向左走
// 练习题 3.5.9
public class BSTEx359<Key extends Comparable<? super Key>, Value> {

	private Node<Key, Value> root; // 二叉查找树的根结点

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

	/*
	 * 判断二叉查找树是否为空
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * 返回二叉查找树的结点总数
	 */
	public int size() {
		return size(root);
	}

	// 返回x的结点总数
	private int size(Node<Key, Value> x) {
		return x == null ? 0 : x.N;
	}

	/*
	 * 返回指定区间[lo, hi]内的结点数量
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

	/*
	 * 判断二叉查找树是否包含指定的key，不依赖于get()方法
	 */
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return contains(root, key);
	}
	
	private boolean contains(Node<Key, Value> x, Key key) {
		if (x == null) {
			return false;
		}
		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			return contains(x.left, key);
		} else if (cmp > 0) {
			return contains(x.right, key);
		} else {
			return true;
		}
	}

	/*
	 * 返回key对应的值，由于允许重复键，返回一个随机的值
	 */
	public Value get(Key key) {

		List<Value> list = get(root, key, new ArrayList<Value>());

		int i = list.size();
		
		return i == 0 ? null : list.get(StdRandom.uniform(i));
	}

	// 递归查找key对应的值
	private List<Value> get(Node<Key, Value> x, Key key, List<Value> list) {

		if (x == null) {
			return list;
		}

		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			return get(x.left, key, list);
		} else if (cmp > 0) {
			return get(x.right, key, list);
		} else {
			list.add(x.val);
			return get(x.left, key, list); // 向左子树继续查找重复键
		}
	}

	/*
	 * 向二叉查找树中插入键值对
	 */
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val) {

		// 如果结点为null，则创建一个新结点
		if (x == null) {
			Node<Key, Value> node = new Node<>(key, val, 1);
			return node;
		}

		int cmp = key.compareTo(x.key);

		// 两个键相等（插入重复键）时，转向左子树
		if (cmp <= 0) {
			x.left = put(x.left, key, val);
		} else {
			x.right = put(x.right, key, val);
		}

		// 每次插入或更新后更新当前结点的结束器
		x.N = size(x.left) + size(x.right) + 1;
		return x;
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

	// Number of keys in the subtree less than key.
	private int rank(Key key, Node<Key, Value> x) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp <= 0) {
			return rank(key, x.left);
		} else if (cmp > 0) {
			return 1 + size(x.left) + rank(key, x.right);
		} else {
			return size(x.left);
		}
	}

	/*
	 * 返回排名第k的key
	 */
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		return select(root, k).key;
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

	/*
	 * 删除一个最小key（没有删除其重复键）
	 */
	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table underflow");
		}
		root = deleteMin(root);
	}

	private Node<Key, Value> deleteMin(Node<Key, Value> x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	/*
	 * 删除一个最大key（没有删除其重复键）
	 */
	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table underflow");
		}
		root = deleteMax(root);
	}

	private Node<Key, Value> deleteMax(Node<Key, Value> x) {
		if (x.right == null) {
			return x.left;
		}
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	/*
	 * 删除键值对
	 */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("calls delete() with a null key");
		}

		// 加入循环，删除所有与key相等的键
		while (contains(key)) {
			root = delete(root, key);
		}
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
	 * 范围查找操作
	 */
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
		if (cmplo <= 0) { // 条件由“<”改为“<=”
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
		BSTEx359<String, Integer> bst = new BSTEx359<>();
		bst.put("A", 1);
		bst.put("A", 2);
		bst.put("A", 3);
		bst.put("A", 7);
		bst.put("B", 2);
		bst.put("B", 2);
		bst.put("C", 2);
		bst.put("D", 2);
		bst.put("D", 2);

		System.out.println(bst.get("A")); // 此时会随机返回1、2、3、7

		for (String string : bst.keys()) {
			System.out.print(string + " "); // A A A A B B C D D 
		}
		System.out.println();

		bst.delete("A"); // 删除所有"A"
		bst.deleteMin(); // 删除一个"B"
		for (String string : bst.keys()) {
			System.out.print(string + " "); // B C D D 
		}
		System.out.println();
		
		System.out.println(bst.size()); // 4
		System.out.println(bst.get("A")); // null
	}
}
