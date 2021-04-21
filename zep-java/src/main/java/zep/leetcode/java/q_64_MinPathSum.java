package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/18 21:42
 */
public class q_64_MinPathSum {

	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		if (m == 1 && n == 1) {
			return grid[0][0];
		}
		int[][] dp = new int[m + 1][n + 1]; // 当前子序列最小和
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				int sum = Math.min(dp[i - 1][j], dp[i][j - 1]);
				if (sum == Integer.MAX_VALUE) {
					sum = 0;
				}
				dp[i][j] = sum + grid[i - 1][j - 1];
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		int[][] grid = {{1, 2, 3}, {4, 5, 6}};
		q_64_MinPathSum test = new q_64_MinPathSum();
		System.out.println(test.minPathSum(grid));
	}

}
