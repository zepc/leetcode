package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/19 14:45
 */
public class q_724_FindPivotIndex {

	public int pivotIndex(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		for (int i = 0; i < nums.length; i++) {
			int lsum = 0, rsum = 0;
			for (int j = 0; j < i; j++) {
				lsum += nums[j];
			}
			for (int j = i + 1; j < nums.length; j++) {
				rsum += nums[j];
			}
			if (lsum == rsum) {
				return i;
			}
		}
		return -1;
	}


	public int solution(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int lsum = 0, rsum = 0;
		for (int i = 1; i < nums.length; i++) {
			rsum += nums[i];
		}
		if (rsum == 0) {
			return 0;
		}
		for (int i = 1; i < nums.length; i++) {
			lsum += nums[i - 1];
			rsum -= nums[i];
			if (lsum == rsum) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		q_724_FindPivotIndex test = new q_724_FindPivotIndex();
		int[] nums = {1, 7, 3, 6, 5, 6};
		System.out.println(test.solution(nums));
	}
}
