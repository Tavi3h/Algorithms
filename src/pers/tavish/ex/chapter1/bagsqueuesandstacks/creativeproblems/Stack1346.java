package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Stack;

// 提高题 1.3.46
public class Stack1346<Item extends Comparable<? super Item>> {

	private Stack<Item> stack;

	private Item lastForbidItem;

	public Stack1346() {
		stack = new Stack<>();
		lastForbidItem = null;
	}

	public void push(Item key) {

		if ((lastForbidItem != null && key.compareTo(lastForbidItem) > 0) || !check(key)) {
			lastForbidItem = key;
			System.out.println("违反规则， " + key + "不能入栈");
			return;
		}

		stack.push(key);
	}

	private boolean check(Item key) {

		// 如果栈大小小于等于1，直接返回true
		if (stack.size() <= 1) {
			return true;
		}
		
		
		

		List<Item> list = new ArrayList<>();
		// 将stack中所有小于key的值存入list
		for (Item item : stack) {
			if (item.compareTo(key) < 0) {
				list.add(item);
			}
		}

		// 如果list的大小大于等于2，进行进一步检查
		if (list.size() >= 2) {
			return check(list);
		} else {
			return true;
		}
	}

	private boolean check(List<Item> list) {

		// 循环比较list中是否存在某个数大于后面的数
		for (int i = list.size() - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (list.get(i).compareTo(list.get(j)) > 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return stack.toString();
	}

	public static void main(String[] args) {

	}
}
