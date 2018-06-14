package pers.tavish.ex.chapter1.bagsqueuesandstacks.linkedlistexercises;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// 单链表
public class SLinkedList<T> implements Iterable<T> {

	private Node<T> beginMarker; // 链表开始标记，first结点为beginMaker.next
	private Node<T> endMarker; // 链表结束标记
	private int modCount = 0; // 记录修改次数
	private int theSize; // 记录链表大小

	// 静态内部类，用于生成Node
	private static class Node<T> {

		public T data;
		public Node<T> next;

		public Node(T d, Node<T> n) {
			data = d;
			next = n;
		}
	}

	// 初始化链表
	public SLinkedList() {
		doClear();
	}

	// 返回链表大小
	public int size() {
		return theSize;
	}

	// 返回链表是否为空
	public boolean isEmpty() {
		return theSize == 0;
	}

	// 重置链表
	public void clear() {
		doClear();
	}

	public String toString() {
		if (size() == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			Node<T> node = beginMarker.next;
			for (int i = 0; i < theSize; i++) {
				sb.append(node.data + ", ");
				node = node.next;
			}
			sb.replace(sb.length() - 2, sb.length(), "]");
			return sb.toString();
		}
	}

	// 在尾部添加一个结点
	public boolean add(T x) {
		return add(theSize, x);
	}

	// 在指定位置添加一个结点
	public boolean add(int idx, T x) {
		if (idx < 0 || idx > theSize) {
			return false;
		}
		return add(getPreNode(idx), x);
	}

	// 替换指定位置结点的值
	public T set(int idx, T newVal) {
		if (idx < 0 || idx > theSize - 1) {
			return null;
		}
		Node<T> p = getPreNode(idx).next;
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}

	// 返回指定位置上结点的值
	public T get(int idx) {
		if (idx < 0 || idx > theSize - 1) {
			return null;
		}
		return getPreNode(idx).next.data;
	}

	// 删除指定位置上的结点
	public T remove(int idx) {
		if (idx < 0 || idx > theSize - 1) {
			return null;
		}
		return remove(getPreNode(idx));
	}

	// 测试x是否在链表中
	public boolean contains(T x) {
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			if (it.next().equals(x)) {
				return true;
			}
		}
		return false;
	}

	// 如果被测试元素x不在链表中，则添加x
	// true: x在链表中
	// false: x不在链表中，将x添加到链表末段
	public boolean containsAdd(T x) {
		return contains(x) ? true : !add(x);
	}

	// 如果被测试元素x在链表中则删除x
	// true: x在链表中，并将其删除
	// false: x不在链表中
	public boolean containsRemove(T x) {
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			if (it.next().equals(x)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	// 链表练习1.3.24
	// 删除idx指代的结点的后续结点
	public T removeAfter(int idx) {
		// 如果idx小于0或大于等于theSize - 1，直接返回null
		if (idx < 0 || idx >= theSize - 1) {
			return null;
		}
		Node<T> cur = getPreNode(idx).next;
		return remove(cur);
	}

	// 链表练习1.3.25
	public boolean insertAfter(T x, T y) {
		return add(x) && add(y);
	}

	// 对链表进行初始化
	private void doClear() {
		// 创建两个空节点，并将beginMaker.next指向endMaker
		beginMarker = new Node<>(null, null);
		endMarker = new Node<>(null, null);
		beginMarker.next = endMarker;
		// 初始化数组大小
		theSize = 0;
		modCount++;
	}

	private boolean add(Node<T> p, T x) {
		Node<T> newNode = new Node<>(x, p.next);
		p.next = newNode;
		theSize++;
		modCount++;
		return true;
	}

	// 删除结点p.next
	private T remove(Node<T> p) {
		Node<T> tmp = p.next;
		T data = tmp.data;
		p.next = tmp.next;
		tmp = null;
		theSize--;
		modCount++;
		return data;
	}

	// 返回idx指代的结点的前一个结点
	private Node<T> getPreNode(int idx) {
		Node<T> p = beginMarker;
		for (int i = 0; i < idx; i++) {
			p = p.next;
		}
		return p;
	}

	// 返回结点n的前一个结点
	private Node<T> getPreNode(Node<T> n) {

		Node<T> p = beginMarker;
		for (int i = 0; i < theSize; i++) {
			if (p.next == n) {
				break;
			}
			p = p.next;
		}
		return p;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			public Node<T> current = beginMarker;
			private boolean okToRemove = false;
			private int expectedModCount = modCount;

			@Override
			public boolean hasNext() {
				return current.next != endMarker;
			}

			@Override
			public T next() {
				if (modCount != expectedModCount) {
					throw new ConcurrentModificationException();
				}
				if (!hasNext()) {
					throw new NoSuchElementException();
				}

				T nextItem = current.next.data;
				current = current.next;
				okToRemove = true;
				return nextItem;
			}

			@Override
			public void remove() {
				if (modCount != expectedModCount) {
					throw new ConcurrentModificationException();
				}
				if (!okToRemove) {
					throw new IllegalStateException();
				}
				SLinkedList.this.remove(getPreNode(current));
				expectedModCount++;
				okToRemove = false;
			}
		};
	}

	/*
	 * 静态方法
	 */

	// 链表练习1.3.21
	public static <T> boolean find(SLinkedList<T> list, T key) {
		return list.contains(key);
	}

	// 链表练习1.3.26
	public static <T> void remove(SLinkedList<T> list, T key) {
		Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(key)) {
				iterator.remove();
			}
		}
	}

	// 链表练习1.3.27
	public static <T extends Comparable<? super T>> T max(SLinkedList<T> list) {

		if (list.size() == 0) {
			return null;
		}

		Iterator<T> iterator = list.iterator();
		T max = iterator.next();
		while (iterator.hasNext()) {
			T tmp = iterator.next();
			if (tmp.compareTo(max) > 0) {
				max = tmp;
			}
		}
		return max;
	}

	// 链表练习1.3.30
	// 返回该链表的beginMaker
	private Node<T> getBM() {
		return beginMarker;
	}

	// 返回该链表的endMaker
	private Node<T> getEM() {
		return endMarker;
	}

	// 破坏性地反转list
	public static <T> SLinkedList<T> reverse(SLinkedList<T> list) {
		if (list == null) {
			throw new IllegalArgumentException();
		}

		// 获取开始结束标记
		Node<T> bm = list.getBM();
		Node<T> em = list.getEM();

		// 对链表进行反转
		Node<T> tmp = em;
		// 假设原链表：bm --> a --> b --> c --> em, tmp = em
		// 经过一轮变换：bm --> b --> c --> em, a --> em, tmp = a
		// 经过两轮变换：bm --> c --> em, b --> a --> em, tmp = b
		// 经过三轮变换：bm --> em, c --> b --> a --> em, tmp = c
		// 最后bm.next = tmp：bm --> c --> b --> a --> em
		for (Node<T> p = bm.next; p != em; p = bm.next) {
			bm.next = p.next;
			p.next = tmp;
			tmp = p;
		}
		bm.next = tmp;
		
		return list;
	}

	public static void main(String[] args) {
		SLinkedList<Integer> list = new SLinkedList<>();
		for (int i = 0; i < 2; i++) {
			list.add(i);
		}
		System.out.println(list);
		System.out.println(reverse(list));
		System.out.println(list);
	}
}
