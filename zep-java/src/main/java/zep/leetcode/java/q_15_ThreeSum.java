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
 * @Description： Created by Zep on 2021/04/13 23:02
 */
public class q_15_ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length < 3) {
			return new ArrayList<>();
		}
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int target = -nums[i];
			int left = i + 1;
			int right = nums.length - 2;
			while (left < right) {
				int sum = nums[left] + nums[right];
				if (sum > target) {
					right--;
				}
				else if (sum < target) {
					left++;
				}
				else {
					res.add(Arrays.asList(i, left, right));
					while (left < right && nums[left] == nums[++left]) {
					}
					while (left < right && nums[right] == nums[--right]) {
					}
				}
			}
		}
		return res;
	}
}
