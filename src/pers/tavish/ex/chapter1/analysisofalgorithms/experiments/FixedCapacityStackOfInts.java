package pers.tavish.ex.chapter1.analysisofalgorithms.experiments;

// 实验题 1.4.37
public class FixedCapacityStackOfInts {
	
	private int[] a;  
	private int N;  

	public FixedCapacityStackOfInts(int cap) {
		this.a = new int[cap];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(int item) {
		a[N++] = item;
	}

	public int pop() {
		return a[--N];
	}
}
