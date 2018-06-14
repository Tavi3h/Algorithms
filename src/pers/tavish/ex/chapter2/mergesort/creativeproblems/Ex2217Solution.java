package pers.tavish.ex.chapter2.mergesort.creativeproblems;

// 提高题2.2.17 归并排序
class ListNode<T> {
	T value;
	ListNode<T> next;

	ListNode(T value) {
		this.value = value;
		next = null;
	}
}

public class Ex2217Solution {

	// 查找链表的中间结点
	public <T> ListNode<T> getMiddle(ListNode<T> head) {
		if (head == null) {
			return head;
		}
		// 使用快慢指针
		ListNode<T> slow, fast;
		slow = fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// 单链表归并排序
	public <T extends Comparable<? super T>> ListNode<T> sort(ListNode<T> head) {
		if (head == null || head.next == null) {
			return head;
		}
		// 得到链表中间的数
		ListNode<T> middle = getMiddle(head);
		ListNode<T> sHalf = middle.next;
		// 拆分链表
		middle.next = null;
		// 递归调用
		return merge(sort(head), sort(sHalf));
	}

	// 合并两个有序链表
	public <T extends Comparable<? super T>> ListNode<T> merge(ListNode<T> a, ListNode<T> b) {
		ListNode<T> dummyHead, curr;
		dummyHead = new ListNode<>(null);
		curr = dummyHead;
		while (a != null && b != null) {
			if (a.value.compareTo(b.value) <= 0) {
				curr.next = a;
				a = a.next;
			} else {
				curr.next = b;
				b = b.next;
			}
			curr = curr.next;
		}
		curr.next = (a == null ? b : a);
		return dummyHead.next;
	}

	public static void main(String[] args) {

		ListNode<Integer> head = new ListNode<>(0);
		ListNode<Integer> node1 = new ListNode<>(5);
		ListNode<Integer> node2 = new ListNode<>(1);
		ListNode<Integer> node3 = new ListNode<>(2);
		ListNode<Integer> node4 = new ListNode<>(4);
		ListNode<Integer> node5 = new ListNode<>(7);
		ListNode<Integer> node6 = new ListNode<>(6);
		ListNode<Integer> node7 = new ListNode<>(3);

		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = null;
		
		new Ex2217Solution().sort(head);

		ListNode<Integer> p = head;
		while (p != null) {
			System.out.print(p.value);
			p = p.next;
		}
	}
}