package zep.leetcode.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * 示例:
 * <p>
 * 输入: 5 输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/16 0016
 * @description leetcode
 */
public class q_118_YangHuiTriangle {

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			List<Integer> numbers = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				int number = num(i, j);
				numbers.add(number);
			}
			result.add(numbers);
		}
		return result;
	}

	// 递归困惑的地方，有时候知道函数的递归关系，但是写不出来程序
	// 有时候写出来的递归程序，需要借助一个全局变量保存状态的时候
	// 那是因为递归的顺序和本质没有理解清楚
	private int num(int i, int j) {
		if (j == 0 || j == i) {
			return 1;
		}
		return num(i - 1, j - 1) + num(i - 1, j);
	}


	// 解答
	public List<List<Integer>> solution(int numRows) {
		if (numRows == 1) {
			List<Integer> numbers = new ArrayList<>();
			List<List<Integer>> ans = new ArrayList<>();
			numbers.add(1);
			ans.add(numbers);
			return ans;
		}
		List<List<Integer>> res = solution(numRows - 1);
		List<Integer> last = res.get(numRows - 1 - 1);
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			if (i == 0 || i == numRows - 1) {
				nums.add(1);
			}
			else {
				nums.add(last.get(i - 1) + last.get(i));
			}
		}
		res.add(nums);
		return res;
	}


	private List<List<Integer>> generate2(int rowNumbers) {
		List<List<Integer>> result = new ArrayList<>();
		if (rowNumbers == 1) {
			return Collections.singletonList(Collections.singletonList(1));
		}

		result.add(Collections.singletonList(1));

		for (int i = 1; i < rowNumbers; i++) {
			List<Integer> numbers = new ArrayList<>();
			List<Integer> preNumbers = result.get(i - 1);
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					numbers.add(1);
				}
				else {
					int a = preNumbers.get(j) + preNumbers.get(j - 1);
					numbers.add(a);
				}
			}
			result.add(numbers);

		}
		return result;
	}

	public static void main(String[] args) {
		q_118_YangHuiTriangle triangle = new q_118_YangHuiTriangle();
		List<List<Integer>> lists = triangle.solution(5);
		for (List<Integer> list : lists) {
			for (Integer i : list) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
