package pers.tavish.ex.chapter2.priorityqueues.exercises;

// 练习题2.4.3 有序链表实现优先队列
// 使用双向链表
public class PQSortedLinkedList<T extends Comparable<? super T>> {

	private int N; // 链表包含的元素数量
	private Node<T> beginMarker; // 定义链表起始标记
	private Node<T> endMarker; // 定义链表结束标志

	// 定义结点
	private static class Node<T> {
		T value;
		Node<T> prev;
		Node<T> next;

		public Node(T value) {
			this(value, null, null);
		}

		public Node(T value, Node<T> prev, Node<T> next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	public PQSortedLinkedList() {
		beginMarker = new Node<>(null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;
		N = 0;
	}

	// 基于向有序数组中插入元素，复杂度为O(N)
	public void insert(T t) {
		Node<T> newNode = new Node<T>(t);
		if (++N == 1) {
			beginMarker.next = newNode;
			newNode.next = endMarker;
			endMarker.prev = newNode;
			newNode.prev = beginMarker;
			return;
		}
		
		Node<T> node = beginMarker.next;
		while (node != endMarker) {
			if (less(newNode.value, node.value)) {
				// 对链表进行操作，将新元素放入正确的位置。
				node.prev.next = newNode;
				newNode.prev = node.prev;
				node.prev = newNode;
				newNode.next = node;
				return;
			}
			node = node.next;
		}
		// 链表中元素均小于等于新元素，将新元素添加到链表尾
		endMarker.prev.next = newNode;
		newNode.next = endMarker;
		newNode.prev = endMarker.prev;
		endMarker.prev = newNode;
	}

	// 删除并返回最大元素，复杂度O(1)
	public T delMax() {
		if (N == 0) {
			throw new UnsupportedOperationException("List Underflow...");
		}
		Node<T> node = endMarker.prev;
		T value = node.value;
		endMarker.prev = node.prev;
		node.prev.next = endMarker;
		node = null;
		N--;
		return value;
	}

	private boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	@Override
	public String toString() {
		if (N == 0) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> p = beginMarker.next;
		for (int i = 0; i < N; i++) {
			sb.append(p.value + ", ");
			p = p.next;
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	public static void main(String[] args) {
		
	}
}
