package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/18 21:12
 */
public class q_63_UniquePaths2 {

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if (m == 1 && n == 1 && obstacleGrid[0][0] == 0) {
			return 1;
		}
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
		int[][] dp = new int[m][n];
		dp[0][0] = 1;
		for (int i = 1; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				break;
			}
			else {
				dp[i][0] = 1;
			}
		}
		for (int i = 1; i < n; i++) {
			if (obstacleGrid[0][i] == 1) {
				break;
			}
			else {
				dp[0][i] = 1;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] != 1) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	public static void main(String[] args) {
		q_63_UniquePaths2 test = new q_63_UniquePaths2();
		int[][] a = {{0, 0}, {1, 1}, {0, 0}};
		System.out.println(test.uniquePathsWithObstacles(a));
	}
}
