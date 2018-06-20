package pers.tavish.code.chapter3.searchingapplications;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 文件索引
// SET：内部封装TreeSet
// ST：内部封装TreeMap
public class FileIndex {

	// Do not instantiate.
	private FileIndex() {
	}

	public static void main(String[] args) {

		// key = word, value = set of files containing that word
		ST<String, SET<File>> st = new ST<String, SET<File>>();

		// create inverted index of all files
		StdOut.println("Indexing files");
		for (String filename : args) {
			StdOut.println("  " + filename);
			File file = new File(filename);
			In in = new In(file);
			while (!in.isEmpty()) {
				String word = in.readString();
				if (!st.contains(word))
					st.put(word, new SET<File>());
				SET<File> set = st.get(word);
				set.add(file);
			}
		}

		// read queries from standard input, one per line
		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			if (st.contains(query)) {
				SET<File> set = st.get(query);
				for (File file : set) {
					StdOut.println("  " + file.getName());
				}
			}
		}
	}
}
