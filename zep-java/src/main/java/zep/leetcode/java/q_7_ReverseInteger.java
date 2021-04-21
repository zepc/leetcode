package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @author Zep
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/13 15:17
 */
public class q_7_ReverseInteger {

	public int reverse(int x) {
		long temp = 0;
		while (x != 0) {
			temp = x % 10 + temp * 10;
			x = x / 10;
		}
		if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
			return 0;
		}
		return ((int) temp);
	}

	public static void main(String[] args) {
		q_7_ReverseInteger test = new q_7_ReverseInteger();
		System.out.println(test.reverse(-321));
	}
}
