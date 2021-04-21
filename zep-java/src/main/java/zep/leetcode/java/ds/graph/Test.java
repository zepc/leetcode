package zep.leetcode.java.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 2020/04/01.
 *
 * @author Zhao Peng
 */
public class Test {

	public static void main(String[] args) {
		HashMap<String, List<String>> graph = new HashMap<>();
		ArrayList<String> a1 = new ArrayList<>();
		Collections.addAll(a1, "B", "C", "D");
		graph.put("A", a1);

		ArrayList<String> a2 = new ArrayList<>();
		Collections.addAll(a2, "A", "E");
		graph.put("B", a2);

		ArrayList<String> a3 = new ArrayList<>();
		Collections.addAll(a3, "A", "F");
		graph.put("C", a3);

		ArrayList<String> a4 = new ArrayList<>();
		Collections.addAll(a4, "A", "G", "H");
		graph.put("D", a4);

		ArrayList<String> a5 = new ArrayList<>();
		Collections.addAll(a5, "B", "F");
		graph.put("E", a5);

		ArrayList<String> a6 = new ArrayList<>();
		Collections.addAll(a6, "E", "C");
		graph.put("F", a6);

		ArrayList<String> a7 = new ArrayList<>();
		Collections.addAll(a7, "D", "H", "I");
		graph.put("G", a7);

		ArrayList<String> a8 = new ArrayList<>();
		Collections.addAll(a8, "G");
		graph.put("H", a8);

		ArrayList<String> a9 = new ArrayList<>();
		Collections.addAll(a9, "G", "D");
		graph.put("I", a9);

		Traverse.dfsTraverse(graph);
	}
}
