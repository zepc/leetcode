package zep.leetcode.scala

;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/19 0019
 * @description leetcode
 */
object q_09_IsPlalindrome {

	/**
	 * 取各个位的数
	 *
	 * @param x
	 * @return
	 */
	def isPalindrome(x: Int): Boolean = x match {
		case x if x < 0 => false
		case x if x >= 0 && x < 10 => true
		case _ => {
			val s = x.toString
			for (i <- 0 to s.length / 2) {
				if (s.charAt(i) != s.charAt(s.length - i - 1)) return false;
			}
			true

		}
		//	  if (x < 0) {
		//		  false
		//	  }
		//	  else if (x >= 0 && x < 10) {
		//		  true
		//	  }
		//	  else {
		//		  val arr = new ArrayBuffer[Int]()
		//		  var num = x
		//		  var flag = true
		//		  while (num != 0 && flag) {
		//			  val (a, b) = (num / 10, num % 10)
		//			  arr += b
		//			  if (a < 10) {
		//				  arr += a
		//				  flag = false
		//			  }
		//			  num /= 10
		//		  }
		//		  var result = true
		//		  for (idx <- arr.indices) {
		//			  val a = arr(idx)
		//			  val b = arr(arr.length - idx - 1)
		//			  if (a != b) result = false
		//		  }
		//		  result
		//	  }
	}

	/**
	 * 判断一半反转后和原数字没有反转的是否相等
	 *
	 * @param x
	 * @return
	 */
	def isPalindromeOfficial(x: Int): Boolean = {
		if (x < 0 || (x % 10 == 0 && x != 0)) return false // 负数或者10的倍数都不是回文数
		if (x >= 0 && x < 10) return true // 个位数都是回文数
		var number = x
		var reverseNumber = 0
		while (number > reverseNumber) {
			reverseNumber = reverseNumber * 10 + number % 10
			number /= 10
		}
		number == reverseNumber || number == reverseNumber / 10
	}

	def main(args: Array[String]): Unit = {
		println(s"result is ${isPalindrome(1221)}")
		println(s"result is ${isPalindromeOfficial(1)}")
	}
}
