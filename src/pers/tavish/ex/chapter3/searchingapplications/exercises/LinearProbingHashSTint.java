package pers.tavish.ex.chapter3.searchingapplications.exercises;

import edu.princeton.cs.algs4.Queue;

// 练习题3.5.4
// int类型的HashST
public class LinearProbingHashSTint {

	private static final int DEFAULT_VALUE = Integer.MAX_VALUE;

	private static final int INIT_CAPACITY = 4;
	private int n; // 散列表中键的数量
	private int m; // 散列表的大小
	private int[] keys; // 保存键的数组

	/*
	 * 构造函数
	 */
	public LinearProbingHashSTint() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHashSTint(int capacity) {
		m = capacity;
		n = 0;
		keys = new int[m];
		for (int i = 0; i < keys.length; i++) { 
			keys[i] = DEFAULT_VALUE;
		}
	}

	/*
	 * 返回散列表中的键值对数量
	 */
	public int size() {
		return n;
	}

	/*
	 * 判断散列表是否为空
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	public void put(int key) {

		// 键值对数量超过散列表大小的一半，扩大散列表
		if (n >= m / 2) {
			resize(2 * m);
		}
		if (contains(key)) {
			return;
		}

		int i = hash(key);
		keys[i] = key;
		n++;
	}

	public boolean contains(int key) {
		for (int i = hash(key); keys[i] != DEFAULT_VALUE; i = (i + 1) % m) {
			if (keys[i] == key) {
				return true;
			}
		}
		return false;
	}

	public void delete(int key) {

		if (!contains(key)) {
			return;
		}

		int i = hash(key);

		while (key != keys[i]) {
			i = (i + 1) % m;
		}

		// 删除对应的键值
		keys[i] = 0;

		// 重新散列被删除键右侧的所有键并插入散列表
		i = (i + 1) % m;
		while (keys[i] != DEFAULT_VALUE) {
			int keyToRehash = keys[i];
			keys[i] = DEFAULT_VALUE;
			n--;
			put(keyToRehash);
			i = (i + 1) % m;
		}

		n--;

		// 如果键值对数量小于散列表大小的1/8，缩小散列表
		if (n > 0 && n <= m / 8) {
			resize(m / 2);
		}
	}

	public Iterable<Integer> keys() {
		Queue<Integer> queue = new Queue<>();
		for (int i = 0; i < m; i++) {
			if (keys[i] != DEFAULT_VALUE)
				queue.enqueue(keys[i]);
		}
		return queue;
	}

	private int hash(int key) {
		return (key & 0x7fffffff) % m;
	}

	// 调整散列表的大小
	private void resize(int capacity) {
		LinearProbingHashSTint temp = new LinearProbingHashSTint(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != DEFAULT_VALUE) {
				temp.put(keys[i]);
			}
		}
		keys = temp.keys;
		m = temp.m;
	}
}

