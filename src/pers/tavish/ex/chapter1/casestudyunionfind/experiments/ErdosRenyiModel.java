package pers.tavish.ex.chapter1.casestudyunionfind.experiments;

import pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems.ErdosRenyi;

// 实验题 1.5.21
public class ErdosRenyiModel {
	public static void main(String[] args) {
		for (int i = 100; i < 20000; i *= 2) {
			ErdosRenyi.count(i);
		}
	}
}
