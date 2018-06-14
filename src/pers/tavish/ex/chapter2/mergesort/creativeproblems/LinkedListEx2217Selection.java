package pers.tavish.ex.chapter2.mergesort.creativeproblems;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdRandom;

// 提高题2.2.17
public class LinkedListEx2217Selection<T> {

	private int n; // 数组大小
	private Node<T> beginMarker; // 链表起始标记
	private Node<T> endMarker; // 链表结束标记

	private static class Node<T> {
		private Node<T> next;
		private T value;

		public Node(T value) {
			this.next = null;
			this.value = value;
		}
	}

	public LinkedListEx2217Selection() {
		doClear();
	}

	public void doClear() {
		n = 0;
		beginMarker = new Node<>(null);
		endMarker = new Node<>(null);
		beginMarker.next = endMarker;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	// 在链表尾部添加一个结点，其value为t
	public boolean add(T t) {
		Node<T> newNode = new Node<>(t);
		Node<T> node = getPreNode(endMarker);
		node.next = newNode;
		newNode.next = endMarker;
		n++;
		return true;
	}

	// 获得node的前一个结点
	private Node<T> getPreNode(Node<T> node) {
		Node<T> tmp = beginMarker;
		while (tmp.next != node) {
			tmp = tmp.next;
		}
		return tmp;
	}

	// 对链表中的结点，按照value进行升序排序
	// 该方法没有创建新的结点，即没有产生额外的空间消耗
	// 基于选择排序的思想
	public void sort(Comparator<T> comparator) {
		
		if (n <= 1 || isSorted(comparator)) {
			return;
		}

		Node<T> start = beginMarker.next;
		
		for (int i = 0; i < n - 1; i++) {
			
			Node<T> minNode = start; // 记录最小结点
			T minValue = minNode.value; // 最小结点的值

			// 寻找最小结点
			Node<T> tmp = start;
			while (tmp.next != endMarker) {
				tmp = tmp.next;
				if (comparator.compare(tmp.value, minValue) < 0) {
					minNode = tmp;
					minValue = tmp.value;
				}
			}

			// 入如果最小结点没有发生变化，start结点向后移动一位，重新开始循环
			if (minNode == start) {
				start = start.next;
				continue;
			}

			// 对结点进行操作，将最小结点移动到当前start结点之后
			Node<T> preMinNode = getPreNode(minNode);
			preMinNode.next = minNode.next;
			Node<T> preStart = getPreNode(start);
			preStart.next = minNode;
			minNode.next = start;
		}
	}

	// 判断链表是否已经排序
	private boolean isSorted(Comparator<T> comparator) {
		Node<T> tmp = beginMarker.next;
		while (tmp.next != endMarker) {
			if (comparator.compare(tmp.next.value, tmp.value) < 0) {
				return false;
			}
			tmp = tmp.next;
		}
		return true;
	}

	@Override
	public String toString() {
		if (n == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			Node<T> node = beginMarker.next;
			for (int i = 0; i < n; i++) {
				sb.append(node.value + ", ");
				node = node.next;
			}
			sb.replace(sb.length() - 2, sb.length(), "]");
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		LinkedListEx2217Selection<Integer> list = new LinkedListEx2217Selection<>();

		for (int i = 0; i < 10; i++) {
			list.add(StdRandom.uniform(100));
		}

		// 传入比较器
		list.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		System.out.println(list);
	}
}
