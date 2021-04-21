package zep.leetcode.java;

import java.util.HashMap;

public class q_760_FindAnagramMappings {


	/*
	 * A = [12, 28, 46, 32, 50]
	 * B = [50, 12, 32, 46, 28]
	 *
	 * A[0] = B[1]
	 * A[1] = B[4]
	 * A[2] = B[3]
	 * A[3] = B[2]
	 * A[4] = B[0]
	 *
	 * P = [1, 4, 3, 2, 0]
	 * */
	public static int[] anagramMappings(int[] A, int[] B) {

		int len = A.length;
		int[] p = new int[len];

		for (int i = 0; i < len; i++) {
			int a = A[i];
			for (int j = 0; j < len; j++) {
				int b = B[j];
				if (a == b) {
					p[i] = j;
				}
			}
		}

		return p;
	}

	public static int[] anagramMappings2(int[] A, int[] B) {
		HashMap<Integer, Integer> map = new HashMap();
		int len = A.length;
		int[] P = new int[len];
		for (int i = 0; i < len; i++) {
			map.put(B[i], i);
		}
		int t = 0;
		for (int x : A) {
			P[t++] = map.get(x);
		}
//        for (int i = 0; i < len; i++) {
//            Integer value = map.get(A[i]);
//            P[i] = value;
//        }
		return P;
	}

	public static void main(String[] args) {
		int[] a = {12, 28, 46, 32, 50};
		int[] b = {50, 12, 32, 46, 28};

		int[] result = anagramMappings2(a, b);
		for (int i : result) {
			System.out.println("i = " + i);
		}
	}
}
