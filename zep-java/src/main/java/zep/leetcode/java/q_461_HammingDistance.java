package zep.leetcode.java;


/**
 * Input: x = 1, y = 4
 * <p>
 * Output: 2
 * <p>
 * Explanation: 1   (0 0 0 1) 4   (0 1 0 0) ↑   ↑ 8   (1 0 0 0)
 * <p>
 * 20 = 0 0 0 1 0 1 0 0
 * <p>
 * 3  = 0 0 0 0 0 0 1 1
 * <p>
 * 0 1 0 1 = 5
 * <p>
 * 0 1 1 0 = 6
 * <p>
 * 6 / 2 = 3
 * <p>
 * 3 / 2 = 1 ... 1
 * <p>
 * The above arrows point to positions where the corresponding bits are different.
 */
public class q_461_HammingDistance {

	/**
	 * Input: x = 1, y = 4
	 * <p>
	 * Output: 2
	 * <p>
	 * Explanation:
	 * <p>
	 * 1   (0 0 0 1) 4   (0 1 0 0) ↑   ↑
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static int hammingDistance(int x, int y) {
		int c = x ^ y;
		// bitCount : 返回该数中bit位为1的个数
		return Integer.bitCount(c);
//        int count = 0;
//        while (c != 0){
//            if (c % 2 != 0) {
//                count++;
//            }
//            c = c >> 1;
//        }
//        return count;
	}

	public static void main(String[] args) {
		int i = hammingDistance(20, 3);
		System.out.println("i = " + i);
	}
}
