package zep.leetcode.java;

/**
 * 泰波那契序列 Tn 定义如下： 
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4 输出：4 解释： T_3 = 0 + 1 + 1 = 2 T_4 = 1 + 1 + 2 = 4 示例 2：
 * <p>
 * 输入：n = 25 输出：1389537  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 37 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/14 0014
 * @description leetcode
 */
public class q_1137_Taibonacci {

	/**
	 * 从顶向下，递归
	 * @param n
	 * @return
	 */
	public int tribonacci(int n) {

		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}

		return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);

	}

	/**
	 * 尾递归可以使用循环来消除
	 *
	 *  从下向上，反向递推
	 * @return
	 */
	public int tribonacci1(int n) {

		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}

		int ta = 0;
		int tb = 1;
		int tc = 1;
		int ans = 2;

		for (int i = 4; i <= n; i++) {
			int t = ans - ta;
			ta = tb;
			tb = tc;
			tc = ans;
			ans = ans + t;
		}

		return ans;
	}

	public static void main(String[] args) {
		q_1137_Taibonacci tribonacci = new q_1137_Taibonacci();
		int n = tribonacci.tribonacci(25);
		System.out.println("n = " + n);

	}
}
