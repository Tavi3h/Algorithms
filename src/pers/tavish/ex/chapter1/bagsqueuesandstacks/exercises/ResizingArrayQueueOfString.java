package pers.tavish.ex.chapter1.bagsqueuesandstacks.exercises;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;

// 练习题1.3.14
public class ResizingArrayQueueOfString {
	// 用于存储数据的数组
	private String[] arr;
	// 队列头index
	private int head;
	// 队列尾index
	private int tail;
	// 队列中元素个数
	private int N;

	public ResizingArrayQueueOfString(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException();
		}
		// 创建队列时初始化head和tail指向-1
		arr = new String[cap];
		this.head = -1;
		this.tail = -1;
	}

	// 返回元素个数是否为0
	public boolean isEmpty() {
		return N == 0;
	}

	// 返回元素个数
	public int size() {
		return N;
	}

	// 返回tail是否指向数组末尾
	public boolean isFull() {
		return tail == arr.length - 1;
	}

	// 入列
	public void enqueue(String s) {
		// 第一次入列时，将数据存入arr[0]，手动调整head和tail指向0
		if (head == -1 && tail == -1) {
			arr[0] = s;
			head = 0;
			tail = 0;
			N++;
			return;
		}

		// 如果tail指向数组末尾，数组大小调整为原来的2倍
		if (isFull()) {
			resize(arr.length * 2);
		}
		
		// tail向前移动并存储数据，元素个数加一
		arr[++tail] = s;
		N++;
	}

	// 出列
	public String dequeue() {
		// 如果队列中没有元素，则抛出异常
		if (isEmpty()) {
			throw new NoSuchElementException("No Elemenet in queue");
		}

		// 如果元素个数小于数组的四分之一，数组大小调整为原来的一半
		if (N <= arr.length / 4) {
			resize(arr.length / 2);
		}

		// 返回head指向的数据，将原数组元素至为null，并向前移动，同时元素个数减一
		String tmp = arr[head];
		arr[head++] = null;
		N--;
		return tmp;
	}

	@Override
	public String toString() {

		if (N == 0) {
			return "Head--> [] <--Tail" + ", size = " + N + ", length(arr) : " + arr.length;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("Head--> [");
		for (int i = head; i < tail; i++) {
			sb.append(arr[i] + ", ");
		}
		sb.append(arr[tail] + "] <--Tail");
		return sb.toString() + ", size = " + N + ", length(arr) : " + arr.length;
	}

	// 调整数组大小
	private void resize(int max) {
		// 创建临时数组
		String[] tmp = new String[max];
		// 对临时数组进行赋值
		for (int i = head, j = 0; i <= tail; i++, j++) {
			tmp[j] = arr[i];
		}
		// 将临时数组赋值给原数组
		arr = tmp;
		// 调整head和tail的指向
		head = 0;
		tail = N - 1;
	}
	
	// 练习题1.3.15
	public static void main(String[] args) {
		
		ResizingArrayQueueOfString queue = new ResizingArrayQueueOfString(10);
		while(!StdIn.isEmpty()) {
			queue.enqueue(StdIn.readString());
		}
		
		int k = Integer.parseInt(args[0]);
		System.out.println("k = " + k);
		
		int size = queue.size();
		
		for (int i = 1; i <= size - k; i++) {
			queue.dequeue();
		}
		
		System.out.println(queue.dequeue());
	}
}
