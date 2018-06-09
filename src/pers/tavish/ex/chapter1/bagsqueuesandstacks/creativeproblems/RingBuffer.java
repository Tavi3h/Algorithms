package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

public class RingBuffer<Item> {

	private Item[] arr;
	private int putMarker = 0; // 放入数据标记
	private int getMarker = 0; // 取出数据标记

	@SuppressWarnings("unchecked")
	public RingBuffer(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException();
		}
		arr = (Item[]) new Object[N];
	}

	public boolean isEmpty() {
		return putMarker == getMarker;
	}

	public boolean isFull() {
		return arr.length == putMarker - getMarker;
	}

	public void putItem(Item item) {
		while (isFull()) {
			System.out.println("Buffer is Full ... ");
			return;
		}
		arr[(putMarker++) % arr.length] = item;
	}

	public Item getItem() {
		// 当空的时候，等待
		while (isEmpty()) {
			System.out.println("Buffer is Empty ... ");
			return null;
		}

		Item data = arr[getMarker % arr.length];
		arr[getMarker++ % arr.length] = null;
		return data;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	public static void main(String[] args) {

	}
}