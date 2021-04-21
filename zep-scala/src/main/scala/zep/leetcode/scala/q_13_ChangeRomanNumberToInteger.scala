package zep.leetcode.scala

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/20 0020
 * @description leetcode
 */
object q_13_ChangeRomanNumberToInteger {

  def romanToInt(s: String): Int = {
    val romantags = Map(
      'I' -> 1,
      'V' -> 5,
      'X' -> 10,
      'L' -> 50,
      'C' -> 100,
      'D' -> 500,
      'M' -> 1000
      )
    var is = false;
    var xs = false;
    var cs = false
    var tag = 0
    var number = 0
    var sum = 0
    for (c <- s) {
      tag += 1
      number = romantags(c)
      if (c == 'I' || is) {
        is = true
        if (is && (c == 'V' || c == 'X')) {
          number = romantags(c) - 1 - 1
          is = false
        }
      }
      if (c == 'X' || xs) {
        xs = true
        if (xs && (c == 'L' || c == 'C')) {
          number = romantags(c) - 10 - 10
          xs = false
        }
      }
      if (c == 'C' || cs) {
        cs = true
        if (cs && (c == 'D' || c == 'M')) {
          number = romantags(c) - 100 - 100
          cs = false
        }
      }
      sum = sum + number
    }
    sum
  }

  /**
   * 将所有的情况都包含在Map种，遍历s，判断当前字符和下一个字符的组合是否在Map中
   *
   * @param s
   * @return
   */
  def romanToIntOfficial(s: String): Int = {
    val romantags = Map(
      "I" -> 1,
      "V" -> 5,
      "X" -> 10,
      "L" -> 50,
      "C" -> 100,
      "D" -> 500,
      "M" -> 1000,
      "IV" -> 4,
      "IX" -> 9,
      "XL" -> 40,
      "XC" -> 90,
      "CD" -> 90,
      "CM" -> 90,
      )

    var sum = 0
    var idx = 0
    while (idx < s.length) {
      if (idx + 1 < s.length) {
        val c1 = s(idx).toString
        val c2 = s(idx + 1).toString
        val c = c1 + c2
        if (romantags.contains(c)) {
          sum += romantags(c)
          idx += 1
        }
        else {
          sum += romantags(c1)
        }
        idx += 1
      }
    }
    sum
  }


  def main(args: Array[String]): Unit = {
    println(s"result is ${romanToInt("III")}")
    println(s"result is ${romanToIntOfficial("III")}")
  }
}
