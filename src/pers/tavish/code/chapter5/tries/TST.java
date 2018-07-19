package pers.tavish.code.chapter5.tries;

import edu.princeton.cs.algs4.Queue;

// 三向单词查找树
public class TST<Value> {

	private int n; // 符号表中键值对的数量

	private Node<Value> root; // 根结点

	private static class Node<Value> {
		private char c; // 结点保存的字符
		private Node<Value> left, mid, right; // 左中右三条链接
		private Value val; // 与键关联的值
	}

	/*
	 * 返回符号表中键值对的数量
	 */
	public int size() {
		return n;
	}

	/*
	 * 判断符号表是否为空
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * 判断符号表中是否含有指定的key
	 */
	public boolean contains(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/*
	 * 返回给定键关联的值
	 */
	public Value get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("calls get() with null argument");
		}

		if (key.length() == 0) {
			throw new IllegalArgumentException("key must have length >= 1");
		}

		Node<Value> x = get(root, key, 0);

		return x == null ? null : x.val;
	}

	private Node<Value> get(Node<Value> x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (key.length() == 0) {
			throw new IllegalArgumentException("key must have length >= 1");
		}
		char c = key.charAt(d);
		if (c < x.c) {
			return get(x.left, key, d);
		} else if (c > x.c) {
			return get(x.right, key, d);
		} else if (d < key.length() - 1) {
			return get(x.mid, key, d + 1);
		} else {
			return x;
		}
	}

	/*
	 * 向符号表中存入键值对
	 */
	public void put(String key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("calls put() with null key");
		}

		if (!contains(key)) {
			n++;
		}
		root = put(root, key, val, 0);
	}

	private Node<Value> put(Node<Value> x, String key, Value val, int d) {
		char c = key.charAt(d);
		if (x == null) {
			x = new Node<>();
			x.c = c;
		}
		if (c < x.c) {
			x.left = put(x.left, key, val, d);
		} else if (c > x.c) {
			x.right = put(x.right, key, val, d);
		} else if (d < key.length() - 1) {
			x.mid = put(x.mid, key, val, d + 1);
		} else {
			x.val = val;
		}
		return x;
	}

	/*
	 * 返回query的前缀中最长的键
	 */
	public String longestPrefixOf(String query) {
		if (query == null) {
			throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
		}
		if (query.length() == 0) {
			return null;
		}
		int length = 0;
		Node<Value> x = root;
		int i = 0;
		while (x != null && i < query.length()) {
			char c = query.charAt(i);
			if (c < x.c) {
				x = x.left;
			} else if (c > x.c) {
				x = x.right;
			} else {
				i++;
				if (x.val != null) {
					length = i;
				}
				x = x.mid;
			}
		}
		return query.substring(0, length);
	}

	/*
	 * 返回所有键
	 */
	public Iterable<String> keys() {
		Queue<String> queue = new Queue<>();
		collect(root, new StringBuilder(), queue);
		return queue;
	}

	/*
	 * 返回所有以prefix为前缀的键
	 */
	public Iterable<String> keysWithPrefix(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
		}
		Queue<String> queue = new Queue<>();
		Node<Value> x = get(root, prefix, 0);
		if (x == null) {
			return queue;
		}
		if (x.val != null) {
			queue.enqueue(prefix);
		}
		collect(x.mid, new StringBuilder(prefix), queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
		if (x == null) {
			return;
		}
		collect(x.left, prefix, queue);
		if (x.val != null) {
			queue.enqueue(prefix.toString() + x.c);
		}
		collect(x.mid, prefix.append(x.c), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
	}

	/*
	 * 按照模式匹配
	 */
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> queue = new Queue<>();
		collect(root, new StringBuilder(), 0, pattern, queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
		if (x == null) {
			return;
		}
		char c = pattern.charAt(i);
		if (c == '.' || c < x.c) {
			collect(x.left, prefix, i, pattern, queue);
		}
		if (c == '.' || c == x.c) {
			if (i == pattern.length() - 1 && x.val != null) {
				queue.enqueue(prefix.toString() + x.c);
			}
			if (i < pattern.length() - 1) {
				collect(x.mid, prefix.append(x.c), i + 1, pattern, queue);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
		if (c == '.' || c > x.c) {
			collect(x.right, prefix, i, pattern, queue);
		}
	}
}
