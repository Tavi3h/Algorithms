package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import pers.tavish.ex.chapter1.bagsqueuesandstacks.linkedlistexercises.DLinkedList;

// 提高题 1.3.33 双向队列
public class Deque<Item> {

	private DLinkedList<Item> dll;
	
	public Deque() {
		dll = new DLinkedList<>();
	}
	
	public boolean isEmpty() {
		return dll.isEmpty();
	}
	
	public int size() {
		return dll.size();
	}
	
	public void pushLeft(Item item) {
		dll.addFirst(item);
	}
	
	public void pushRight(Item item) {
		dll.addLast(item);
	}
	
	public Item popLeft() {
		return dll.removeFirst();
	}
	
	public Item popRight() {
		return dll.removeLast();
	}
}
