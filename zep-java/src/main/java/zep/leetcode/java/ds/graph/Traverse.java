package zep.leetcode.java.ds.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2020/04/01.
 *
 * @author Zhao Peng
 */
public class Traverse {

	/**
	 * 深度优先遍历
	 *
	 * @param graph
	 */
	public static void dfsTraverse(Map<String, List<String>> graph) {
		Map<String, Boolean> visited = new HashMap<>();
		for (String k : graph.keySet()) {
			if (visited.get(k) == null) {
				dfs(graph, visited, k);
			}
		}
	}

	private static void dfs(Map<String, List<String>> graph,
	                        Map<String, Boolean> visited,
	                        String node) {
		System.out.println(node);
		visited.put(node, Boolean.TRUE);
		for (String k : graph.get(node)) {
			if (visited.get(k) == null) {
				dfs(graph, visited, k);
			}
		}
	}
}
