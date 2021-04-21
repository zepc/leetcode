package zep.leetcode.java;

/**
 * Created on 2020/10/08.
 *
 * @author Zhao Peng
 */
public class q_279_TotalSquare {

	private static int most = 1;
	private static int second = 1;

	public static void main(String[] args) {
		q_279_TotalSquare s = new q_279_TotalSquare();
		int result = s.numSquares(13);
		System.out.println(result);
	}


	public int numSquares(int n) {

		mostSquares(n);
		secondSquares(n);

		System.out.println("most = " + most);
		System.out.println("second = " + second);

		if (most >= second) {
			return second;
		}
		return most;
	}

	public int mostSquares(int n) {
		double sqrt = Math.sqrt(n);
		double floor = Math.floor(sqrt); // 下取整
		double ceil = Math.ceil(sqrt); // 上取整
		if (floor == ceil) {
			return 1;
		}

		if (floor == 1 || ceil == 1) {
			return n;
		}
		int minus = n - ((int) (floor * floor)); // 23 - 16 = 7

		int c = mostSquares(minus);

		most += c;

		return 1;
	}

	private boolean isFirst = true;

	public int secondSquares(int n) {
		double sqrt = Math.sqrt(n);
		double floor = Math.floor(sqrt); // 下取整
		double ceil = Math.ceil(sqrt); // 上取整
		if (floor == ceil) {
			return 1;
		}

		if (floor == 1 || ceil == 1) {
			return n;
		}

		int floor2 = ((int) (floor * floor));

		if (isFirst) {
			floor2 = ((int) ((floor - 1) * (floor - 1)));
			isFirst = false;
		}

		int minus = n - floor2;

		while (minus > floor2) {
			second++;
			minus = minus - floor2;
		}
		int c = secondSquares(minus);

		second += c;

		return 1;
	}
}
