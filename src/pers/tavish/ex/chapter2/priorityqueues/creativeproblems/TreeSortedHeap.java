package pers.tavish.ex.chapter2.priorityqueues.creativeproblems;

import edu.princeton.cs.algs4.StdRandom;

// 提高题 2.4.44
// 写的比较烂，比较次数太多，而且引入了随机数，程序不可控，但可以满足基本需求。
// 没有准确评估耗时，估计在平均情况下为logN，因为insert和delMax基本每次只遍历树的一半
public class TreeSortedHeap<T extends Comparable<? super T>> {

	private int N; // 记录结点个数
	private TreeNode<T> beginMarker; // 开始节点

	private static class TreeNode<T> {
		// 三个指针
		TreeNode<T> prev; // 指向上一个结点
		TreeNode<T> left; // 指向左下结点
		TreeNode<T> right; // 指向右下结点
		T value; // 记录结点的值

		public TreeNode(T value) {
			this(value, null, null, null);
		}

		public TreeNode(T value, TreeNode<T> prev, TreeNode<T> left, TreeNode<T> right) {
			this.prev = prev;
			this.left = left;
			this.right = right;
			this.value = value;
		}
	}

	public TreeSortedHeap() {
		beginMarker = new TreeNode<>(null);
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void insert(T val) {

		// 创建新结点
		TreeNode<T> node = new TreeNode<>(val);

		if (N == 0) {
			// N = 0时直接将新结点放在beginMarker的left上
			beginMarker.left = node;
			node.prev = beginMarker;
		} else {
			// 寻找一个合适的位置添加该新结点
			TreeNode<T> firstNode = beginMarker.left;
			while (true) {
				if (firstNode.left == null) {
					// 结点左端为null，则添加新结点到左端
					firstNode.left = node;
					break;
				} else if (firstNode.right == null) {
					// 结点有端为null，则添加新结点到右端
					firstNode.right = node;
					break;
				} else if (StdRandom.bernoulli()) { // 左右两端都不为null，则随机向左右移动
					firstNode = firstNode.left;
				} else {
					firstNode = firstNode.right;
				}
			}
			node.prev = firstNode; // 设置node结点的prev指针
		}
		
		// 上浮该结点
		swim(node);
		N++;
	}

	public T delMax() {
		if (N == 0) {
			throw new UnsupportedOperationException("Tree Underflow...");
		}

		T maxValue = beginMarker.left.value; // 最大值为堆顶结点的值

		TreeNode<T> node = beginMarker.left;
		// 寻找一个合适的叶结点，用于与最大元素结点交换值
		while (!(node.left == null && node.right == null)) {
			// 左右结点至少有一个不为null
			if (node.left == null) {
				// 左节点为null，则向右移动
				node = node.right;
			} else if (node.right == null) {
				// 右节点为null，则向左移动
				node = node.left;
			} else if (less(node.left.value, node.right.value)) {
				// 左右均不为null，比较它们的value，并向小的一端移动
				node = node.left;
			} else {
				node = node.right;
			}
		}

		// 此时node是一个叶结点，其left和right均为null
		exch(node, beginMarker.left);
		
		// 由于exch交换的不是node并操作其三个指针，而是node的value 
		// 所以需要调整指针，使指向该node的指针为null
		if (node.prev.left == node) {
			node.prev.left = null;
		} else {
			node.prev.right = null;
		}
		node = null;
		
		// N = 0 时不需要sink，因为beginMarker.left为null
		if (--N != 0) {
			sink(beginMarker.left);
		}
		return maxValue;
	}

	// 下沉结点
	private void sink(TreeNode<T> node) {
		while (true) {
			// 左右均不为null时，选择沉向较大的一端
			if (node.left != null && node.right != null) {
				if (less(node.left.value, node.right.value)) {
					exch(node, node.right);
					node = node.right;
				} else {
					exch(node, node.left);
					node = node.left;
				}
			} else if (node.left == null && node.right == null) {
				// 当左右均为null时，此时node已经沉到底部，结束循环
				break;
			} else {
				// 结点的左右一端为null
				TreeNode<T> tmp = node.left == null ? node.right : node.left;
				// 比较此时node的value是否比不为null的结点value小
				if (less(node.value, tmp.value)) {
					// 如果小，则交换两个node值，并下沉
					exch(node, tmp);
					node = tmp;
				} else {
					// 如果不小，此时node已下沉至合适的位置，结束循环
					break;
				}
			}
		}
	}

	// 上浮该结点到合适的位置
	private void swim(TreeNode<T> node) {
		while (node.prev != beginMarker && less(node.prev.value, node.value)) {
			exch(node, node.prev);
			node = node.prev;
		}
	}

	// 交换两个node的value，而不是交换两个node
	private void exch(TreeNode<T> v, TreeNode<T> w) {
		T val = v.value;
		v.value = w.value;
		w.value = val;
	}

	private boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		
	}
}
