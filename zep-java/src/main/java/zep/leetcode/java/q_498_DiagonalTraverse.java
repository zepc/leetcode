package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/20 16:33
 */
public class q_498_DiagonalTraverse {

	public int[] findDiagonalOrder(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		int[] ans = new int[row * col];
		if (row == 1) {
			return mat[0];
		}
		if (col == 1) {
			for (int i = 0; i < row; i++) {
				ans[i] = mat[i][0];
			}
			return ans;
		}
		int i = 0, j = 0, k = 0;
		boolean around = false;
		while (i < row && j < col) {
			ans[k++] = mat[i][j];
			if (i - 1 < 0 || j == col - 1) {
				if (j == col - 1) {
					ans[k++] = mat[++i][j];
				}
				else {
					ans[k++] = mat[i][++j];
				}
				around = true;
			}
			if (i == row - 1 && j == col - 1) {
				break;
			}
			if (j - 1 < 0 || i == row - 1) {
				if (i == row - 1) {
					ans[k++] = mat[i][++j];
				}
				else {
					ans[k++] = mat[++i][j];
				}
				around = false;
			}
			if (around) {
				i++;
				j--;
			}
			else {
				i--;
				j++;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		q_498_DiagonalTraverse test = new q_498_DiagonalTraverse();
//		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] matrix = {{2, 5}, {8, 4}, {0, -1}};
		int[] ans = test.findDiagonalOrder(matrix);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

}
