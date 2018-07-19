package pers.tavish.code.chapter5.substringsearch;

public class BruteSearch {

	public static int search_1(String pat, String txt) {

		int M = pat.length();
		int N = txt.length();

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (txt.charAt(i + j) != pat.charAt(j)) {
					break;
				}
			}
			if (j == M) {
				return i; // 找到匹配
			}
		}
		return N; // 未找到匹配
	}

	public static int search_2(String pat, String txt) {
		
		int j, M = pat.length();
		int i, N = txt.length();
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (txt.charAt(i) == pat.charAt(j)) {
				j++;
			} else {
				i -= j;
				j = 0;
			}
		}
		return j == M ? i - M : N; // true -> 找到匹配，false -> 未找到匹配
	}

}
