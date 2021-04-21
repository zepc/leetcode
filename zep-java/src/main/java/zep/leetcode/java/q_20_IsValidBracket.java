package zep.leetcode.java;


import java.util.Deque;
import java.util.Stack;

/**
 * Created on 2020/10/11.
 *
 * @author Zhao Peng
 */
public class q_20_IsValidBracket {

	private Deque<String> stack;

	// 直接使用 Character 来作为泛型，而不是使用 String
//    private Stack<Character> stack = new Stack<>();

	//  '('，')'，'{'，'}'，'['，']'

	public boolean isValid(String s) {
		return solution_02(s);
	}


	public boolean solution_01(String s) {
		for (char c : s.toCharArray()) {
			String cs, peek;
			char top;
			switch (c) {
				case '(':
					cs = String.valueOf(c);
					stack.push(cs);
					break;
				case '[':
					cs = String.valueOf(c);
					stack.push(cs);
					break;
				case '{':
					cs = String.valueOf(c);
					stack.push(cs);
					break;
				case ')':
					peek = stack.peek();
					if (peek != null) {
						top = peek.charAt(0);
					}
					else {
						return false;
					}
					if (top == '(') {
						stack.pop();
					}
					else {
						return false;
					}
					break;
				case ']':
					peek = stack.peek();
					if (peek != null) {
						top = peek.charAt(0);
					}
					else {
						return false;
					}
					if (top == '[') {
						stack.pop();
					}
					else {
						return false;
					}
					break;
				case '}':
					peek = stack.peek();
					if (peek != null) {
						top = peek.charAt(0);
					}
					else {
						return false;
					}
					if (top == '{') {
						stack.pop();
					}
					else {
						return false;
					}
				default:

			}
		}

		return stack.isEmpty();
	}


	public boolean solution_02(String s) {
		Stack<Character> stack = new Stack<>();

		for (char c : s.toCharArray()) {
			if (c == '(') {
				stack.push(')');
			}
			else if (c == '{') {
				stack.push('}');
			}
			else if (c == '[') {
				stack.push(']');
			}
			else if (stack.isEmpty() || c != stack.pop()) {
				return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {
		q_20_IsValidBracket o = new q_20_IsValidBracket();
		System.out.println(o.isValid("{[()]}"));
	}
}
