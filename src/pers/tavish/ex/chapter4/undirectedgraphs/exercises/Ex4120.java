package pers.tavish.ex.chapter4.undirectedgraphs.exercises;

import edu.princeton.cs.algs4.In;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;
import pers.tavish.code.chapter4.undirectedgraphs.TwoColor;

// 练习题4.1.20
public class Ex4120 {
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0])); // 读取tinyGex2.txt构造图
		TwoColor tw = new TwoColor(G);
		for (boolean bool : tw.color()) {
			System.out.print(bool + " ");
		}
	}
}
