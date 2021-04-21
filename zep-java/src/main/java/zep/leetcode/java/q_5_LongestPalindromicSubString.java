package zep.leetcode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/18 12:01
 */
public class q_5_LongestPalindromicSubString {

	public boolean isPalindrome(String s) {
		if (s.length() == 1) {
			return true;
		}
		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

	// 暴力法
	public String longestPalindrome(String s) {
		if (s.length() == 1) {
			return s;
		}
		int ans = 0;
		int start = 0, end = 0;
		for (int i = 0; i + ans < s.length(); i++) {
			for (int j = s.length() - 1; j > i; j--) {
				if (i - start == end - j) {
					break;
				}
				if (s.charAt(j) == s.charAt(i)) {
					// 判断子序列是否回文
					if (isPalindrome(s.substring(i + 1, j))) {
						if (ans < j - i + 1) {
							ans = j - i + 1;
							start = i;
							end = j;
						}
						break;
					}
				}
			}
		}
		return s.substring(start, end + 1);
	}

	// dp: dp[i][j] = dp[i-1][j+1] ^ (Si == Sj)
	public String longestPalindrome_01(String s) {
		int len = s.length();
		if (len < 2) {
			return s;
		}
		int maxLen = 1;
		int begin = 0;
		boolean[][] dp = new boolean[len][len]; // dp[i][j] 表示 s[i..j] 是否是回文串
		for (int i = 0; i < len; i++) {
			dp[i][i] = true;  // 初始化：所有长度为 1 的子串都是回文串
		}
		char[] charArray = s.toCharArray();
		// 递推开始
		for (int L = 2; L <= len; L++) {    // 先枚举子串长度
			for (int i = 0; i < len; i++) {      // 枚举左边界，左边界的上限设置可以宽松一些
				int j = L + i - 1; // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
				if (j >= len) { // 如果右边界越界，就可以退出当前循环
					break;
				}
				if (charArray[i] != charArray[j]) {
					dp[i][j] = false;
				}
				else {
					if (j - i < 3) {
						dp[i][j] = true;
					}
					else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}
				if (dp[i][j] && j - i + 1 > maxLen) {  // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
					maxLen = j - i + 1;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + maxLen);
	}

	// 中心扩展法，从回文传的中心向两边进行扩展
	public String solution_02(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i); // 边界情况即为子串长度为1或2的情况
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	// 从回文子串的中心向两边进行扩展
	public int expandAroundCenter(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			--left;
			++right;
		}
		return right - left - 1;
	}

	// 扩展的时候出现冗余，减少扩展次数
	public String solution_03(String s) {
		int start = 0, end = -1;
		StringBuffer t = new StringBuffer("#");
		for (int i = 0; i < s.length(); ++i) {
			t.append(s.charAt(i));
			t.append('#');
		}
		t.append('#'); // 让所有的字符串变为奇数串
		s = t.toString();

		List<Integer> arm_len = new ArrayList<Integer>();
		int right = -1, j = -1;
		for (int i = 0; i < s.length(); ++i) {
			int cur_arm_len;
			if (right >= i) {
				int i_sym = j * 2 - i;
				int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
				cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
			}
			else {
				cur_arm_len = expand(s, i, i);
			}
			arm_len.add(cur_arm_len);
			if (i + cur_arm_len > right) {
				j = i;
				right = i + cur_arm_len;
			}
			if (cur_arm_len * 2 + 1 > end - start) {
				start = i - cur_arm_len;
				end = i + cur_arm_len;
			}
		}

		StringBuffer ans = new StringBuffer();
		for (int i = start; i <= end; ++i) {
			if (s.charAt(i) != '#') {
				ans.append(s.charAt(i));
			}
		}
		return ans.toString();
	}

	public int expand(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			--left;
			++right;
		}
		return (right - left - 2) / 2;
	}

	public static void main(String[] args) {
		q_5_LongestPalindromicSubString test = new q_5_LongestPalindromicSubString();
		System.out.println(test.longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

}
