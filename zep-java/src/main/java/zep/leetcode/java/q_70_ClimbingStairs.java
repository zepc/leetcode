package zep.leetcode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 23:15
 */
public class q_70_ClimbingStairs {

	Map<Integer, Integer> cache = new HashMap<>();

	public int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		int result = climbStairs(n - 1) + climbStairs(n - 2);
		cache.put(n, result);
		return result;
	}


	public int solution_02(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i < n + 1; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public static void main(String[] args) {
		q_70_ClimbingStairs test = new q_70_ClimbingStairs();
		System.out.println(test.solution_02(4));
	}
}
