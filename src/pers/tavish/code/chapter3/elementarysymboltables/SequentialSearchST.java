package pers.tavish.code.chapter3.elementarysymboltables;

import edu.princeton.cs.algs4.Queue;

// 基于无序单链表实现的顺序查找ST，Key没有实现Comparable
// 低效的实现
public class SequentialSearchST<Key, Value> {

	private Node<Key, Value> first; // 链表首结点

	private int N;

	private static class Node<Key, Value> {
		Key key;
		Value val;
		Node<Key, Value> next;

		public Node(Key key, Value val, Node<Key, Value> next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public int size() {
		return N;
	}
	
    public boolean isEmpty() {
        return size() == 0;
    }

	// 查找指定的键，返回相关联的值
	public Value get(Key key) {
		
        if (key == null) {
        	throw new IllegalArgumentException("argument to get() is null"); 
        }

		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}

	/*
	 *  查找给定的键，找到则更新其值，否则在表中新建结点插入表头
	 */
	public void put(Key key, Value val) {
		
        if (key == null) {
        	throw new IllegalArgumentException("first argument to put() is null"); 
        }
        
        if (val == null) {
            delete(key);
            return;
        }
		
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val; // 命中，更新
				return;
			}
		}
		first = new Node<>(key, val, first); // 未命中，新建结点
		N++;
	}
	
	/*
	 * 检测链表是否包含某个key
	 */
    public boolean contains(Key key) {
        if (key == null) {
        	throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /*
     * 根据键删除键值对
     */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		first = delete(first, key);
	}

	private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
		if (x == null) {
			return null;
		}
		if (key.equals(x.key)) {
			N--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	/*
	 * 返回第k个key
	 */
	public Key select(int k) {

		if (k < 0 || k > N - 1) {
			throw new IllegalArgumentException();
		}

		Node<Key, Value> x = first;
		for (int i = k; i > 0; i--) {
			x = x.next;
		}

		return x.key;
	}

	/*
	 * 以一个Iterable集合的方式返回所有的key
	 */
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node<Key, Value> x = first; x != null; x = x.next) {
			sb.append("[" + x.key + ", " + x.val + "] ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

	}
}
