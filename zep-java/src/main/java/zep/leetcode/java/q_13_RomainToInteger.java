package zep.leetcode.java;

import java.util.HashMap;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/13 22:49
 */
public class q_13_RomainToInteger {

	public int romanToInt(String s) {

		HashMap<Character, Integer> map = new HashMap() {{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}};

		int last = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			int num = map.get(c);
			if (num < last) {
				num = last - num;
			}
			else {
				num = num + last;
			}
			last = num;
		}
		return last;
	}

	public static void main(String[] args) {
		q_13_RomainToInteger test = new q_13_RomainToInteger();
		System.out.println(test.romanToInt("MCMXCIV"));
	}
}
