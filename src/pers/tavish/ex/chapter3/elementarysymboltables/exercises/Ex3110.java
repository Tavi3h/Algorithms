package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

import edu.princeton.cs.algs4.Queue;

// 练习题3.1.10
class SequentialSearchSTEx3110<Key, Value> {
	private Node<Key, Value> first; // 链表首结点

	private int N;
	
	private int count; // 记录比较次数
	
	public int getCount() { // 返回count的方法
		return count;
	}

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

	// 查找指定的键，返回相关联的值
	public Value get(Key key) {
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}

	// 查找给定的键，找到则更新其值，否则在表中新建结点插入表头
	public void put(Key key, Value val) {
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			count++;
			if (key.equals(x.key)) {
				x.val = val; // 命中，更新
				return;
			}
		}
		first = new Node<>(key, val, first); // 未命中，新建结点
		N++;
		show();
	}

	// 打印当前所有key的方法
	private void show() {
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			System.out.print(x.key + " ");
		}
		System.out.println();
	}

	// 根据键删除指定的键值对
	public Value delete(Key key) {
		if (size() == 0) {
			throw new UnsupportedOperationException("ST underflow...");
		}

		Value value = null;
		Node<Key, Value> pre = first;
		for (Node<Key, Value> x = first; x != null; pre = x, x = x.next) {
			if (x.key.equals(key)) {
				value = x.val;
				pre.next = x.next;
				x = null;
				break;
			}
		}
		N--;
		return value;
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}
}


public class Ex3110 {
	
	public static void main(String[] args) {
		SequentialSearchSTEx3110<String, Integer> st = new SequentialSearchSTEx3110<>();
		String string = "E A S Y Q U E S T I O N";
		for (String s : string.split(" ")) {
			st.put(s, 0);
		}
		System.out.println(st.getCount());
	}
}
