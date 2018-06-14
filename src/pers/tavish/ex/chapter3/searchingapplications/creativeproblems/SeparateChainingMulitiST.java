package pers.tavish.ex.chapter3.searchingapplications.creativeproblems;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

// 允许重复值的基于无序单链表实现的顺序查找ST
class SequentialSearchMultiST<Key, Value> {

	private Node<Key, Value> first; // 链表首结点

	private int N;

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

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * 根据键，返回一个随机的对应值
	 */
	public Value get(Key key) {

		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}

		List<Value> list = new ArrayList<>();

		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				list.add(x.val);
			}
		}

		int i = list.size();

		return i == 0 ? null : list.get(StdRandom.uniform(i));
	}
	
	/*
	 * 返回一个键对应的所有值
	 */
	public Iterable<Value> getAll(Key key) {
		Queue<Value> queue = new Queue<>(); 
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				queue.enqueue(x.val);
			}
		}
		return queue;
	}
	
	/*
	 * 插入键值对，允许重复键
	 */
	public void put(Key key, Value val) {

		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}

		if (val == null) {
			delete(key);
			return;
		}

		first = new Node<>(key, val, first); // 未命中，新建结点
		N++;
	}

	/*
	 * 检测链表是否包含某个key
	 */
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 根据键删除键值对
	 */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		first = delete(first, key);
	}

	private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
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

	/*
	 * 以一个Iterable集合的方式返回所有的key
	 */
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}
}

// 提高题3.5.19
// 允许重复值的拉链法散列表
public class SeparateChainingMulitiST<Key, Value> {

	private static final int INIT_CAPACITY = 4; // 初始数组长度

	private int n; // 键值对的数量
	private int m; // 散列表的大小

	private SequentialSearchMultiST<Key, Value>[] st;

	/*
	 * 构造函数
	 */
	public SeparateChainingMulitiST() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingMulitiST(int m) {
		this.m = m;
		st = (SequentialSearchMultiST<Key, Value>[]) new SequentialSearchMultiST[m];
		for (int i = 0; i < m; i++) {
			st[i] = new SequentialSearchMultiST<>();
		}
	}

	/*
	 * 返回散列表中键值对的数量
	 */
	public int size() {
		return n;
	}

	/*
	 * 判断散列表是否是空的
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * 根据key返回Value
	 */
	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		int i = hash(key);
		return st[i].get(key);
	}

	/*
	 * 存入键值对key-Value
	 */
	public void put(Key key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}

		if (val == null) {
			delete(key);
			return;
		}

		// 如果链表的平均长度大于10，对散列表进行调整扩大
		if (n >= 10 * m) {
			resize(2 * m);
		}

		int i = hash(key);
		st[i].put(key, val);
		n++;
	}

	/*
	 * 判断散列表是否包含指定的key
	 */
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		int i = hash(key);
		return st[i].contains(key);
	}

	/*
	 * 根据给定的键删除键值对，有重复键时删除所有重复键
	 */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}

		int i = hash(key);
		while (st[i].contains(key)) {
			st[i].delete(key);
			n--;
		}

		// 如果链表的平均长度小于2，对散列表进行缩小
		if (m > INIT_CAPACITY && n <= 2 * m) {
			resize(m / 2);
		}
	}
	
	/*
	 * 以一个Iterable集合的方式返回所有的key
	 */
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				queue.enqueue(key);
			}
		}
		return queue;
	}
	
	/*
	 * 返回一个键对应的所有值
	 */
	public Iterable<Value> getAll(Key key) {
		int i = hash(key);
		return st[i].getAll(key);
	}
	
	// 将key散列到[0, m-1]
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}
	
	// 对散列表扩大并重新散列所有键值对
	private void resize(int chains) {
		SeparateChainingMulitiST<Key, Value> temp = new SeparateChainingMulitiST<Key, Value>(chains);
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}
	
	public static void main(String[] args) {
		SeparateChainingMulitiST<String,Integer> scmst = new SeparateChainingMulitiST<>();
		scmst.put("A", 0);
		scmst.put("B", 2);
		scmst.put("C", 3);
		scmst.put("A", 5);
		scmst.put("d", 4);
		
		for (String string : scmst.keys()) { 
			System.out.print(string + " "); // d A A B C 
		}
		System.out.println();
		for (Integer i : scmst.getAll("A")) {
			System.out.print(i + " "); // 5 0 
		}
	}
}
