package pers.tavish.ex.chapter3.binarysearchtrees.experiments;


// 实验题3.2.41，使用三个数组表示一棵树
// 一个数组记录键值对，一个数组记录左链接，一个数组记录右链接
public class ThreeArraysBST<Key extends Comparable<? super Key>, Value> {
	
	private Tuple<Key, Value>[] tuples;
	private Integer[] lefts;
	private Integer[] rights;

	private int N; // 记录键的数量
	
	// 定义一个元组来保存键值对
	private static class Tuple<Key, Value> {
		private Key key;
		private Value val;
		
		public Tuple(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
		
		@Override
		public String toString() {
			return "[" + key + ", " + val + "]";
		}
	}
	
	@SuppressWarnings("unchecked")
	public ThreeArraysBST(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Illegal capacity...");
		}
		tuples = new Tuple[capacity];
		lefts = new Integer[capacity];
		rights = new Integer[capacity];
		N = 0;
	}

	public void put(Key key, Value val) {

		if (N == tuples.length) {
			throw new UnsupportedOperationException("BST Overflow...");
		}

		if (N == 0) {
			tuples[N++] = new Tuple<>(key, val);
			return;
		}

		int i = 0;
		while (true) {
			int cmp = key.compareTo(tuples[i].key);
			if (cmp < 0) {
				if (lefts[i] == null) {
					lefts[i] = N;
					break;
				}
				i = lefts[i];
			} else if (cmp > 0) {
				if (rights[i] == null) {
					rights[i] = N;
					break;
				}
				i = rights[i];
			} else {
				tuples[i].val = val;
				return;
			}
		}
		tuples[N++] = new Tuple<>(key, val);
	}
	
	public Value get(Key key) {
		// 遍历keys数组
		for (int i = 0; i < N; i++) {
			if (key.compareTo(tuples[i].key) == 0) {
				return tuples[i].val;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		ThreeArraysBST<Integer, Character> tabst = new ThreeArraysBST<>(10);
		tabst.put(5, 'A');
		tabst.put(3, 'B');
		tabst.put(6, 'C');
		tabst.put(1, 'D');
		tabst.put(9, 'E');
		tabst.put(7, 'F');
		tabst.put(4, 'G');
		tabst.put(3, 'Z');
		
		for (Tuple<Integer, Character> i : tabst.tuples) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (Integer i : tabst.lefts) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (Integer i : tabst.rights) {
			System.out.print(i + " ");
		}
	}
}
