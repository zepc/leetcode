package zep.leetcode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 23:01
 */
public class q_509_FibonacciNumber {

	// <n, sum>
	// 使用cache保存中间结果，避免重复计算
	Map<Integer, Integer> cache = new HashMap<>();

	public int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		int result = fib(n - 1) + fib(n - 2);
		cache.put(n, result);
		return result;
	}


	public static void main(String[] args) {
		q_509_FibonacciNumber test = new q_509_FibonacciNumber();
		System.out.println(test.fib(3));
	}
}
