package pers.tavish.code.chapter5.stringsorts;

import edu.princeton.cs.algs4.StdOut;

// 字母表API
public class Alphabet {

	/*
	 * The binary alphabet { 0, 1 }.
	 */
	public static final Alphabet BINARY = new Alphabet("01");

	/*
	 * The DNA alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
	 */
	public static final Alphabet DNA = new Alphabet("ACGT");

	/*
	 * The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
	 */
	public static final Alphabet OCTAL = new Alphabet("01234567");

	/*
	 * The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
	 */
	public static final Alphabet DECIMAL = new Alphabet("0123456789");

	/*
	 * The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
	 */
	public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

	/*
	 * The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V,
	 * W, Y }.
	 */
	public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

	/*
	 * The lowercase alphabet { a, b, c, ..., z }.
	 */
	public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

	/*
	 * The uppercase alphabet { A, B, C, ..., Z }.
	 */
	public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

	/*
	 * The base-64 alphabet (64 characters).
	 */
	public static final Alphabet BASE64 = new Alphabet(
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

	/*
	 * The ASCII alphabet (0-127).
	 */
	public static final Alphabet ASCII = new Alphabet(128);

	/*
	 * The extended ASCII alphabet (0-255).
	 */
	public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

	/*
	 * The Unicode 16 alphabet (0-65,535).
	 */
	public static final Alphabet UNICODE16 = new Alphabet(65536);

	private char[] alphabet; // the characters in the alphabet
	private int[] inverse; // indices
	private final int R; // the radix of the alphabet

	/*
	 * 使用字符串构造一个字母表
	 */
	public Alphabet(String alpha) {

		// 检查字母表是否包含重复的字符
		boolean[] unicode = new boolean[Character.MAX_VALUE];
		for (int i = 0; i < alpha.length(); i++) {
			char c = alpha.charAt(i);
			if (unicode[c]) {
				throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
			}
			unicode[c] = true;
		}

		alphabet = alpha.toCharArray();
		R = alpha.length();
		inverse = new int[Character.MAX_VALUE];
		for (int i = 0; i < inverse.length; i++) {
			inverse[i] = -1;
		}

		for (int c = 0; c < R; c++) {
			inverse[alphabet[c]] = c;
		}
	}

	/*
	 * 创建一个使用字符0到255的字母表
	 */
	public Alphabet() {
		this(256);
	}

	// 创建一个使用字符0到R-1的字母表
	private Alphabet(int radix) {

		this.R = radix;
		alphabet = new char[R];
		inverse = new int[R];

		for (int i = 0; i < R; i++) {
			alphabet[i] = (char) i;
		}

		for (int i = 0; i < R; i++) {
			inverse[i] = i;
		}
	}

	/*
	 * 判断字符c是否在字母表中
	 */
	public boolean contains(char c) {
		return inverse[c] != -1;
	}

	/*
	 * 返回字母表中字符的数量
	 */
	public int radix() {
		return R;
	}

	/*
	 * 返回表示一个索引所需的比特数
	 */
	public int lgR() {
		int lgR = 0;
		for (int t = R - 1; t >= 1; t /= 2) {
			lgR++;
		}
		return lgR;
	}

	/*
	 * 返回字符c的角标
	 */
	public int toIndex(char c) {
		if (c >= inverse.length || inverse[c] == -1) {
			throw new IllegalArgumentException("Character " + c + " not in alphabet");
		}
		return inverse[c];
	}

	/*
	 * 将s转换为R进制的整数
	 */
	public int[] toIndices(String s) {
		char[] source = s.toCharArray();
		int[] target = new int[s.length()];
		for (int i = 0; i < source.length; i++) {
			target[i] = toIndex(source[i]);
		}
		return target;
	}

	/*
	 * 将R进制的整数转换为基于该字母表的字符串
	 */
	public String toChars(int[] indices) {
		StringBuilder s = new StringBuilder(indices.length);
		for (int i = 0; i < indices.length; i++)
			s.append(toChar(indices[i]));
		return s.toString();
	}

	/*
	 * 返回字母表中与索引对应的字符
	 */
	public char toChar(int index) {
		if (index < 0 || index >= R) {
			throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
		}
		return alphabet[index];
	}
	
    public static void main(String[] args) {
        int[]  encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);
 
        int[]  encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[]  encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
        
        for (char c : Alphabet.PROTEIN.alphabet) {
			System.out.print(c + " ");
		}
        System.out.println();
        for (int i : Alphabet.PROTEIN.inverse) {
        	if (i != -1) {
        		System.out.print(i + " ");
        	}
        }
        System.out.println();
        System.out.println(Alphabet.PROTEIN.contains('A'));
        System.out.println(Alphabet.PROTEIN.toChar(1));
    }
}
