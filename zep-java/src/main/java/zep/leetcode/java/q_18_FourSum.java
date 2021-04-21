package zep.leetcode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/14 00:57
 */
public class q_18_FourSum {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return ans;
		}
		Arrays.sort(nums);
		int length = nums.length;
		for (int i = 0; i < length - 3; i++) {

			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
				break;
			}
			if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
				continue;
			}

			for (int j = i + 1; j < length - 2; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
					break;
				}
				if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
					continue;
				}
				int minus = target - (nums[i] + nums[j]);
				int left = j + 1;
				int right = length - 1;
				while (left < right) {
					int sum = nums[left] + nums[right];
					if (sum < minus) {
						left++;
					}
					else if (sum > minus) {
						right--;
					}
					else {
						ans.add(Arrays.asList(i, j, left, right));
						while (left < right && nums[left] == nums[++left]) {
						}
						while (left < right && nums[right] == nums[--right]) {
						}
					}
				}
			}
		}
		return ans;
	}


	public static void main(String[] args) {
		int[] a = new int[]{0, 0, 0, 0};
		int target = 0;
		q_18_FourSum test = new q_18_FourSum();
		System.out.println(test.fourSum(a, target));
	}
}
