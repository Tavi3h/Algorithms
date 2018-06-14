package pers.tavish.ex.chapter3.searchingapplications.exercises;

import edu.princeton.cs.algs4.Bag;
import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 练习题3.5.14
public class Invert {

	public static void main(String[] args) {
		Bag<String> bag1 = new Bag<>();
		bag1.add("A");
		bag1.add("B");
		bag1.add("C");
		
		Bag<String> bag2 = new Bag<>();
		bag2.add("X");
		bag2.add("Y");
		bag2.add("Z");
		
		Bag<String> bag3 = new Bag<>();
		bag3.add("B");
		bag3.add("C");
		bag3.add("X");
		bag3.add("Y");
		
		RedBlackBST<String, Bag<String>> rbbst = new RedBlackBST<>();
		rbbst.put("1", bag1);
		rbbst.put("2", bag2);
		rbbst.put("3", bag3);
		
		rbbst = invert(rbbst);
		
		for (String key : rbbst.keys()) {
			System.out.print(key + ": ");
			for (String ele : rbbst.get(key)) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}

	private static RedBlackBST<String, Bag<String>> invert(RedBlackBST<String, Bag<String>> that) {

		RedBlackBST<String, Bag<String>> rbbst = new RedBlackBST<>();

		for (String key : that.keys()) { // 遍历that的所有键
			for (String element : that.get(key)) { // 遍历这个bag中的所有元素
				if (!rbbst.contains(element) ) { // 如果rbbst中不包含该元素
					rbbst.put(element, new Bag<>()); // 存入键值对
				}
				Bag<String> tmp = rbbst.get(element); // 取出对应的bag
				tmp.add(key); // bag存入key
			}
		}
		return rbbst;
	}
}
