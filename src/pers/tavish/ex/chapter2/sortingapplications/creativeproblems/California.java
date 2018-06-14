package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import java.util.Arrays;
import java.util.Comparator;

public class California {

	public static void main(String[] args) {

		String[] a = { "WILL", "TAVISH", "TACO", "HARRY", "AZUNA", "IMOTHPEN", "RUTH" };

		Arrays.sort(a, new Comparator<String>() {

			private final String table = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

			// 取首字母进行比较，如果相同，则比较全名；如果不相同则查表比较。
			@Override
			public int compare(String o1, String o2) {

				String name1 = o1.substring(0, 1);
				String name2 = o2.substring(0, 1);

				return name1.equals(name2) ? o1.compareTo(o2) : table.indexOf(name1) - table.indexOf(name2);
			}

		});

		for (String string : a) {
			System.out.println(string);
		}
	}
}
