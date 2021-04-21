package zep.leetcode.java.ds.二分查找;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java.ds.二分查找
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 01:35
 */
public class BinaryFunction {


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

	public boolean guess(long mid, long y) {
		return mid * mid <= y;
	}

}
