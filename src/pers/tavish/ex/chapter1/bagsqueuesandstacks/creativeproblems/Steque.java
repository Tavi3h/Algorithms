package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import pers.tavish.ex.chapter1.bagsqueuesandstacks.linkedlistexercises.SLinkedList;

// 提高题 1.3.32
public class Steque<Item> {
	private SLinkedList<Item> sll;
	
	public int size() {
		return sll.size();
	}
	
	public boolean isEmpty() {
		return sll.isEmpty();
	}
	
	public Steque() {
		sll = new SLinkedList<>();
	}
	
	public void push(Item item) {
		sll.add(0, item);
	}
	
	public Item pop() {
		return sll.remove(0);
	}
	
	public void enqueue(Item item) {
		sll.add(item);
	}
	
	// 提高题 1.3.47
	public void catenation(Steque<Item> steque) {
		while(steque.size() != 0) {
			enqueue(steque.pop());
		}
	}
	
	@Override
	public String toString() {
		return sll.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
