package pers.tavish.ex.chapter4.directedgraphs.exercises;

import java.util.Iterator;

import pers.tavish.code.chapter4.directedgraphs.Digraph;
import pers.tavish.code.chapter4.directedgraphs.Topological;

// 练习题4.2.9
public class Ex429 {
	
	// 判断order是否是G的拓扑排序
	public static boolean isTopOrder(Digraph G, Iterable<Integer> order) {

		// 获取图G的拓扑排序
		Iterable<Integer> topOrder = new Topological(G).order();

		// 比较topoOrder、order
		Iterator<Integer> topIter = topOrder.iterator();
		Iterator<Integer> orderIter = order.iterator();

		while (topIter.hasNext()) {
			if (!orderIter.hasNext()) {
				return false;
			}
			int v = topIter.next();
			int w = orderIter.next();
			if (v != w) {
				return false;
			}
		}

		return orderIter.hasNext() ? false : true;
	}
}
