package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java.ds
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 00:51
 */
public class q_69_Sqrt {

	public int mySqrt(int x) {
		long L = 0, R = ((long) x) + 1;
		long ans = 0;
		while (L < R) {
			long mid = (L + R) / 2;
			if (guess(mid, x)) {
				ans = mid;
				L = mid + 1;
			}
			else {
				R = mid;
			}
		}
		return (int) ans;
	}

	// 可以接受的值
	public boolean guess(long mid, long y) {
//		long z = mid * mid; // y最小值
		return mid * mid <= y;
	}


	public static void main(String[] args) {
		q_69_Sqrt test = new q_69_Sqrt();
		System.out.println(test.mySqrt(2147483647));
	}

}
