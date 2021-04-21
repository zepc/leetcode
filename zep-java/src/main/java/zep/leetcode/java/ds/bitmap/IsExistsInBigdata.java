package zep.leetcode.java.ds.bitmap;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IsExistsInBigdata {

	// 题目：我有40亿个整数，再给一个新的整数，我需要判断新的整数是否在40亿个整数中，你会怎么做？

	private static final Random random = new Random();
	private static final Set<Integer> checkSet = new HashSet<>();

	public static void main(String[] args) {

		// 思考: 一个整数4个字节, 40亿个整数是160亿个字节, 也就是大约16G
		//      如果机器的内存2G, 那么如果使用Set的话, 则需要加载数据8次
		//      磁盘加载数据的速度是非常慢的, 通常比内存的慢上百倍

		// 如果有多台机器, 可以使用分布式的方法, 每个机器都查找, 然后进行汇总就ok了

		// 最优解:
		//  判断一个整数是否存在只有两个状态, 0和1
		//  一个32位int类型包含2^32个位, 也就是42亿
		//  如果我们申请2^32位, 也就是2^29B, 也就是大概500M的数据, 就可以判断某个数是否在40亿个数中.
		//  (位置元素交换)
		BitSet bitSet = StoreData();
		int data = random.nextInt(100);
		boolean b = bitSet.get(data);
		System.out.println("result = " + b);
		System.out.println(" .." + checkSet.contains(data));
	}

	private static BitSet StoreData() {
		BitSet bs = new BitSet(100);
		for (int i = 0; i < 100; i++) {
			int value = random.nextInt(100);
			bs.set(value, true);
			checkSet.add(value);
		}
		System.out.println("size = " + checkSet.size());
		return bs;
	}
}
