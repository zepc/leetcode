package zep.leetcode.java;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 13:53
 */
public class q_779_KthSymbolInGrammer {

	public int kthGrammar(int N, int K) {
		if (N == 1) {
			return 0;
		}
		if (N == 2 && K == 1) {
			return 0;
		}
		if (N == 2 && K == 2) {
			return 1;
		}
		int c = ((int) Math.pow(2, N - 2));
		if (K <= c) {
			return kthGrammar(N - 1, K);
		}
		else {
			return 1 ^ kthGrammar(N, K - c);
		}
	}

	public int solution_01(int N, int K) {
		if (N == 1) {
			return 0;
		}
		return (~K & 1) ^ kthGrammar(N - 1, (K + 1) / 2);
	}

	public int solution_02(int N, int K) {
		if (N == 1) {
			return 0;
		}
		if (K <= 1 << N - 2) {
			return kthGrammar(N - 1, K);
		}
		return kthGrammar(N - 1, K - (1 << N - 2)) ^ 1;
	}

	public int solution_03(int N, int K) {
		return Integer.bitCount(K - 1) % 2;
	}


	public static void main(String[] args) {
		q_779_KthSymbolInGrammer test = new q_779_KthSymbolInGrammer();
		System.out.println(test.kthGrammar(4, 5));
	}

}
