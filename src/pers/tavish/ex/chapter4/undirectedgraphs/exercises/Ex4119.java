package pers.tavish.ex.chapter4.exercises;

import edu.princeton.cs.algs4.In;
import pers.tavish.code.chapter4.undirectedgraphs.Cycle;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;

public class Ex4119 {

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0])); // 读取tinyGex2.txt构造图
		Cycle cycle = new Cycle(G);
		for (int i : cycle.cycle()) {
			System.out.print(i + " ");
		}
	}
}
