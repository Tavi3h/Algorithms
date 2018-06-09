package pers.tavish.ex.chapter3.searchingapplications.creativeproblems;

import java.util.Iterator;

import pers.tavish.ex.chapter3.searchingapplications.exercises.HashSET;

// 提高题3.5.17
public class MathSET<Key> implements Iterable<Key> {

	private HashSET<Key> hashSET;

	private Key[] universe; // U，全集

	public MathSET(Key[] universe) {
		this.universe = universe;
		hashSET = new HashSET<>();
	}

	public void add(Key key) {
		hashSET.add(key);
	}

	/*
	 * 求补集
	 */
	public MathSET<Key> complement() {

		MathSET<Key> tmp = new MathSET<>(this.universe);

		for (Key key : universe) {
			if (!contains(key)) {
				tmp.add(key);
			}
		}

		return tmp;
	}

	/*
	 * 求并集
	 */
	public void union(MathSET<Key> a) {
		if (a == null) {
			throw new IllegalArgumentException("called union() with a null argument");
		}
		for (Key key : a) {
			add(key);
		}
	}

	/*
	 * 求交集
	 */
	public void intersection(MathSET<Key> a) {
		if (a == null) {
			throw new IllegalArgumentException("called intersects() with a null argument");
		}

		MathSET<Key> tmp = new MathSET<>(universe);

		if (this.size() < a.size()) {
			for (Key x : this) {
				if (a.contains(x)) {
					tmp.add(x);
				}
			}
		} else {
			for (Key x : a) {
				if (this.contains(x)) {
					tmp.add(x);
				}
			}
		}
		hashSET = tmp.hashSET;
	}

	public void delete(Key key) {
		hashSET.delete(key);
	}

	public boolean contains(Key key) {
		return hashSET.contains(key);
	}

	public int size() {
		return hashSET.size();
	}

	public boolean isEmpty() {
		return hashSET.isEmpty();
	}
	
	@Override
	public String toString() {
		
		if (size() == 0) {
			return "{}";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Key key : this) {
			sb.append(key + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "}");
		return sb.toString();
	}

	@Override
	public Iterator<Key> iterator() {
		return hashSET.iterator();
	}

	public static void main(String[] args) {
		Integer[] universe = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		MathSET<Integer> setA = new MathSET<>(universe);
		MathSET<Integer> setB = new MathSET<>(universe);
		
		setA.add(4);
		setA.add(6);
		setA.add(8);
		setA.add(10);
		
		setB.add(2);
		setB.add(8);
		setB.add(10);
		
		System.out.println(setA); // {8, 10, 4, 6}
		System.out.println(setB); // {8, 2, 10}
		
		setA.union(setB);
		System.out.println(setA); // {2, 4, 6, 8, 10}
		
		MathSET<Integer> setC = setA.complement();
		System.out.println(setC); // {1, 3, 5, 7, 9}
		
		setA.intersection(setB);
		System.out.println(setA); // {8, 2, 10}
		
		setA.delete(2);
		System.out.println(setA); // {8, 10}
 		
		setA.intersection(setC);
		System.out.println(setA); // {}
	}
}
