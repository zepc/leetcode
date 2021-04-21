package zep.leetcode.java.ds.list;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/13 0013
 * @description leetcode
 */
public class ListNode {

	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "ListNode{" +
				"val=" + val +
				'}';
	}
}
