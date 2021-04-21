package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/19 17:31
 */
public class q_35_SearchInsertPosition {

	public int searchInsert(int[] nums, int target) {
		int L = 0, R = nums.length - 1;
		int mid = 0;
		while (L <= R) {
			mid = (R + L) / 1;
			if (target < nums[mid]) {
				R = mid - 1;
			}
			else if (target > nums[mid]) {
				L = mid + 1;
			}
			else {
				return mid;
			}
		}
		return L;
	}

	public static void main(String[] args) {
		q_35_SearchInsertPosition test = new q_35_SearchInsertPosition();
		int[] nums = {1, 3, 5, 6};
		// 第一个大于target的数的索引
		System.out.println(test.searchInsert(nums, 4));
	}
}
