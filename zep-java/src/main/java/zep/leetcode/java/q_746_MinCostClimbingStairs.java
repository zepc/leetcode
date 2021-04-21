package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/18 00:14
 */
public class q_746_MinCostClimbingStairs {

	public int minCostClimbingStairs(int[] cost) {
		if (cost.length == 0 || cost.length == 1) {
			return 0;
		}
		int[] dp = new int[cost.length];
		dp[0] = cost[0];
		dp[1] = cost[1];
		for (int i = 2; i < cost.length; i++) {
			dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
		}
		return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
	}

	// 优化存储空间
	public int solution_01(int[] cost) {
		if (cost.length == 0 || cost.length == 1) {
			return 0;
		}
		int last = 0, curr = 0;
		for (int i = 2; i < cost.length + 1; i++) {
			if (last + cost[i - 1] > curr + cost[i - 2]) {
				curr = last;
				last = last + cost[i - 2];
			}
			else {
				curr = last;
				last = last + cost[i - 1];
			}
		}
		return last;
	}

	public static void main(String[] args) {
		q_746_MinCostClimbingStairs test = new q_746_MinCostClimbingStairs();
		int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		System.out.println(test.minCostClimbingStairs(cost));
	}
}
