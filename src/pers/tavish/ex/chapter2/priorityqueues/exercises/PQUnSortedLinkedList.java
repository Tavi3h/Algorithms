package pers.tavish.ex.chapter2.priorityqueues.exercises;

// 练习题2.4.3 无序链表实现优先队列
// 使用双向链表
public class PQUnSortedLinkedList<T extends Comparable<? super T>> {

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

	public PQUnSortedLinkedList() {
		beginMarker = new Node<>(null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;
		N = 0;
	}

	// 插入元素到链表尾，复杂度为O(1)
	public void insert(T t) {
		Node<T> node = new Node<>(t);
		node.next = endMarker;
		node.prev = endMarker.prev;
		endMarker.prev.next = node;
		endMarker.prev = node;
		N++;
	}

	// 删除并返回最大元素
	// 由于getMaxNode()方法，算法复杂度为O(N)
	public T delMax() {

		if (N == 0) {
			throw new UnsupportedOperationException("List Underflow...");
		}

		Node<T> node = getMaxNode(); // 获取最大元素的node
		T val = node.value;
		Node<T> prevNode = node.prev;
		Node<T> nextNode = node.next;

		prevNode.next = nextNode;
		nextNode.prev = prevNode;

		node = null;
		N--;
		return val;
	}

	// 复杂度为O(N)
	private Node<T> getMaxNode() {
		if (N == 1) {
			return beginMarker.next;
		}

		Node<T> maxNode = beginMarker.next;
		T maxValue = maxNode.value;

		for (Node<T> i = maxNode.next; i != endMarker; i = i.next) {
			T val = i.value;
			if (less(maxValue, val)) {
				maxNode = i;
				maxValue = val;
			}
		}

		return maxNode;
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
