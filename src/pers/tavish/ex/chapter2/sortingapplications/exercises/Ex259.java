package pers.tavish.ex.chapter2.sortingapplications.exercises;

import java.util.Set;
import java.util.TreeSet;

// 练习题2.5.9
class DJIATranscation implements Comparable<DJIATranscation> {
	private Integer data_mem1;
	private String data_mem2;
	private String data_mem3;
	private Integer trans_amount;

	public DJIATranscation(String trans) {
		String[] s = trans.split("-");
		this.data_mem1 = Integer.parseInt(s[0]);
		this.data_mem2 = s[1];
		this.data_mem3 = s[2];
		this.trans_amount = Integer.parseInt(s[3]);
	}

	@Override
	public String toString() {
		return data_mem1 + "-" + data_mem2 + "-" + data_mem3 + "\t" + trans_amount;
	}

	@Override
	public int compareTo(DJIATranscation o) {
		return this.trans_amount != o.trans_amount ? this.trans_amount.compareTo(o.trans_amount)
				: this.data_mem1.compareTo(o.data_mem1);
	}
}

public class Ex259 {
	public static void main(String[] args) {
		String[] arr = { "1-Oct-28-3500000", "2-Oct-28-3850000", "3-Oct-28-4060000", "4-Oct-28-4330000",
				"5-Oct-28-4360000", "30-Dec-99-554680000", "31-Dec-99-374049984", "3-Jan-00-931800000",
				"4-Jan-00-1009000000", "5-Jan-00-1085500000" };

		Set<DJIATranscation> set = new TreeSet<>();

		for (String s : arr) {
			set.add(new DJIATranscation(s));
		}
		
		for (DJIATranscation s : set) {
			System.out.println(s);
		}
	}
}
