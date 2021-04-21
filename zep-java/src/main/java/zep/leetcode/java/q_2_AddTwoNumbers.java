package zep.leetcode.java;

import zep.leetcode.java.ds.list.ListNode;

/*
 * [2] 两个链表对应元素相加
 */


class q_2_AddTwoNumbers {

	public ListNode solution(ListNode la, ListNode lb) {
		return solution_03(la, lb);
	}


	public ListNode solution_01(ListNode l1, ListNode l2) {
		int isCarryBit = 0;
		int flag = 0;
		ListNode head = null;
		ListNode p = null;
		while (l1 != null && l2 != null) {
			if (head == null) {
				int sum = l1.val + l2.val;
				if (sum >= 10) {
					isCarryBit = 1;
				}
				head = new ListNode(sum % 10);
				l1 = l1.next;
				l2 = l2.next;
				p = head;
				if (l1 == null) {
					flag = 2;
				}
				if (l2 == null) {
					flag = 3;
				}
				if (l2 == null && l1 == null) {
					flag = 1;
				}
			}
			else {
				int sum = l1.val + l2.val;
				if (isCarryBit == 1) {
					sum++;
					isCarryBit = 0;
				}
				if (sum >= 10) {
					isCarryBit = 1;
				}
				ListNode node = new ListNode(sum % 10);
				p.next = node;
				p = node;
				l1 = l1.next;
				l2 = l2.next;
				if (l1 == null) {
					flag = 2;
				}
				if (l2 == null) {
					flag = 3;
				}
				if (l2 == null && l1 == null) {
					flag = 1;
				}
			}
		}
		switch (flag) {
			case 1:
				if (isCarryBit == 1) {
					ListNode node = new ListNode(1);
					p.next = node;
					isCarryBit = 0;
				}
				break;
			case 2:
				while (l2 != null) {
					int sum = l2.val;
					if (isCarryBit == 1) {
						sum += 1;
						isCarryBit = 0;
					}
					if (sum >= 10) {
						isCarryBit = 1;
					}
					l2.val = sum % 10;
					p.next = l2;
					l2 = l2.next;
					p = p.next;
				}
				if (isCarryBit == 1) {
					ListNode node = new ListNode(1);
					p.next = node;
				}
				break;
			default:
				while (l1 != null) {
					int sum = l1.val;
					if (isCarryBit == 1) {
						sum += 1;
						isCarryBit = 0;
					}
					if (sum >= 10) {
						isCarryBit = 1;
					}
					l1.val = sum % 10;
					p.next = l1;
					l1 = l1.next;
					p = p.next;
				}
				if (isCarryBit == 1) {
					ListNode node = new ListNode(1);
					p.next = node;
				}
				break;
		}
		return head;
	}

	public ListNode solution_02(ListNode la, ListNode lb) {
		if (la == null || lb == null) {
			return null;
		}
		ListNode fakeHead = new ListNode(0);
		ListNode current = fakeHead;
		int carry = 0;
		while (la != null && lb != null) {
			int sum = la.val + lb.val + carry;
//			if (sum < 10) {
//				current.val = sum;
//
//			}
//			else {
//				current.val = sum - 10;
//				carry = 1;
//			}

			carry = sum / 10;
			current.next = new ListNode(sum % 10);
			current = current.next;
			la = la.next;
			lb = lb.next;
		}
		// 如果进位，就不能这样处理了，需要遍历
		while (lb != null) {
			if (carry != 0) {
				int sum = lb.val + carry;
				carry = sum / 10;
				current.next = new ListNode(sum % 10);
				current = current.next;
				lb = lb.next;
			}
			else {
				current.next = lb;
				break;
			}
		}
		while (la != null) {
			if (carry != 0) {
				int sum = la.val + carry;
				carry = sum / 10;
				current.next = new ListNode(sum % 10);
				current = current.next;
				la = la.next;
			}
			else {
				current.next = la;
				break;
			}
		}
		return fakeHead.next;
	}

	public ListNode solution_03(ListNode l1, ListNode l2) {
		ListNode fakeHeader = new ListNode(0);
		ListNode curNode = fakeHeader;
		ListNode p = l1;
		ListNode q = l2;
		// 使用商判断是否进位，将商和余数集合起来
		// boolean isCarryBit = false;
		int carry = 0;
		while (p != null || q != null) {
			// 记住这样的写发，三目运算符
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = x + y + carry;
			// if (sum >= 10) isCarryBit = true;
			// if (isCarryBit) sum += 1;
			carry = sum / 10;
			curNode.next = new ListNode(sum % 10);
			curNode = curNode.next;
			if (p != null) {
				p = p.next;
			}
			if (q != null) {
				q = q.next;
			}
		}

		if (carry > 0) {
			curNode.next = new ListNode(carry);
		}

		return fakeHeader.next;
	}
}