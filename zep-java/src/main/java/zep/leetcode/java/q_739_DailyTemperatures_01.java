package zep.leetcode.java;

import java.util.Stack;

/**
 * Created on 2020/10/11.
 *
 * @author Zhao Peng
 */
public class q_739_DailyTemperatures_01 {

	// 给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
	// 1   1 -4 -2  3  4 -3
	// 1   1  4  2  1  1  0 0
	// 73 74 75 71 69 72 76 73

	public int[] dailyTemperatures(int[] T) {
		return solution_02(T);
	}

	public int[] solution_01(int[] T) {
		int[] diffs = new int[T.length - 1];
		for (int i = 1; i < T.length; i++) {
			int diff = T[i] - T[i - 1];
			diffs[i - 1] = diff;
		}
		int[] result = new int[T.length];
		for (int j = 0; j < diffs.length; j++) {
			int diff = diffs[j];
			if (diff > 0) {
				result[j] = 1;
			}
			else {
				int sum = diff;
				int i = j;
				int count = 1;
				while (sum <= 0) {
					i++;
					if (i >= diffs.length) {
						count = 0;
						break;
					}
					sum += diffs[i];
					count++;
				}
				result[j] = count;
			}
		}
		return result;
	}

	// 使用单调栈
	public int[] solution_02(int[] T) {
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[T.length];
		stack.push(0);
		for (int i = 1; i < T.length; i++) {
			while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
				result[stack.peek()] = i - stack.pop();
			}
			stack.push(i);
		}
		return result;
	}


	public static void main(String[] args) {
		q_739_DailyTemperatures_01 o = new q_739_DailyTemperatures_01();
		// 8, 1, 5, 4, 1, 2, 1, 1, 0, 0
		int[] temperatures = {89, 62, 70, 58, 47, 47, 46, 76, 100, 70};
		int[] result = o.dailyTemperatures(temperatures);
		for (int i : result) {
			System.out.println(i);
		}
	}
}
