package pers.tavish.ex.chapter3.hashtables.experiments;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import pers.tavish.code.chapter3.elementarysymboltables.SequentialSearchST;

// 实验题3.4.36
// 插入过程不进行resize
public class SeparateChainingHashSTEx3436<Key, Value> {

	private static final int INIT_CAPACITY = 4; // 初始数组长度

	private int n; // 键值对的数量
	private int m; // 散列表的大小

	private SequentialSearchST<Key, Value>[] st;

	/*
	 * 构造函数
	 */
	public SeparateChainingHashSTEx3436() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashSTEx3436(int m) {
		this.m = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++) {
			st[i] = new SequentialSearchST<>();
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
		// if (n >= 10 * m) {
		// resize(2 * m);
		// }

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
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	// 对散列表扩大并重新散列所有键值对
	private void resize(int chains) {
		SeparateChainingHashSTEx3436<Key, Value> temp = new SeparateChainingHashSTEx3436<Key, Value>(chains);
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

	public static void main(String[] args) {
		SeparateChainingHashSTEx3436<Integer, Integer> schst = null;

		for (int N = 1000; N <= 1000000; N *= 10) {
			schst = new SeparateChainingHashSTEx3436<Integer, Integer>(N / 100);
			for (int i = 0; i < N; i++) {
				int rand = StdRandom.uniform(Integer.MAX_VALUE);
				schst.put(rand, 0);
			}

			int maxLength = Integer.MIN_VALUE;
			int minLength = Integer.MAX_VALUE;

			@SuppressWarnings("rawtypes")
			SequentialSearchST[] sts = schst.st;
			for (int i = 0; i < sts.length; i++) {
				int size = sts[i].size();
				if (size > maxLength) {
					maxLength = size;
				}
				if (size < maxLength) {
					minLength = size;
				}
			}
			
			System.out.println("对于N = " + N);
			System.out.println("链表最大长度：" + maxLength);
			System.out.println("链表最小长度：" + minLength + "\n");
		}

	}
}
