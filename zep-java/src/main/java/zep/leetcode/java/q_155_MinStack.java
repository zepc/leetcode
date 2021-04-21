package zep.leetcode.java;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack();
 * obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4 = obj.getMin();
 */

/**
 * Created on 2020/10/10.
 *
 * @author Zhao Peng
 */
public class q_155_MinStack {

	public void solution_01() {
		class MiniStack {

			private final List<Integer> data;
			private final List<Integer> sortedData;

			/**
			 * initialize your data structure here.
			 */
			public MiniStack() {
				data = new ArrayList<>();
				sortedData = new ArrayList<>();
			}


			public void push(int x) {
				data.add(x);
				sortedData.add(x);
			}

			public void pop() {
				if (!this.isEmpty()) {
					sortedData.remove(data.get(data.size() - 1));
					data.remove(data.size() - 1);
				}
			}

			private boolean isEmpty() {
				return data.isEmpty();
			}

			public int top() {
				return data.get(data.size() - 1);
			}

			public int getMin() {
				// 为什么对于 -2147483648 和 2147483647 的排序 使用sort((a,b) -> a - b) 不能排序出结果
				// 而可以使用Integer::compareTo进行排序。
				sortedData.sort(Integer::compareTo);
				return sortedData.get(0);
			}
		}

		MiniStack minStack = new MiniStack();
		minStack.push(2147483647);
		minStack.push(-2147483648);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());

		int x = -2147483648;
		System.out.println("x = " + x);
	}

	public void solution_02() {

		class MiniStack {
			/*
			 * 额外空间也是用栈，原栈中每push进一个元素，最小栈也push进当前栈已有元素的最小值
			 * 这样减少了比较次数，直接记录了当前栈的最小值。
			 * */

			private final Deque<Integer> xStack;
			private final Deque<Integer> minStack;


			public MiniStack() {
				this.xStack = new LinkedList<>();
				this.minStack = new LinkedList<>();
				minStack.add(Integer.MAX_VALUE);
			}

			public void push(int x) {
				int min = minStack.peek();
				if (x < min) {
					minStack.push(x);
				}
				xStack.push(x);
			}

			public void pop() {
				xStack.pop();
				minStack.pop();
			}

			public int top() {
				return xStack.peek();
			}

			public int getMin() {
				return minStack.peek();
			}
		}

		MiniStack minStack = new MiniStack();
		minStack.push(2147483647);
		minStack.push(-2147483648);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());

		int x = -2147483648;
		System.out.println("x = " + x);
	}

	public void solution_03() {

		class MiniStack {

			private final Deque<List<Integer>> xStack;
			private List<Integer> tuple;
			private int min = Integer.MAX_VALUE;

			public MiniStack() {
				this.xStack = new LinkedList<>();
			}

			public void push(int x) {
				tuple = new ArrayList<>(2);
				if (x < min) {
					min = x;
				}
				tuple.add(x);
				tuple.add(min);
				xStack.push(tuple);
			}

			public void pop() {
				xStack.pop();
			}

			public int top() {
				return xStack.peek().get(0);
			}

			public int getMin() {
				return xStack.peek().get(1);
			}
		}

		MiniStack minStack = new MiniStack();
		minStack.push(2147483647);
		minStack.push(-2147483648);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());

		int x = -2147483648;
		System.out.println("x = " + x);
	}

	public void solution_04() {

		class MiniStack {

			/*
			 * 通过存储差值，来替代比较
			 * */

			private final Deque<Integer> xStack;
			private int min;

			public MiniStack() {
				this.xStack = new LinkedList<>();
				this.min = -1;
			}

			public void push(int x) {
				if (xStack.isEmpty()) {
					xStack.push(0);
					min = x;
				}
				else {
					int diff = x - min;
					xStack.push(diff);
					if (diff < 0) {
						this.min = x;
					}
				}
			}

			public void pop() {
				if (!xStack.isEmpty()) {
					int diff = xStack.pop();
					if (diff < 0) {
						min = min - diff;
					}
				}
			}

			public int top() {
				Integer diff = xStack.peek();
				if (diff > 0) {
					return min + diff;
				}
				else {
					return min;
				}

			}

			public int getMin() {
				return min;
			}
		}

		MiniStack minStack = new MiniStack();
		minStack.push(2147483647);
		minStack.push(-2147483648);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());

		int x = -2147483648;
		System.out.println("x = " + x);
	}

	public static void main(String[] args) {

	}
}


