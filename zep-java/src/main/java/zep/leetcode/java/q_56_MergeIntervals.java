package zep.leetcode.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/19 18:02
 */
public class q_56_MergeIntervals {

	public int[][] f(int[][] res) {
		// 按照二维数组第一个元素排序
		Arrays.sort(res, Comparator.comparingInt(o -> o[0]));

		int[][] ans = new int[res.length][2];
		int i = 0;
		int j = 0;
		while (i < res.length) {
			if (i == res.length - 1) {
				ans[j] = res[i];
				j++;
				break;
			}
			if (res[i + 1][0] <= res[i][1]) {
				res[i][1] = Math.max(res[i + 1][1], res[i][1]);
				res[i][0] = Math.min(res[i + 1][0], res[i][0]);
				ans[j] = res[i];
				i += 2;
				j++;
			}
			else {
				ans[j] = res[i];
				j++;
				i += 1;
			}
		}
		int[][] result = new int[j][2];
		System.arraycopy(ans, 0, result, 0, j);
		return result;
	}

	public int[][] merge(int[][] intervals) {
		int[][] ans = f(intervals);
		while (ans.length != f(ans).length) {
			ans = f(ans);
		}
		return ans;
	}

	public int[][] solution_01(int[][] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(o -> o[0])); // Java自带的排序比实现的快排慢很多
		int[] idx = new int[intervals.length];
		idx[0] = 1;
		for (int i = 1, j = 1; i < intervals.length; i++, j++) {
			if (intervals[i][0] <= intervals[i - 1][1]) {
				intervals[i][1] = Math.max(intervals[i - 1][1], intervals[i][1]);
				intervals[i][0] = Math.min(intervals[i - 1][0], intervals[i][0]);
				if (idx[j - 1] == i) {
					idx[j - 1] = i + 1;
					j--;
				}
			}
			else {
				idx[j] = i + 1;
			}
		}
		int count = 0;
		for (int i = 0; i < idx.length; i++) {
			if (idx[i] != 0) {
				count++;
			}
		}
		int[][] ans = new int[count][2];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = intervals[idx[i] - 1];
		}
		return ans;
	}

	public int[][] solution_02(int[][] intervals) {
		quickSort(intervals, 0, intervals.length - 1);
		int count = 0;
		int[][] res = new int[intervals.length][2];
		res[0][0] = intervals[0][0];
		res[0][1] = intervals[0][1];
		for (int idx = 1; idx < intervals.length; idx++) {
			if (res[count][1] < intervals[idx][0]) {
				count++;
				res[count][0] = intervals[idx][0];
				res[count][1] = intervals[idx][1];
			}
			if (res[count][1] >= intervals[idx][0] && res[count][1] < intervals[idx][1]) {
				res[count][1] = intervals[idx][1];
			}

		}

		return Arrays.copyOfRange(res, 0, count + 1);
	}

	//快排
	private void quickSort(int[][] intervals, int start, int end) {
		if (start >= end) {
			return;
		}
		int midVal = intervals[start][0];

		int i = start, j = end;
		while (i < j) {
			while (intervals[j][0] > midVal && i < j) {
				j--;
			}
			while (intervals[i][0] <= midVal && i < j) {
				i++;
			}
			//交换
			if (i == j) {
				break;
			}
			for (int idx = 0; idx < 2; idx++) {
				int tmp = intervals[i][idx];
				intervals[i][idx] = intervals[j][idx];
				intervals[j][idx] = tmp;
			}
		}
		int midIdx = i;
		for (int idx = 0; idx < 2; idx++) {
			int tmp = intervals[midIdx][idx];
			intervals[midIdx][idx] = intervals[start][idx];
			intervals[start][idx] = tmp;
		}
		quickSort(intervals, start, midIdx - 1);
		quickSort(intervals, midIdx + 1, end);
	}

	public static void main(String[] args) {
		q_56_MergeIntervals test = new q_56_MergeIntervals();
//		int[][] intervals = {{1,3},{2,6}, {8,10},{15,18}};
//		int[][] intervals = {{1,4},{0,0}};
		int[][] intervals = {{1, 4}, {4, 6}, {6, 7}};
//		int[][] intervals = {{0,5}};
		test.solution_01(intervals);
	}

}
