package pers.tavish.test.generic.array;

import java.lang.reflect.Array;

class A {
	
}

public class GenericTest<T extends Comparable<? super T>> {

	@SuppressWarnings("unused")
	private T[] arr;

	@SuppressWarnings("unused")
	private T[] arr2;

	@SuppressWarnings("unchecked")
	public GenericTest(int N) {
		arr = (T[]) new Comparable[N];
	}

	@SuppressWarnings("unchecked")
	public GenericTest(Class<T> type, int length) {
		arr2 = (T[]) Array.newInstance(type, length);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		GenericTest<A> gt = new GenericTest<>(A.class, 10);  // generic error
		GenericTest<String> gt = new GenericTest<>(String.class, 10);
		GenericTest<String> gt2 = new GenericTest<>(10);
	}
}
