package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 01:38
 */
public class q_410_SplitArrayLargestSum {

	public int splitArray(int[] nums, int m) {
		long L = 0, R = 0;
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		R = sum + 1;
		long ans = 0;
		while (L < R) {
			long mid = (L + R) / 2;
			if (guess(mid, nums, m)) { // 负相关，满足条件取左区间
				ans = mid;
				R = mid - 1;
			}
			else {
				L = mid;
			}
		}
		return (int) ans;
	}

	// 当最大值是mid的时候，m最小是多少
	// 当最大值是mid的时候，m是可用的
	public boolean guess(long mid, int[] nums, long m) {  // m个大小是mid的箱子，去装nums这么多货
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (sum + nums[i] > mid) { // 箱子装不下了
				m--; // 减少箱子的数量
				if (nums[i] > mid) { // 箱子的大小不行，直接返回，重新计算箱子的大小
					return false;
				}
			}
			else {
				sum += nums[i];
			}
		}
		return m >= 1;  // 最少要留一个箱子装nums
		// 当最大值是mid的时候，m是可用的，m没有被消耗光，sum一直是有东西的，所以要遗留一组给sum用
	}

}
