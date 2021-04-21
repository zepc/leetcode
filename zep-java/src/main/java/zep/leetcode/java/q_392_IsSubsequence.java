package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 21:04
 */
public class q_392_IsSubsequence {

	public boolean isSubsequence(String s, String t) {
		if (s.isEmpty()) {
			return true;
		}
		char c = s.charAt(0);
		for (int i = 0; i < t.length(); i++) {
			if (c == t.charAt(i)) {
				return isSubsequence(s.substring(1), t.substring(i + 1));
			}
		}
		return false;
	}

	public boolean solution_02(String s, String t) {
		if (s.isEmpty()) {
			return true;
		}
		if (s.equals(t)) {
			return true;
		}
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[s.length()][t.length()] == s.length();
	}

	public boolean solution_03(String s, String t) {
		if (s.isEmpty()) {
			return true;
		}
		if (s.equals(t)) {
			return true;
		}
		while (s.length() > 0 && t.length() > 0) {
			if (s.charAt(0) == t.charAt(0)) {
				s = s.substring(1);
			}
			t = t.substring(1); // 每次都裁剪s，消耗时间
		}
		return s.length() == 0;
	}

	public boolean solution_04(String s, String t) {
		if (s.isEmpty()) {
			return true;
		}
		if (s.equals(t)) {
			return true;
		}
		while (s.length() > 0 && t.length() > 0) {
			for (int i = 0; i < t.length(); i++) {
				if (s.charAt(0) == t.charAt(i)) {
					s = s.substring(1);
					t = t.substring(i + 1);
					break;
				}
				if (i == t.length() - 1) {
					return false;
				}
			}
		}
		return s.length() == 0;
	}

	public static void main(String[] args) {
		q_392_IsSubsequence test = new q_392_IsSubsequence();
		System.out.println(test.solution_04("axc", "ahbgdc"));
	}
}
