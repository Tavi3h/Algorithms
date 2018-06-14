package pers.tavish.ex.chapter1.analysisofalgorithms.experiments;

// 实验题 1.4.37
public class FixedCapacityStack<T> {
	
	private T[] a;
	private int N;

	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int cap) {
		a = (T[]) new Object[cap];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(T item) {
		a[N++] = item;
	}

	public T pop() {
		return a[--N];
	}
}
