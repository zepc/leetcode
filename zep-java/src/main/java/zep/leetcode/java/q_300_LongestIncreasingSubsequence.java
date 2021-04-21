package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 00:00
 */
public class q_300_LongestIncreasingSubsequence {

	private final int[] dp = new int[10000];
	private final int[] p = new int[10000];
	private int n;

	// 1. 暴力搜索
	public int search(int idx, int[] nums) {
		if (idx < 0) {
			return 0;
		}
		// 2. 去冗余
		if (dp[idx] > 0) {
			return dp[idx];
		}
		int ans = 0;
		for (int i = 0; i < idx; i++) {
			if (nums[idx] > nums[i]) {
				ans = Math.max(ans, search(i, nums)); // idx之前，最长上升子序列的长度是多少
			}
		}
		dp[idx] = ans + 1;
		return ans + 1; // 加上nums[idx]
	}

	public int lengthOfLIS_01(int[] nums) {
		for (int i = 0; i < 10000; i++) {
			dp[i] = 0;
		}
		for (int i = 0; i < nums.length; i++) {
			p[i] = nums[i];
		}
		n = nums.length;
		p[n] = Integer.MAX_VALUE;
		++n;
		int ans = search(n - 1, p);
		return ans - 1; // 去除p[n]
	}


	public int lengthOfLIS_02(int[] nums) {
		System.arraycopy(nums, 0, p, 0, nums.length);
		n = nums.length;
		p[n] = Integer.MAX_VALUE;
		++n;
		for (int i = 0; i < n; i++) {
			int ans = 0;
			for (int j = 0; j < i; j++) { // i之前的，最长递增子序列
				if (p[i] > p[j]) {
					ans = Math.max(ans, dp[j]); // idx之前，最长上升子序列的长度是多少
				}
			}
			dp[i] = ans + 1;
		}
		return dp[n - 1] - 1; // 去除p[n]
	}


	public int lengthOfLIS_03(int[] nums) {
		int[] dp = new int[10000];
		System.arraycopy(nums, 0, p, 0, nums.length);
		n = nums.length;
		p[n] = Integer.MAX_VALUE;
		++n;
		int[] min = new int[10000];// 子序列中，最后一个数字较小的
		int path = 0; // 当前子串的长度
		for (int i = 0; i < 10000; i++) {
			min[i] = 10000001;
		}
		for (int i = 0; i < n; i++) {
			int ans = 0;
//			for (int j = path; j >= 1; j--) {  // 搜索长度为path的递增子序列，又因为min是单调递增，所以可以使用二分法
//				if (p[i] > min[j]){
//					ans = j;
//					break;
//				}
//			}
			int L = 1, R = path;
			while (L <= R) {
				int mid = (L + R) / 2;
				if (min[mid] < p[i]) {
					ans = mid;
					L = mid + 1;
				}
				else {
					R = mid - 1;
				}
			}
			dp[i] = ans + 1;
			min[dp[i]] = Math.min(min[dp[i]], p[i]); // 如果使用path表示dp(n),那么在处理类似a b a序列的时候会产生问题
			path = Math.max(path, dp[i]);
		}
		return dp[n - 1] - 1; // 去除p[n]
	}


	public int lengthOfLIS_04(int[] nums) {
		System.arraycopy(nums, 0, p, 0, nums.length);
		n = nums.length;
		p[n] = Integer.MAX_VALUE;
		++n;
		int[] min = new int[10000];// 子序列中，最后一个数字较小的
		int path = 0; // 当前子串的长度
		for (int i = 0; i < 10000; i++) {
			min[i] = 10000001;
		}
		for (int i = 0; i < n; i++) {
			int ans = 0;
			int L = 1, R = path;
			while (L <= R) {
				int mid = (L + R) / 2;
				if (min[mid] < p[i]) {
					ans = mid;
					L = mid + 1;
				}
				else {
					R = mid - 1;
				}
			}
			int t = ans + 1;
			min[t] = Math.min(min[t], p[i]);
			path = Math.max(path, t);
		}
		return path - 1;
	}

	public static void main(String[] args) {
		q_300_LongestIncreasingSubsequence test = new q_300_LongestIncreasingSubsequence();
		int[] numbers = {4, 10, 4, 3, 8, 9};
		System.out.println(test.lengthOfLIS_04(numbers));
	}

}
