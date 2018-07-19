package pers.tavish.code.chapter5.tries;

import edu.princeton.cs.algs4.Queue;

// R（256）向单词查找树
public class TrieST<Value> {

	private static final int R = 256; // extended ASCII

	private Node<Value> root; // 根结点
	private int n; // trie中键的数量

	// R向trie结点
	@SuppressWarnings("unchecked")
	private static class Node<Value> {
		private Value val;
		private Node<Value>[] next = new Node[R];
	}

	/*
	 * 返回键对应的值
	 */
	public Value get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		Node<Value> x = get(root, key, 0);
		return x == null ? null : x.val;
	}

	private Node<Value> get(Node<Value> x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (d == key.length()) {
			return x;
		}
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}

	/*
	 * 判断符号表是否包含给定的可以
	 */
	public boolean contains(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/*
	 * 向符号表中插入键值对，插入时新值会覆盖原值，如果value为空，则删除对应的key
	 */
	public void put(String key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}
		if (val == null) {
			delete(key);
		} else {
			root = put(root, key, val, 0);
		}
	}

	private Node<Value> put(Node<Value> x, String key, Value val, int d) {
		if (x == null) {
			x = new Node<Value>();
		}
		if (d == key.length()) {
			if (x.val == null) {
				n++;
			}
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
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
	 * 删除指定的key（如果存在）
	 */
	public void delete(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		root = delete(root, key, 0);
	}

	private Node<Value> delete(Node<Value> x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (d == key.length()) {
			if (x.val != null) {
				n--;
			}
			x.val = null;
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}

		// remove subtrie rooted at x if it is completely empty
		if (x.val != null) {
			return x;
		}

		for (int c = 0; c < R; c++) {
			if (x.next[c] != null) {
				return x;
			}
		}
		return null;
	}

	/*
	 * 查找符号表中所有键
	 */
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}

	/*
	 * 返回所有以prefix为前缀的键
	 */
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> results = new Queue<>();
		Node<Value> x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}

	private void collect(Node<Value> x, StringBuilder prefix, Queue<String> results) {
		if (x == null) {
			return;
		}
		if (x.val != null) {
			results.enqueue(prefix.toString());
		}
		for (char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	/*
	 * 按照模式匹配，其中"."作为通配符
	 */
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> results = new Queue<>();
		collect(root, new StringBuilder(), pattern, results);
		return results;
	}

	private void collect(Node<Value> x, StringBuilder prefix, String pattern, Queue<String> results) {
		if (x == null) {
			return;
		}
		int d = prefix.length();
		if (d == pattern.length() && x.val != null) {
			results.enqueue(prefix.toString());
		}
		if (d == pattern.length()) {
			return;
		}
		char c = pattern.charAt(d);
		if (c == '.') {
			for (char ch = 0; ch < R; ch++) {
				prefix.append(ch);
				collect(x.next[ch], prefix, pattern, results);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		} else {
			prefix.append(c);
			collect(x.next[c], prefix, pattern, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	/*
	 * query的前缀中最长的键
	 */
	public String longestPrefixOf(String query) {
		if (query == null) {
			throw new IllegalArgumentException("argument to longestPrefixOf() is null");
		}
		int length = longestPrefixOf(root, query, 0, -1);
		if (length == -1) {
			return null;
		} else {
			return query.substring(0, length);
		}
	}

	private int longestPrefixOf(Node<Value> x, String query, int d, int length) {
		if (x == null) {
			return length;
		}
		if (x.val != null) {
			length = d;
		}
		if (d == query.length()) {
			return length;
		}
		char c = query.charAt(d);
		return longestPrefixOf(x.next[c], query, d + 1, length);
	}

}
