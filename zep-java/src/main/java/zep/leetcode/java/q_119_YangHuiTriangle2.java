package zep.leetcode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 22:01
 */
public class q_119_YangHuiTriangle2 {

	public List<Integer> getRow(int rowIndex) {
		if (rowIndex == 1) {
			List<Integer> numbers = new ArrayList<>();
			numbers.add(1);
			return numbers;
		}
		List<Integer> nums = getRow(rowIndex - 1);
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < rowIndex; i++) {
			if (i == 0 || i == rowIndex - 1) {
				ans.add(1);
			}
			else {
				ans.add(nums.get(i - 1) + nums.get(i));
			}
		}
		return ans;
	}


	public static void main(String[] args) {
		q_119_YangHuiTriangle2 test = new q_119_YangHuiTriangle2();
		System.out.println(test.getRow(3));
	}

}
