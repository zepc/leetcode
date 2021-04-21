package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/19 22:03
 */
public class q_48_RotateImage {

	public void rotate(int[][] matrix) {
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j <= Math.min(i, matrix.length - i - 2); j++) {
				// 1
				matrix[i][j] = matrix[i][j] ^ matrix[j][matrix.length - i - 1];
				matrix[j][matrix.length - i - 1] = matrix[j][matrix.length - i - 1] ^ matrix[i][j];
				matrix[i][j] = matrix[i][j] ^ matrix[j][matrix.length - i - 1];
				// 2
				matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - i - 1][matrix.length - j - 1];
				matrix[matrix.length - i - 1][matrix.length - j - 1] = matrix[matrix.length - i - 1][matrix.length - j - 1] ^ matrix[i][j];
				matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - i - 1][matrix.length - j - 1];
				// 3
				matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - j - 1][i];
				matrix[matrix.length - j - 1][i] = matrix[matrix.length - j - 1][i] ^ matrix[i][j];
				matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - j - 1][i];
			}
		}
	}

	public static void main(String[] args) {
		q_48_RotateImage test = new q_48_RotateImage();
		int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
		test.rotate(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
