package pers.tavish.ex.chapter3.binarysearchtrees.creativeproblems;

import java.util.Set;
import java.util.TreeSet;

// 对提高题3.2.33 isBST()方法进行测试
public class TestisBST<Key extends Comparable<? super Key>, Value> {

	// Node类
	private static class Node<Key, Value> {
		private Key key; // 键
		@SuppressWarnings("unused")
		private Value val; // 值
		private Node<Key, Value> left, right; // 指向子树的链接
		private int N; // 以该结点为根的子树中的结点总数

		public Node(Key key, Value val, int n) {
			this.key = key;
			this.val = val;
			N = n;
		}
	}
	
	/****************************************************
	 ******************** check *************************
	 ****************************************************/

	// 二叉树检查
	private boolean isBinaryTree(Node<Key, Value> x) {
		if (x == null) {
			throw new IllegalArgumentException("Node x can not be null...");
		}

		if (x.right == null && x.left == null) { // 如果x没有左右结点
			return x.N == 1;
		} else if (x.right != null && x.left != null) { // 如果x同时具有左右结点，则结点数量为1+左子树结点数+右子数结点数
			return x.N != x.left.N + x.right.N + 1 ? false : isBinaryTree(x.left) && isBinaryTree(x.right);
		} else { // x结点只有一个子树
			Node<Key, Value> t = x.left == null ? x.right : x.left;
			return x.N != t.N + 1 ? false : isBinaryTree(t);
		}
	}

	// 有序性检查
	private boolean isOrdered(Node<Key, Value> x, Key min, Key max) {
		
		if (x == null) {
			throw new IllegalArgumentException("Node x can not be null...");
		}
		if (min.compareTo(max) > 0) {
			throw new IllegalArgumentException("min can not be greater than max...");
		}
		
		// 结点x的key不在[min, max]范围，直接返回false
		if (min.compareTo(x.key) > 0 || max.compareTo(x.key) < 0) {
			return false;
		}
		
		if (x.left == null && x.right == null) { // 如果x的左右结点均为null
			return true;
		} else if (x.left != null && x.right != null) { // 如果x的左右结点均不为null
			if (x.left.key.compareTo(x.key) > 0 || x.right.key.compareTo(x.key) < 0) { // 有序性检查
				return false;
			}
			return isOrdered(x.left, min, max) && isOrdered(x.right, min, max);
		} else { // x的左右结点其中之一为null
			if (x.left == null) { // 如果左结点为空
				if (x.right.key.compareTo(x.key) < 0) { // 如果右结点key小于x.key
					return false;
				}
				x = x.right;
			} else { // 如果右结点为空
				if (x.left.key.compareTo(x.key) > 0) { // 如果右结点key大于x.key
					return false;
				}
				x = x.left;
			}
			return isOrdered(x, min, max);
		}
	}

	// 等值键检查
	private boolean hasNoDuplicates(Node<Key, Value> x) {

		if (x == null) {
			throw new IllegalArgumentException("Node x can not be null...");
		}

		return hasNoDuplicates(x, new TreeSet<Key>());
	}

	private boolean hasNoDuplicates(Node<Key, Value> x, Set<Key> set) {

		// 判断set中是否存在x.key
		if (set.contains(x.key)) {
			return false; // 如果存在，说明有重复键
		}
		set.add(x.key); // 如果不存在，将x.key添加进set

		if (x.left == null && x.right == null) { // 如果x结点没有左右结点，返回true
			return true;
		} else if (x.left != null && x.right != null) { // 如果x结点具有左右两个子结点
			return hasNoDuplicates(x.left, set) && hasNoDuplicates(x.right, set);
		} else {
			// 结点x只有一个子结点
			x = x.left == null ? x.right : x.left;
			return hasNoDuplicates(x, set);
		}
	}

	// 验证
	private boolean isBST(Node<Key, Value> x, Key min, Key max) {
		if (!isBinaryTree(x)) {
			System.out.println("二叉树检查未通过...");
			return false;
		}
		if (!isOrdered(x, min, max)) {
			System.out.println("有序性检查未通过...");
			return false;
		}
		if (!hasNoDuplicates(x)) {
			System.out.println("等值键检查未通过...");
			return false;
		}
		System.out.println("测试通过...");
		return true;
	}

	public static void main(String[] args) {

		/****************************************************
		 ******************** 测 试 ***************************
		 ****************************************************/

		/*
		 * 创建结点
		 */
		Node<Integer, Character> root = new Node<>(20, '*', 10);
		Node<Integer, Character> nodeA = new Node<>(10, 'A', 5);
		Node<Integer, Character> nodeB = new Node<>(30, 'B', 4);
		Node<Integer, Character> nodeC = new Node<>(7, 'C', 1);
		Node<Integer, Character> nodeD = new Node<>(15, 'D', 3);
		Node<Integer, Character> nodeE = new Node<>(25, 'E', 1);
		Node<Integer, Character> nodeF = new Node<>(40, 'F', 2);
		Node<Integer, Character> nodeG = new Node<>(12, 'G', 1);
		Node<Integer, Character> nodeH = new Node<>(17, 'H', 1);
		Node<Integer, Character> nodeI = new Node<>(45, 'I', 1);

		/*
		 * 创建链接
		 */
		root.left = nodeA;
		root.right = nodeB;

		nodeA.left = nodeC;
		nodeA.right = nodeD;

		nodeB.left = nodeE;
		nodeB.right = nodeF;

		nodeD.left = nodeG;
		nodeD.right = nodeH;

		nodeF.right = nodeI;
		

		/*
		 * 调整结点root~nodeI的key和计数值来进行测试
		 */
		
		new TestisBST<Integer, Character>().isBST(root, 0, 40);
	}
}
