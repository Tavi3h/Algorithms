package pers.tavish.ex.chapter1.dataabstraction.exercises;

import edu.princeton.cs.algs4.StdOut;

// 练习题1.2.11 & 1.2.12
public class SmartDate implements Comparable<SmartDate> {

	// 每个月的天数，DAYS[O]不使用
	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private final int month; // 月份，1-12之间
	private final int day; // 日期 1-DAYS[month]之间
	private final int year; // 年份

	// 使用三个int值初始化
	public SmartDate(int month, int day, int year) {
		if (!isValid(month, day, year))
			throw new IllegalArgumentException("Invalid date");
		this.month = month;
		this.day = day;
		this.year = year;
	}

	// 使用字符串格式初始化
	public SmartDate(String date) {
		String[] fields = date.split("/");
		if (fields.length != 3) {
			throw new IllegalArgumentException("Invalid date");
		}
		month = Integer.parseInt(fields[0]);
		day = Integer.parseInt(fields[1]);
		year = Integer.parseInt(fields[2]);
		if (!isValid(month, day, year))
			throw new IllegalArgumentException("Invalid date");
	}

	// 返回月份
	public int month() {
		return month;
	}

	// 返回日期
	public int day() {
		return day;
	}

	// 返回年份
	public int year() {
		return year;
	}

	// 判断日期是否合法
	private static boolean isValid(int m, int d, int y) {
		if (m < 1 || m > 12)
			return false;
		if (d < 1 || d > DAYS[m])
			return false;
		if (m == 2 && d == 29 && !isLeapYear(y))
			return false;
		return true;
	}

	// 判断是否为闰年
	private static boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		if (y % 100 == 0)
			return false;
		return y % 4 == 0;
	}

	// 返回当前日期的下一天
	public SmartDate next() {
		if (isValid(month, day + 1, year))
			return new SmartDate(month, day + 1, year);
		else if (isValid(month + 1, 1, year))
			return new SmartDate(month + 1, 1, year);
		else
			return new SmartDate(1, 1, year + 1);
	}

	// 判断当前日期是否在某个日期之后
	public boolean isAfter(SmartDate that) {
		return compareTo(that) > 0;
	}

	// 判断当前日期是否在某个日期之前
	public boolean isBefore(SmartDate that) {
		return compareTo(that) < 0;
	}

	// 计算某个日期是星期几的算法
	public String dayOfWeek() {

		int monthTmp = month;
		int yearTmp = year;
		
		if (month < 3) {
			monthTmp += 12;
			--yearTmp;
		}
		int week = (day + 1 + 2 * monthTmp + 3 * (monthTmp + 1) / 5 + yearTmp + (yearTmp >> 2) - yearTmp / 100 + yearTmp / 400) % 7;

		String weekstr = "";
		
		switch (week) {
		case 0:
			weekstr = "Sunday";
			break;
		case 1:
			weekstr = "Monday";
			break;
		case 2:
			weekstr = "Tuesday";
			break;
		case 3:
			weekstr = "Wednesday";
			break;
		case 4:
			weekstr = "Thursday";
			break;
		case 5:
			weekstr = "Friday";
			break;
		case 6:
			weekstr = "Saturday";
			break;
		}
		return weekstr;
	}

	// 按照日历比较两个日期
	@Override
	public int compareTo(SmartDate that) {
		if (this.year < that.year)
			return -1;
		if (this.year > that.year)
			return +1;
		if (this.month < that.month)
			return -1;
		if (this.month > that.month)
			return +1;
		if (this.day < that.day)
			return -1;
		if (this.day > that.day)
			return +1;
		return 0;
	}

	// 覆盖toString()方法
	@Override
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	// 覆盖equals方法，判断两个日期是否相等
	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (other.getClass() != this.getClass())
			return false;
		SmartDate that = (SmartDate) other;
		return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
	}

	// 覆盖hashCode()方法
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + month;
		hash = 31 * hash + day;
		hash = 31 * hash + year;
		return hash;
	}

	public static void main(String[] args) {
		StdOut.println(new SmartDate("4/4/2018").dayOfWeek());
	}
}
