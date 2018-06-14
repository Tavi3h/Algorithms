package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

// 提高题1.1.38 链表实现
public class GeneralizedQueueLinked<Item> {

	private int count; // 下一个结点的编号，不包括bm和em
	private int size; // 记录有数据的结点个数
	private Node<Item> beginMaker;
	private Node<Item> endMaker;

	private static class Node<Item> {

		public Item data;
		public Node<Item> next;
		public int index;

		// 除了bm和em每个结点都有他们加入链表时的编号，从1开始
		public Node(Item d, Node<Item> n, int idx) {
			data = d;
			next = n;
			index = idx;
		}

		public Node(Item d, Node<Item> n) {
			data = d;
			next = n;
		}
	}

	public GeneralizedQueueLinked() {
		endMaker = new Node<>(null, null);
		beginMaker = new Node<>(null, endMaker);
		size = 0;
		count = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(Item x) {
		add(endMaker, x);
	}

	private void add(Node<Item> p, Item x) {
		Node<Item> tmp = beginMaker;
		while (tmp.next != p) {
			tmp = tmp.next;
		}
		tmp.next = new Node<>(x, endMaker, ++count);
		size++;
	}

	public Item delete(int k) {

		if (k < 1 || k > count) {
			throw new IllegalArgumentException();
		}
		Node<Item> tmp = beginMaker;
		boolean find = false; // 设置标记
		while (tmp.next != endMaker) {
			if (tmp.next.index == k) {
				find = true;
				break;
			}
			tmp = tmp.next;
		}

		// 如果链表中已经没有第k次插入的数据了
		if (!find) {
			return null;
		}

		Node<Item> p = tmp.next;
		Item data = p.data;
		tmp.next = p.next;
		p.next = null;
		size--;

		return data;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<Item> p = beginMaker.next;
		while (p != endMaker) {
			sb.append(p.data + ", ");
			p = p.next;
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	public static void main(String[] args) {

	}
}
