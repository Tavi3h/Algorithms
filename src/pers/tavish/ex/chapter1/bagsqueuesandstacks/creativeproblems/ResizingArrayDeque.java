package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

//提高题 1.3.33 双向队列数组实现
public class ResizingArrayDeque<Item> {
	private Item[] arr; // 内部使用的数组
	private int N; // 元素个数
	private int head; // 队列头，指向队列最左端的元素
	private int tail; // 队列尾，指向队列最右端的元素

	public ResizingArrayDeque() {
		this(10);
	}

	@SuppressWarnings("unchecked")
	public ResizingArrayDeque(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException();
		}
		arr = (Item[]) new Object[cap];
		head = -1;
		tail = -1;
	}

	// 返回元素个数是否为0
	public boolean isEmpty() {
		return N == 0;
	}

	// 返回元素个数
	public int size() {
		return N;
	}

	public void pushLeft(Item item) {
		// 如果head和tail均指向-1，则将元素添加到0角标上，并设置head和tail指向0
		if (head == -1 && tail == -1) {
			arr[0] = item;
			head = 0;
			tail = 0;
			N++;
			return;
		}

		// 如果从左侧添加元素时，head指向0，说明元素填充已达到数组左边缘
		// 则将数组大小调整为当前元素个数的3倍
		if (head == 0) {
			enlarge();
		}

		// head指针向左移动并添加item
		arr[--head] = item;
		N++;
	}

	public void pushRight(Item item) {
		// 如果head和tail均指向-1，则将元素添加到0角标上，并设置head和tail指向0
		if (head == -1 && tail == -1) {
			arr[0] = item;
			head = 0;
			tail = 0;
			N++;
			return;
		}

		// 如果从右侧添加元素时，tail指向arr.length - 1，说明元素填充已达到数组右边缘
		// 则将数组大小调整为当前元素个数的3倍
		if (tail == arr.length - 1) {
			enlarge();
		}

		// tail指针向右移动并添加item
		arr[++tail] = item;
		N++;
	}

	public Item popLeft() {
		// 如果数组中没有元素则返回null
		if (N == 0) {
			return null;
		}

		// 记录head指针指向的元素
		Item data = arr[head];
		// 将head指针当前指向的元素置为null，head指针向右移动
		arr[head++] = null;
		N--;
		// 如果当前元素数量不为0，且元素数量的4倍小于等于arr.length
		// 说明此时数组长度过大，将数组大小调整为当前元素个数的2倍
		if (4 * N <= arr.length && N != 0) {
			reduce();
		}
		return data;
	}

	public Item popRight() {
		// 如果数组中没有元素则返回null
		if (N == 0) {
			return null;
		}
		// 记录tail指针指向的元素
		Item data = arr[tail];
		// 将tail指针当前指向的元素置为null，tail指针向左移动
		arr[tail--] = null;
		N--;
		// 如果当前元素数量不为0，且元素数量的4倍小于等于arr.length
		// 说明此时数组长度过大，将数组大小调整为当前元素个数的2倍
		if (4 * N <= arr.length && N != 0) {
			reduce();
		}
		return data;
	}

	// 减小数组长度
	@SuppressWarnings("unchecked")
	private void reduce() {
		Item[] tmp = (Item[]) new Object[2 * N];
		// 调整大小时将原数组的数据放在中间，左右留出一定的空间用于可能的push操作
		for (int i = head, j = N / 2; i <= tail; i++, j++) {
			tmp[j] = arr[i];
		}
		arr = tmp;
		head = N / 2;
		tail = N + head - 1;
	}

	// 增大数组长度
	@SuppressWarnings("unchecked")
	private void enlarge() {
		Item[] tmp = (Item[]) new Object[3 * N];

		// 调整大小时将原数组的数据放在中间，左右留出足够的空间用于可能的push操作
		for (int i = head, j = N; i <= tail; i++, j++) {
			tmp[j] = arr[i];
		}
		arr = tmp;
		head = N;
		tail = N + head - 1;
	}

	@Override
	public String toString() {
		
		if (N == 0) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = head; i <= tail; i++) {
			sb.append(arr[i] + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	public static void main(String[] args) {
		
	}
}
