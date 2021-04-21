package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/18 22:14
 */
public class q_91_DecodeWays {

	public int numDecodings(String s) {
		if (s.length() == 0) {
			return 0;
		}
		if (s.charAt(0) == '0') {
			return 0;
		}
		if (s.length() == 1 && s.charAt(0) <= '9') {
			return 1;
		}
		int i = numDecodings(s.substring(0, s.length() - 1));
		if (s.charAt(s.length() - 1) == '0') {
			return 1;
		}
		if (s.charAt(s.length() - 1) <= '6' && s.charAt(s.length() - 1) > '0') {
			if (s.charAt(s.length() - 2) <= '2' && s.charAt(s.length() - 2) > '0') {
				i++;
			}
		}
		return i;
	}

	public int solution(String s) {
		if (s.length() == 0) {
			return 0;
		}
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		if (s.charAt(0) == '0') {
			dp[1] = 0;
		}
		else {
			dp[1] = 1;
		}
		for (int i = 2; i <= s.length(); i++) {
			int number = Integer.parseInt(s.substring(i - 1, i));
			if (number >= 1 && number <= 9) {
				dp[i] += dp[i - 1];
			}
			number = Integer.parseInt(s.substring(i - 2, i));
			if (number >= 10 && number <= 26) {
				dp[i] += dp[i - 2];
			}
		}
		return dp[s.length()];
	}

	public static void main(String[] args) {
		q_91_DecodeWays test = new q_91_DecodeWays();
		System.out.println(test.solution("2101"));
	}

}
