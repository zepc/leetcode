package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/20 00:30
 */
public class q_73_SetMatrixZeroes {

	public void setZeroes(int[][] matrix) {

		// 记录0的位置，遍历清除

//		Set<Integer> idxa = new HashSet<>();
//		Set<Integer> idxb = new HashSet<>();

		int[] idxa = new int[matrix.length];
		int[] idxb = new int[matrix[0].length];

//		int max = Math.max(matrix.length, matrix[0].length); // 会删除其他列，有问题
//		int[] idx = new int[max];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {

//					idxa[i] = 1;
//					idxb[j] = 1;
//					if (!idxa.contains(i)) idxa.add(i);
//					if (!idxb.contains(j)) idxb.add(j);
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
//				if (idxa.contains(i) || idxb.contains(j)) matrix[i][j] = 0;
				if (idxa[i] == 1 || idxb[j] == 1) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	public void solution_01(int[][] matrix) {
		// 使用第一行和第一列来标记剩余的0元素
		int row = matrix.length;
		int col = matrix[0].length;
		boolean row0_flag = false;
		boolean col0_flag = false;
		// 第一行是否有零
		for (int j = 0; j < col; j++) {
			if (matrix[0][j] == 0) {
				row0_flag = true;
				break;
			}
		}
		// 第一列是否有零
		for (int i = 0; i < row; i++) {
			if (matrix[i][0] == 0) {
				col0_flag = true;
				break;
			}
		}
		// 把第一行第一列作为标志位
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}
		// 置0
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if (row0_flag) {
			for (int j = 0; j < col; j++) {
				matrix[0][j] = 0;
			}
		}
		if (col0_flag) {
			for (int i = 0; i < row; i++) {
				matrix[i][0] = 0;
			}
		}
	}

	public static void main(String[] args) {
		q_73_SetMatrixZeroes test = new q_73_SetMatrixZeroes();
		int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
		test.setZeroes(matrix);
	}
}
