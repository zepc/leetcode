package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 21:22
 */
public class q_53_MaxSumSubArray {


	public int sumSubArray(int start, int end, int tag, int[] nums) {
		if (start > end) {
			return 0;
		}
		int ans = 0;
		for (int i = start; i <= end; i++) {
			if (nums[i] > tag) {
				int sumL = sumSubArray(start, i - 1, nums[i], nums);
				int sumR = sumSubArray(i + 1, end, nums[i], nums);
				if (sumL > 0 && sumR > 0) {
					return sumR + sumL + nums[i];
				}
				else {
					if (sumL > 0) {
						return sumL + nums[i];
					}
					else if (sumR > 0) {
						return sumR + nums[i];
					}
					else {
						return nums[i];
					}
				}
			}
		}
		return ans;
	}


	public int maxSubArray(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int[] dp = new int[nums.length];
		int res = nums[0];
		dp[0] = nums[0]; // dp[i]表示在[0, i]区间和的最大值

		for (int i = 1; i < nums.length; i++) {
			if (dp[i - 1] > 0) {
				dp[i] = dp[i - 1] + nums[i];
			}
			else {
				dp[i] = nums[i];
			}
			res = Math.max(dp[i], res);
		}
		return res;
	}


	public int test(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		int curr = 1;
		int ans = nums[0];
		boolean flag = false;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = curr; j < i + 1; j++) {
				sum += nums[j];
			}
			if (sum > 0) {
				ans = ans + sum;
				flag = true;
			}
			if (nums[i] > ans) {
				ans = nums[i];
				flag = true;
			}
			if (flag) {
				curr = i + 1;
				flag = false;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		q_53_MaxSumSubArray test = new q_53_MaxSumSubArray();
//		int[] numbers = {-2,1,-3,4,-1,2,1,-5,4};
		int[] numbers = {8, -19, 5, -4, 20};
//		int[] numbers = {-2, 1};
//		test.sumSubArray(0, numbers.length - 1, Integer.MIN_VALUE, numbers);
		System.out.println(test.test(numbers));
	}

}
