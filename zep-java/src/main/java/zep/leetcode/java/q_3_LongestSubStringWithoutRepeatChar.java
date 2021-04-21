package zep.leetcode.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * [3] 无重复字符的最长子串
 * 同438, 76, 567都是使用滑动窗口做
 */

class q_3_LongestSubStringWithoutRepeatChar {

	public int solution(String s) {
		return solution_02(s);
	}

	public int solution_01(String s) {
		char[] cs = s.toCharArray();
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			List<Character> list = new ArrayList<>();
			list.add(cs[i]);
			int j = i;
			while (j + 1 < s.length() && !list.contains(cs[j + 1])) {
				j++;
				list.add(cs[j]);
			}
			if (max < list.size()) {
				max = list.size();
			}

			if (j + 1 >= s.length()) {
				break;
			}
			i = i + list.indexOf(cs[j + 1]);
			while (j + 1 < s.length() && cs[i] == cs[j + 1]) {
				i++;
				j++;
			}
			if (max >= s.length() - i) {
				break;
			}
			i--;
		}
		return max;
	}

	/**
	 * 利用滑动窗口
	 */
	public int solution_02(String s) {
		int ans = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0, left = 0; right < s.length(); right++) {
			// 重复字符，窗口滑动一下，left移动一步
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				left = Math.max(map.get(c), left); // pww的时候，left需要指向第二个w
			}
			// 没有重复字符，计算长度，保存字符
			ans = Math.max(ans, right - left + 1);
			map.put(c, right + 1);
		}
		return ans;
	}

	/**
	 * 利用位图取代map
	 */
	public int solution_03(String s) {
		int ans = 0;
//		BitSet bs = new BitSet(256);
//		int left = 0, right = 0;
//		while (left < s.length()){
//			if (right < s.length() && !bs.get(s.charAt(right))){
//				bs.set(s.charAt(right));
//				right++;
//			}
//			else{
//				bs.set(s.charAt(left), false);
//				left++;
//			}
//			ans = Math.max(ans, right - left);
//		}
		return ans;
	}

	public static void main(String[] args) {
		q_3_LongestSubStringWithoutRepeatChar test = new q_3_LongestSubStringWithoutRepeatChar();
//	    System.out.println(test.solution_03("abcabcbb"));
		System.out.println(test.solution_03("pwwkew"));
	}
}

