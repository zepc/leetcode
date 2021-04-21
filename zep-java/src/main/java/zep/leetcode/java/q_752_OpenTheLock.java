package zep.leetcode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2020/08/02.
 *
 * @author Zhao Peng
 */
public class q_752_OpenTheLock {

	public int openLock(String[] deadends, String target) {

		HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
		HashSet<String> visited = new HashSet<>();

		dfs(target, getNeighbors(target, dead), dead);

		return count;
	}

	private int count = 0;

	private void dfs(String target, List<String> neighbors, Set<String> dead) {
		if (neighbors.contains(target)) {
			return;
		}
		for (String s : neighbors) {
			List<String> inbors = getNeighbors(s, dead);
			count++;
			System.out.println(count);
			dfs(s, inbors, dead);
		}
	}

	private List<String> getNeighbors(String target, Set<String> dead) {
		List<String> list = new ArrayList<>();
		char[] chars = target.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			int number = Integer.valueOf(ch + "");
			// add
			int n = number + 1;
			if (n == 10) {
				n = 0;
			}
			chars[i] = ((char) (n + 48));
			String s_add = new String(chars);
			if (!dead.contains(s_add)) {
				list.add(s_add);
			}
			// sub
			int m = number - 1;
			if (m == -1) {
				m = 9;
			}
			chars[i] = ((char) (m + 48));
			String s_sub = new String(chars);
			if (!dead.contains(s_add)) {
				list.add(s_sub);
			}

			chars[i] = ch;
		}
		return list;
	}


	public static void main(String[] args) {

		q_752_OpenTheLock demo = new q_752_OpenTheLock();
		// deadends = ["0201","0101","0102","1212","2002"], target = "0202"
		String[] deads = {"0201", "0101", "0102", "1212", "2002"};
		System.out.println(demo.openLock(deads, "2020"));

	}
}
