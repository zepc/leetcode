package zep.leetcode.java.ds.欧几里得算法;

/**
 * 最大公约数（q > p） ： 1。如果p为0，则最大公约数为q 2. 如果p不为0，设r为q除以p之后得到的余数，则q与p的最大公约数为p与r的最大公约数
 */
public class GCD {

	protected static int gcd(int q, int p) {
		if (p == 0) {
			return q;
		}
		int r = q % p;
		return gcd(p, r);
	}

}
