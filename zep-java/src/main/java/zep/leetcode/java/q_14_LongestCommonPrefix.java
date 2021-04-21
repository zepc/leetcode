package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/20 18:01
 */
public class q_14_LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}
		char[] cs = new char[strs[0].length()];
		boolean isCommon = true, hasNull = false;
		int k = 0;
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i < strs[j].length()) {
					if (c == strs[j].charAt(i)) {
						isCommon = true;
					}
					else {
						isCommon = false;
						break;
					}
				}
				else {
					hasNull = true;
					break;
				}
			}
			if (hasNull) {
				break;
			}
			if (isCommon) {
				cs[k] = c;
				k++;
				isCommon = false;
			}
			else {
				break;
			}
		}
		return new String(cs).trim();
	}

	public String solution(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		for (int i = 0; i < strs[0].length(); i++) {
			for (int j = 1; j < strs.length; j++) {
				if (strs[j].length() < i + 1 || strs[j].charAt(i) != strs[0].charAt(i)) {
					return i == 0 ? "" : strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}

	public static void main(String[] args) {
		q_14_LongestCommonPrefix test = new q_14_LongestCommonPrefix();
//		String[] strs = {"abab", "aba", ""};
		String[] strs = {"a"};
		System.out.println(test.longestCommonPrefix(strs));
	}

}
