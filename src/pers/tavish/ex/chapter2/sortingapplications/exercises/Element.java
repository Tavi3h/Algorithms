package pers.tavish.ex.chapter2.sortingapplications.exercises;

import edu.princeton.cs.algs4.Quick;

// 练习题2.5.11
public class Element implements Comparable<Element> {
	private String main_key;
	private int vice_key;

	public Element(String m, int v) {
		this.main_key = m;
		this.vice_key = v;
	}

	// 按照主键进行排序
	@Override
	public int compareTo(Element o) {
		return this.main_key.compareTo(o.main_key);
	}

	@Override
	public String toString() {
		return "[" + main_key + ", " + vice_key + "]";
	}

	public static void main(String[] args) {
		Element[] elements = { new Element("A", 0), new Element("A", 1), new Element("A", 2), new Element("A", 3),
				new Element("A", 4), new Element("A", 5), new Element("A", 6) };
		
		Quick.sort(elements);
		
		for (int i = 0; i < elements.length; i++) {
			System.out.println(elements[i]);
		}
	}
}
