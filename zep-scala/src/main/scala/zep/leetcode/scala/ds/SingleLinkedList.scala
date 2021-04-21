package zep.leetcode.scala.ds

/**
 * Created on 2021/02/28.
 *
 * @author Zhao Peng
 */
case class ListNode[T](index: Int = 0, var next: ListNode[T] = null, name: T, nickName: T)

class SingleLinkedList[T] {

	var head: ListNode[T] = _

	def insert(node: ListNode[T]) = {
		if (head == null) {
			head = node
		};
		else {
			var q = head
			while (q.next != null) {
				q = q.next
			}
			q.next = node
		}
	}
}

object Test {

	def main(args: Array[String]): Unit = {
	}
}

