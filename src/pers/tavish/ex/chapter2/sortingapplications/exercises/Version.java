package pers.tavish.ex.chapter2.sortingapplications.exercises;

// 练习题 2.5.10
public class Version implements Comparable<Version> {
	private int main_ver;
	private int sec_ver;
	private int thr_ver;

	public Version(String version) {
		String[] strings = version.split("\\.");
		this.main_ver = Integer.parseInt(strings[0]);
		this.sec_ver = Integer.parseInt(strings[1]);
		this.thr_ver = Integer.parseInt(strings[2]);
	}

	@Override
	public String toString() {
		return main_ver + "." + sec_ver + "." + thr_ver;
	}

	@Override
	public int compareTo(Version o) {

		return this.main_ver != o.main_ver ? this.main_ver - o.main_ver
				: (this.sec_ver != o.sec_ver ? this.sec_ver - o.sec_ver
						: (this.thr_ver != o.thr_ver ? this.thr_ver - o.thr_ver : 0));
	}
}
