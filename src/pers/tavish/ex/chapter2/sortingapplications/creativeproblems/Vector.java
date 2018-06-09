package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

// 提高题2.5.21
public class Vector implements Comparable<Vector> {
	private int d; // 保存维数
	private int[] arr; // 保存每一维度的值

	public Vector(int d, int[] a) {
		if (d != a.length) {
			throw new IllegalArgumentException();
		}
		this.d = d;
		this.arr = new int[d];
		for (int i = 0; i < d; i++) {
			arr[i] = a[i];
		}
	}

	@Override
	public int compareTo(Vector o) {

		if (this.d != o.d) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < d; i++) {
			if (this.arr[i] > o.arr[i]) {
				return 1;
			} else if (this.arr[i] < o.arr[i]) {
				return -1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		Vector vector1 = new Vector(4, new int[] { 0, 1, 2, 4 });
		Vector vector2 = new Vector(4, new int[] { 0, 1, 2, 3 });
		System.out.println(vector1.compareTo(vector2));
	}
}
