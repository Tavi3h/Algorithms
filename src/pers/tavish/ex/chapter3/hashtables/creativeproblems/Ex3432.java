package pers.tavish.ex.chapter3.hashtables.creativeproblems;

public class Ex3432 {
	
	public static void main(String[] args) {
		String s1 = "aoffckzdaoffckzdhfibxuiu";
		String s2 = "aoffckzdaoffckzdhkdcmtby";
		String s3 = "aoffckzdaoffckzdmfdwlmfy";
		System.out.print(hash(s1) + " ");
		System.out.print(hash(s2) + " ");
		System.out.print(hash(s3) + " ");
	}

	public static int hash(String s) {
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = (hash * 31) + s.charAt(i);
		}
		return hash;
	}
}
