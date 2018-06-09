package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// 提高题 1.3.35 随机队列
public class RandomQueue<Item> implements Iterable<Item> {
	private Item[] arr; // 内部维护的数组
	private int tail; // 指向数组最后一个元素
	private int size; // 元素数量

	// 构造函数
	@SuppressWarnings("unchecked")
	public RandomQueue(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException();
		}
		// 初始化时指定tail指向-1
		arr = (Item[]) new Object[cap];
		size = 0;
		tail = -1;
	}

	// 返回元素个数是否为0
	public boolean isEmpty() {
		return size == 0;
	}

	// 返回元素个数
	public int size() {
		return size;
	}

	// 添加元素
	public void enqueue(Item item) {

		// 如果tail指向数组右侧边界，则扩大数组
		if (tail == arr.length - 1) {
			resize(arr.length * 2);
		}

		// tail指针向右移动并保存数据
		arr[++tail] = item;
		size++;
	}

	// 出列
	public Item dequeue() {
		if (size == 0) {
			return null;
		}

		swap(randomIdx(), tail);

		Item item = arr[tail];
		arr[tail--] = null;
		size--;

		if (size <= arr.length / 4) {
			resize(arr.length / 2);
		}

		return item;
	}

	// 取样
	public Item sample() {
		if (size == 0) {
			return null;
		}
		return arr[randomIdx()];
	}

	// 返回[0, tail]之间的一个随机整数
	private int randomIdx() {
		return new Random().nextInt(tail + 1);
	}

	// 交换idx和tail指向的数据
	private void swap(int idx, int tail) {
		Item tmp = arr[idx];
		arr[idx] = arr[tail];
		arr[tail] = tmp;
	}

	// 调整数组大小
	@SuppressWarnings("unchecked")
	private void resize(int n) {
		Item[] tmp = (Item[]) new Object[n];
		for (int i = 0; i <= tail; i++) {
			tmp[i] = arr[i];
		}
		arr = tmp;
		tail = size - 1;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i <= tail; i++) {
			sb.append(arr[i] + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	// 提高题 1.3.36 随机迭代器
	public Iterator<Item> iterator() {
		return new RandomIterator();

	}

	private class RandomIterator implements Iterator<Item> {

		private Item[] tmp; // 去除arr数组中tail指针的所有右侧元素
		private List<Item> list; // tmp数组转为list
		private Iterator<Item> iter; // 内部维护的迭代器

		@SuppressWarnings("unchecked")
		public RandomIterator() {
			// 对tmp数组进行赋值
			tmp = (Item[]) new Object[size];
			for (int i = 0; i <= tail; i++) {
				tmp[i] = arr[i];
			}

			list = Arrays.asList(tmp); // 将tmp数组转为list
			Collections.shuffle(list); // 对list进行随机打乱（此时tmp数组）
			iter = list.iterator(); // 初始化iter为打乱后的list的迭代器
		}

		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}

		@Override
		public Item next() {
			return iter.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		
	}
}
