package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

public class Buffer {

	private int size;
	private int cursor; // 光标，链表中每个节点的编号为[0, size - 1]，bm为-1，em为size

	private Node<Character> beginMaker;
	private Node<Character> endMaker;

	@SuppressWarnings("hiding")
	private static class Node<Character> {

		public Node<Character> prev;
		public Node<Character> next;
		public Character data;

		public Node(Node<Character> prev, Node<Character> next, Character c) {
			this.prev = prev;
			this.next = next;
			this.data = c;
		}

	}

	public Buffer() {
		endMaker = new Node<>(null, null, null);
		beginMaker = new Node<>(null, endMaker, null);
		endMaker.prev = beginMaker;
		size = 0;
		cursor = -1;
	}

	// 在光标位置之后添加数据，添加后光标指向新节点
	public void insert(char c) {
		Node<Character> p = getCursorNode();
		Node<Character> newNode = new Node<>(p, p.next, c);

		p.next = newNode;
		newNode.next.prev = newNode;

		cursor++;
		size++;
	}

	// 删除光标指向的节点，删除后光标指向该节点的前一个节点
	public char delete() {
		if (cursor == -1) {
			return '\u0000';
		}

		Node<Character> p = getCursorNode();

		char data = p.data;

		p.next.prev = p.prev;
		p.prev.next = p.next;
		p.next = null;
		p.prev = null;

		cursor--;
		size--;

		return data;
	}

	public void left(int k) {
		for (int i = 0; i < k; i++) {
			cursor--;
		}
		// 如果移动之后光标在bm或bm左边，则令cusor指向bm
		cursor = cursor <= -1 ? -1 : cursor;
	}

	public void right(int k) {
		for (int i = 0; i < k; i++) {
			cursor++;
		}
		// 如果移动之后光标在em或em右边，则令cursor指向size - 1
		cursor = cursor >= size ? size - 1 : cursor;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<Character> p = beginMaker.next;
		for (int i = 0; i < size; i++) {
			sb.append(p.data + ", ");
			p = p.next;
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	private Node<Character> getCursorNode() {

		if (cursor == -1) {
			return beginMaker;
		}

		Node<Character> p = beginMaker.next;
		for (int i = 0; i < cursor; i++) {
			p = p.next;
		}

		return p;
	}

	public static void main(String[] args) {
		
	}
}
