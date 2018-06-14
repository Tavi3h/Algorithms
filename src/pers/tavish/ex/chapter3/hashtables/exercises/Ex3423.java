package pers.tavish.ex.chapter3.hashtables.exercises;

// 练习2.4.23
public class Ex3423 {
	public static void main(String[] args) {

		
		System.out.println(hash("abcde"));
		System.out.println(hash("abdce"));
		System.out.println(hash("badce"));
		System.out.println(hash("adbce"));
		System.out.println(hash("abecd"));
	}

	private static int hash(String s) {
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = ((256 * hash) + s.charAt(i)) % 255;
		}
		return hash;
	}
}
