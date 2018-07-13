package pers.tavish.code.chapter5.stringsorts;

import edu.princeton.cs.algs4.StdIn;

public class Count {
	
	public static void main(String[] args) {
		
		Alphabet alphabet = new Alphabet(args[0]);
		int R = alphabet.radix();
		int[] count = new int[R];

		String s = StdIn.readAll();
		int N = s.length();
		for (int i = 0; i < N; i++) {
			if (alphabet.contains(s.charAt(i))) {
				count[alphabet.toIndex(s.charAt(i))]++;
			}
		}

		for (int c = 0; c < R; c++) {
			System.out.println(alphabet.toChar(c) + " " + count[c]);
		}
	}
}
