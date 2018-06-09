package pers.tavish.code.chapter3.hashtables;

import edu.princeton.cs.algs4.Queue;

// 基于线性探测法的开放地址散列表
// 这个实现中散列表的大小m总是2的幂，不够好
public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;

	private int n; // 散列表中键值对的数量
	private int m; // 散列表的大小
	private Key[] keys; // 保存键的数组
	private Value[] vals; // 保存值得数组

	/*
	 * 构造函数
	 */
	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) {
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
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

	/*
	 * 存入键值对
	 */
	public void put(Key key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}

		if (val == null) {
			delete(key);
			return;
		}

		// 键值对数量超过散列表大小的一半，扩大散列表
		if (n >= m / 2)
			resize(2 * m);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		n++;
	}

	/*
	 * 根据特定的键返回值
	 */
	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}

		for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				return vals[i];
			}
		}

		return null;
	}
	
	/*
	 * 判断给定的key是否存在于散列表中
	 */
    public boolean contains(Key key) {
        if (key == null) {
        	throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

	/*
	 * 根据特定的键删除键值对
	 */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}

		if (!contains(key)) {
			return;
		}

		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % m;
		}

		// 删除对应的键值
		keys[i] = null;
		vals[i] = null;

		// 重新散列被删除键右侧的所有键并插入散列表
		i = (i + 1) % m;
		while (keys[i] != null) {
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}

		n--;

		// 如果键值对数量小于散列表大小的1/8，缩小散列表
		if (n > 0 && n <= m / 8) {
			resize(m / 2);
		}
	}

	/*
	 * 以一个Iterable集合的形式返回所有key
	 */
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (int i = 0; i < m; i++) {
			if (keys[i] != null)
				queue.enqueue(keys[i]);
		}
		return queue;
	}

	// 散列算法，将键散列到[0, m-1]
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	// 调整散列表的大小
	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m = temp.m;
	}
}
