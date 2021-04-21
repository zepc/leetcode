package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/13 22:45
 */
public class q_11_ContainerWithMostWater {

	public int maxArea(int[] height) {
		int start = 0;
		int end = height.length - 1;
		int area = 0;
		while (start < end) {
			int w = end - start - 1;
			int h = 0;
			if (height[start] < height[end]) {
				h = height[start];
				start++;
			}
			else {
				h = height[end];
				end--;
			}
			int temp = w * h;
			if (temp > area) {
				area = temp;
			}
		}
		return area;
	}
}
