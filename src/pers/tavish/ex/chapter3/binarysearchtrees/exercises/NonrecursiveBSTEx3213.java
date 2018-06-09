package pers.tavish.ex.chapter3.binarysearchtrees.exercises;

// 练习题3.2.13
public class NonrecursiveBSTEx3213<Key extends Comparable<? super Key>, Value> {
	private Node<Key, Value> root;

	private static class Node<Key, Value> {
		private Key key; // sorted by key
		private Value val; // associated value
		private Node<Key, Value> left, right; // left and right subtrees

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	public void put(Key key, Value val) {
		if (root == null) {
			root = new Node<>(key, val);
			return;
		}

		Node<Key, Value> x = root;
		Node<Key, Value> parent = null;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			parent = x;
			if (cmp > 0) {
				x = x.right;
			} else if (cmp < 0) {
				x = x.left;
			} else {
				x.val = val;
				return;
			}
		}

		int cmp = key.compareTo(parent.key);

		if (cmp < 0) {
			parent.left = new Node<>(key, val);
		} else {
			parent.right = new Node<>(key, val);
		}
	}

	public Value get(Key key) {
		Node<Key, Value> x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {
				x = x.right;
			} else {
				return x.val;
			}
		}
		return null;
	}

	// 练习 3.2.14
	// 最小key
	public Key min() {
		Node<Key, Value> x = root;
		while (x.left != null) {
			x = x.left;
		}
		return x.key;
	}

	// 练习 3.2.14
	// 最大key
	public Key max() {
		Node<Key, Value> x = root;
		while (x.right != null) {
			x = x.right;
		}
		return x.key;
	}

	// 练习 3.2.14
	// 向下取整
	public Key floor(Key key) {
		Node<Key, Value> x = root;
		Node<Key, Value> t = null;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				x = x.left;
				continue;
			}
			if (cmp == 0 || x.right == null) {
				return x.key;
			}
			t = x;
			x = x.right;
		}
		return t == null ? null : t.key;
	}

	// 练习 3.2.14
	// 向上取整
	public Key ceiling(Key key) {
		Node<Key, Value> x = root;
		Node<Key, Value> t = null;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp > 0) {
				x = x.right;
				continue;
			}
			if (cmp == 0 || x.left == null) {
				return x.key;
			}
			t = x;
			x = x.left;
		}
		return t == null ? null : t.key;
	}

	public static void main(String[] args) {
		String[] strings = "20 10 30 7 15 25 40 11 17 14".split(" ");
		NonrecursiveBSTEx3213<Integer, Integer> bst = new NonrecursiveBSTEx3213<>();
		for (int i = 0; i < strings.length; i++) {
			bst.put(Integer.parseInt(strings[i]), i);
		}
		System.out.println(bst.min());
		System.out.println(bst.max());
		System.out.println(bst.floor(21));
		System.out.println(bst.ceiling(18));
	}

}
