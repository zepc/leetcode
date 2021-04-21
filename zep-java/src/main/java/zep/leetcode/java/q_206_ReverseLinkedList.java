package zep.leetcode.java;

import zep.leetcode.java.ds.list.ListNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/16 22:17
 */
public class q_206_ReverseLinkedList {

	public ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		ListNode reversed = reverseList(head.next);
		ListNode p = reversed;
//		while (p.next != null){
//			p = p.next;
//		}
		p.next = head;
		head.next = null;
		return reversed;
	}

	public ListNode reverseList01(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p = reverseList01(head.next);
		head.next.next = head;
		head.next = null;
		return p;
	}


	// 	如果需要使用preNode的时候，记得构建一个fakeNode
	public static void main(String[] args) {
		q_206_ReverseLinkedList test = new q_206_ReverseLinkedList();
		ListNode first = new ListNode(1);
		ListNode second = new ListNode(2);
		ListNode third = new ListNode(3);
		ListNode fourth = new ListNode(4);
		first.next = second;
		second.next = third;
		third.next = fourth;
		test.reverseList01(first);
	}

}
