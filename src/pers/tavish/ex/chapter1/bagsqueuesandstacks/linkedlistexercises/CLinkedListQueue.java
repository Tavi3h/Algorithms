package pers.tavish.ex.chapter1.bagsqueuesandstacks.linkedlistexercises;

// 链表练习 1.3.29 单向循环链表实现Queue
public class CLinkedListQueue<T> {

	private int size; // 队列大小
	private Node<T> last; // 队列尾部标记，指向队列头；队列尾指向该标记

	// 静态内部类，用于生成Node
	private static class Node<T> {

		public T data;
		public Node<T> next;

		public Node(T d, Node<T> n) {
			data = d;
			next = n;
		}
	}

	// 返回队列是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	// 返回size
	public int size() {
		return size;
	}

	// 出列
	public T dequeue() {
		if (size == 0) {
			return null;
		}

		Node<T> tmp = last.next;
		T data = tmp.data;
		last.next = tmp.next;
		tmp.next = null;
		size--;
		return data;
	}

	// 入列
	public boolean enqueue(T x) {

		Node<T> p = last;
		for (int i = 0; i < size; i++) {
			p = p.next;
		}
		return add(x, p);
	}

	private boolean add(T x, Node<T> p) {

		Node<T> tmp = new Node<>(x, last);
		p.next = tmp;
		size++;
		return true;
	}

	// 构造函数
	public CLinkedListQueue() {
		doClear();
	}

	// 重置队列
	public void doClear() {
		last = new Node<T>(null, null);
		size = 0;
	}

	public static void main(String[] args) {

	}
}
