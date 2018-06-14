package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

// 练习题3.1.3 有序链表实现符号表
public class OrderedSequentialSearchST<Key extends Comparable<? super Key>, Value> {

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

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Value get(Key key) {
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}

	public void put(Key key, Value value) {
		Node<Key, Value> pre = first;
		for (Node<Key, Value> x = first; x != null && x.key.compareTo(key) < 0; pre = x, x = x.next) {
			if (x.key.compareTo(key) == 0) {
				x.val = value;
				return;
			}
		}
		if (pre == first) {
			first = new Node<>(key, value, first);
		} else {
			pre.next = new Node<>(key, value, pre.next);
		}
		N++;
	}
}
