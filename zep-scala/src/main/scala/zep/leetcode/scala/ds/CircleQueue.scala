package zep.leetcode.scala.ds

/**
 * Created on 2021/02/27.
 *
 * @author Zhao Peng
 */
class CircleQueue(size: Int) {

  val array = new Array[Int](size)
  var head = 0
  var tail = 0

  def isFull = this.tail + 1 % this.size == this.head

  def isEmpty = this.tail == this.head

  def push(value: Int): Exception = {
    if (this.isFull) new Exception("queue full")
    this.array(this.tail) = value
    this.tail = (this.tail + 1) % this.size
    null
  }

  def pop(): Any = {
    if (this.isEmpty) new Exception("queue empty")
    val value = this.array(this.head)
    this.head = (this.head + 1) % this.size
    value
  }

}
