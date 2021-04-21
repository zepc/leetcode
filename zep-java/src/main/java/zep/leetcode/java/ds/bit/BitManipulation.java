package zep.leetcode.java.ds.bit;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @author Zep
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java.ds
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/13 15:02
 */
public class BitManipulation {

	// 异或的特性
	/*
	 * x ^ 0 = x;
	 * x ^ x = 0;
	 * x ^ 1111_1111 = ~x;
	 * x ^ (~x) = 1111_1111;
	 * a ^ b ^ c => a ^ b = b => b ^ c=a; 交换律
	 * a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c; 结合律
	 * */

	/*
	 * 构造特殊mask，将特殊位置放0或1
	 * 1. 将x最右边的n位清零 : x&(~0<<n)
	 * 2. 获取x的第n位值（0或者1）: (x>>n)&1
	 * 3. 获取x的第n位的幂值 : x&(1<<(n-1))
	 * 4. 仅将第n位置为1 : x|(1<<n)
	 * 5. 仅将第n位置为0 : x&(~(1<<n))
	 * 6. 将x最高位至第n位(含)清零 : x&((1<<n)-1)
	 * 7. 将第n位至第0位(含)清零 : x&(~((1<<(n+1))-1))
	 * */

	/*
	 * 具有特殊意义的&位操作运算
	 * 1. 判断是否是奇数 : x&1=1
	 * 2. 将最低为(LSB)的1清零 : x&=(x-1)
	 * 3. 得到最低位(LSB)的1 : x&-x
	 * 4. x & -x = 0
	 * */
}
