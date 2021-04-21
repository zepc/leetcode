package zep.leetcode.java;


/*
 * [9] 回文数
 */

class q_9_PalindromeNumber {

	public boolean isPalindrome(int x) {
		String s = Integer.toString(x);
		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		q_9_PalindromeNumber test = new q_9_PalindromeNumber();
		System.out.println(test.isPalindrome(1221));
	}
}

