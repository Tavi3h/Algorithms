package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

// 提高题2.5.14
public class Domain implements Comparable<Domain> {

	private String domain;

	public Domain(String domain) {
		this.domain = domain;
	}

	@Override
	public int compareTo(Domain o) {

		return this.reverse().compareTo(o.reverse());
	}

	private String reverse() {

		String[] fields = domain.split("\\.");
		StringBuilder sb = new StringBuilder();

		for (int i = fields.length - 1; i >= 0; i--) {
			if (i != 0) {
				sb.append(fields[i] + ".");
			} else {
				sb.append(fields[i]);
			}
		}
		return sb.toString();
	}
}
