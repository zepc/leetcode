package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 12:33
 */
public class q_50_Powxn {

	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		if (n == -1) {
			return 1 / x;
		}
		double ans = myPow(x, n / 2);
		if (n < 0) {
			x = 1 / x;
		}
		if ((n & 1) == 1) {
			return ans * ans * x;
		}
		else {
			return ans * ans;
		}
	}


	private double fastPow(double x, long n) {
		if (n == 0) {
			return 1.0;
		}
		double half = fastPow(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		}
		else {
			return half * half * x;
		}
	}

	// 先分情况再进行递归
	public double solution_02(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		return fastPow(x, N);
	}

	// 空间复杂度为O(1)
	public double solution_03(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		double ans = 1;
		double current_product = x;
		for (long i = N; i > 0; i /= 2) {
			if ((i % 2) == 1) {
				ans = ans * current_product;
			}
			current_product = current_product * current_product;
		}
		return ans;
	}

	public static void main(String[] args) {
		q_50_Powxn test = new q_50_Powxn();
		System.out.println(test.myPow(2, -4));
	}

}
