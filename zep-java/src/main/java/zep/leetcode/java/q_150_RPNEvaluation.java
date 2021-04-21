package zep.leetcode.java;

import java.util.Stack;

/**
 * Created on 2020/10/12.
 *
 * @author Zhao Peng
 * <p>
 * 逆波兰式求值
 */
public class q_150_RPNEvaluation {

	public int evalRPN(String[] tokens) {
		return solution_03(tokens);
	}


	private final Stack<String> stack = new Stack<>();

	public int solution_01(String[] tokens) {
		int result = 0;
		for (String token : tokens) {
			if (!"/".equals(token) && !"-".equals(token) && !"+".equals(token) && !"*".equals(token)) {
				stack.push(token);
			}
			else {
				int a = Integer.parseInt(stack.pop());
				int b = Integer.parseInt(stack.pop());
				switch (token) {
					case "/":
						result = b / a;
						break;
					case "-":
						result = b - a;
						break;
					case "*":
						result = b * a;
						break;
					case "+":
						result = b + a;
						break;
					default:
						break;
				}
				stack.push(String.valueOf(result));
			}
		}
		return Integer.parseInt(stack.pop());
	}

	public int solution_02(String[] tokens) {
		for (String token : tokens) {
			int a, b, result;
			switch (token) {
				case "/":
					a = Integer.parseInt(stack.pop());
					b = Integer.parseInt(stack.pop());
					result = b / a;
					stack.push(String.valueOf(result));
					break;
				case "-":
					a = Integer.parseInt(stack.pop());
					b = Integer.parseInt(stack.pop());
					result = b - a;
					stack.push(String.valueOf(result));
					break;
				case "*":
					a = Integer.parseInt(stack.pop());
					b = Integer.parseInt(stack.pop());
					result = b * a;
					stack.push(String.valueOf(result));
					break;
				case "+":
					a = Integer.parseInt(stack.pop());
					b = Integer.parseInt(stack.pop());
					result = b + a;
					stack.push(String.valueOf(result));
					break;
				default:
					stack.push(token);
					break;
			}
		}
		return Integer.parseInt(stack.pop());
	}


	public int solution_03(String[] tokens) {
		int[] numStack = new int[tokens.length / 2 + 1]; // 使用数组模拟栈
		int index = 0;
		for (String token : tokens) {
			switch (token) {
				case "/":
					numStack[index - 2] /= numStack[--index];
					break;
				case "-":
					numStack[index - 2] -= numStack[--index];
					break;
				case "*":
					numStack[index - 2] *= numStack[--index];
					break;
				case "+":
					numStack[index - 2] += numStack[--index];
					break;
				default:
					numStack[index++] = Integer.parseInt(token);
					break;
			}
		}
		return numStack[0];
	}


	public static void main(String[] args) {
		String[] s = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
		q_150_RPNEvaluation a = new q_150_RPNEvaluation();
		System.out.println(a.evalRPN(s));

	}
}
