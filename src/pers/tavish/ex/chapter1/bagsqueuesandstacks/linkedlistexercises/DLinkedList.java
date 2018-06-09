package pers.tavish.ex.chapter1.bagsqueuesandstacks.linkedlistexercises;


// 双向链表，链表练习1.3.31
public class DLinkedList<T> {
	
	private int size; // 链表元素个数
	private DoubleNode<T> beginMaker; // 链表的起始标记，无prev，指向链表中第一个结点
	private DoubleNode<T> endMaker; // 链表结束标记，无next，链表的最后一个结点指向该标记

	private static class DoubleNode<T> {
		public T data;
		public DoubleNode<T> prev;
		public DoubleNode<T> next;

		public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	public DLinkedList() {
		doClear();
	}

	// 初始化链表
	public void doClear() {
		beginMaker = new DoubleNode<>(null, null, null);
		endMaker = new DoubleNode<>(null, beginMaker, null);
		beginMaker.next = endMaker;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	// 根据data查找其结点编号，如果没有找到返回-1
	public int indexOf(T key) {
		DoubleNode<T> p = beginMaker.next;
		for (int i = 0; i < size; i++) {
			if (p.data.equals(key)) {
				return i;
			} 
			p = p.next;
		}
		return -1;
	}

	// 在表头插入结点
	public boolean addFirst(T x) {
		return add(beginMaker, x);
	}

	// 在表尾插入结点
	public boolean addLast(T x) {
		return add(endMaker.prev, x);
	}

	// 在指定位置之前插入新结点
	public boolean addBefore(int idx, T x) {
		if (idx == 0) {
			return addFirst(x);
		}
		return add(idx - 1, x);
	}

	// 在指定位置之后插入结点
	public boolean addAfter(int idx, T x) {
		if (idx == size - 1) {
			return addLast(x);
		}
		return add(idx, x);
	}

	// 从表头删除结点
	public T removeFirst() {
		if (isEmpty()) {
			return null;
		}
		return remove(beginMaker.next);
	}

	// 从表尾删除结点
	public T removeLast() {
		if (isEmpty()) {
			return null;
		}
		return remove(endMaker.prev);
	}

	// 在指定位置删除结点
	public T remove(int idx) {
		return remove(getNode(idx));
	}

	// 在idx指代的结点之后添加结点
	private boolean add(int idx, T x) {
		if (idx < 0 || idx > size - 1) {
			return false;
		}

		return add(getNode(idx), x);
	}

	// 在结点p之后添加结点
	private boolean add(DoubleNode<T> p, T x) {
		DoubleNode<T> tmp = new DoubleNode<>(x, p, p.next);
		p.next.prev = tmp;
		p.next = tmp;
		size++;
		return true;
	}

	// 删除结点p
	private T remove(DoubleNode<T> p) {
		T data = p.data;
		p.prev.next = p.next;
		p.next.prev = p.prev;
		p.prev = null;
		p.next = null;
		size--;
		return data;
	}

	// 返回idx指代的结点
	private DoubleNode<T> getNode(int idx) {
		
		if (idx < 0 || idx > size - 1) {
			throw new IllegalArgumentException();
		}

		DoubleNode<T> p = null;
		if (idx <= size / 2) {
			// 如果idx小于等于size的一半，则从beginMaker.next开始向后遍历
			p = beginMaker.next;
			for (int i = 0; i < idx; i++) {
				p = p.next;
			}
		} else {
			// 否则从endMaker.prev开始向前遍历
			p = endMaker.prev;
			for (int i = size - 1; i > idx; i--) {
				p = p.prev;
			}
		}
		// 遍历结束，结点p为idx指定的结点
		return p;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		DoubleNode<T> p = beginMaker.next;
		for (int i = 0; i < size; i++) {
			sb.append(p.data + ", ");
			p = p.next;
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();

	}

	public static void main(String[] args) {
		DLinkedList<String> dll = new DLinkedList<>();
		
		System.out.println(dll.indexOf("hello"));
	}
}
