package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

// 提高题1.1.38 数组实现
public class GeneralizedQueueArr<Item> {
	private Item[] arr;
	private int size;
	private int tail;

	@SuppressWarnings("unchecked")
	public GeneralizedQueueArr(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException();
		}
		arr = (Item[]) new Object[cap];
		size = 0;
		tail = -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(Item x) {
		// 如果tail指向数组右侧边界，则扩大数组
		if (tail == arr.length - 1) {
			resize(arr.length * 2);
		}

		// tail指针向右移动并保存数据
		arr[++tail] = x;
		size++;
	}

	public Item delete(int k) {
		if (k - 1 < 0 || k - 1 > tail) {
			throw new IllegalArgumentException();
		}
		Item data = arr[k - 1];
		arr[k - 1] = null;
		size--;
		return data;
	}

	@SuppressWarnings("unchecked")
	private void resize(int size) {
		Item[] tmp = (Item[]) new Object[size];
		for (int i = 0; i <= tail; i++) {
			tmp[i] = arr[i];
		}
		arr = tmp;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i <= tail; i++) {
			sb.append(arr[i] + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
