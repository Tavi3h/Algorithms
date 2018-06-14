package pers.tavish.ex.chapter3.hashtables.exercises;

import edu.princeton.cs.algs4.Queue;
import pers.tavish.code.chapter3.elementarysymboltables.SequentialSearchST;

// 练习题3.4.18
public class SeparateChainingHashSTEx3418<Key, Value> {

	// 用于hash函数
	private static final int[] PRIMES = { 31, 61, 127, 251, 509, 1021, 2039, 4093, 9191, 16381, 32749, 65521, 131071,
			262139, 524287, 1048573, 2097143, 4194301, 8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
			536870909, 1073741789, 2147483647 };

	private static final int INIT_CAPACITY = 4; // 初始数组长度

	private int n; // 键值对的数量
	private int m; // 散列表的大小

	private SequentialSearchST<Key, Value>[] st;

	private int alpha = 5; // 默认的可以接受的最大平均探测次数

	/*
	 * 构造函数
	 */
	public SeparateChainingHashSTEx3418() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashSTEx3418(int capacity) {
		this.m = capacity;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++) {
			st[i] = new SequentialSearchST<>();
		}
	}

	// 练习3.4.18
	public SeparateChainingHashSTEx3418(int alpha, int capacity) {

		this(capacity);

		if (alpha < 1) {
			throw new IllegalArgumentException();
		}
		this.alpha = alpha;
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

		// 如果链表平均长度大于alpha，链表数量扩大2倍
		if (n >= alpha * m) {
			resize(2 * m);
		}

		int i = hash(key);
		if (!st[i].contains(key)) {
			n++;
		}
		st[i].put(key, val);
	}

	/*
	 * 判断散列表是否包含指定的key
	 */
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/*
	 * 根据给定的键删除键值对
	 */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}

		int i = hash(key);
		if (st[i].contains(key)) {
			n--;
		}
		st[i].delete(key);

		// 如果链表的平均长度小于2，对散列表进行缩小
		if (m > INIT_CAPACITY && n <= 2 * m) {
			resize(m / 2);
		}
	}

	// 将key散列到[0, m-1]
	// 练习3.4.18
	private int hash(Key key) {

		int t = key.hashCode() & 0x7fffffff;

		int lgM = (int) Math.log10(m);

		if (lgM < 26) {
			t %= PRIMES[lgM + 5];
		}
		return t % m;
	}

	// 对散列表扩大并重新散列所有键值对
	private void resize(int chains) {
		SeparateChainingHashSTEx3418<Key, Value> temp = new SeparateChainingHashSTEx3418<Key, Value>(chains);
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
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
}
