import java.util.TreeMap;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: PACKAGE_NAME
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/13 19:06
 */
public class Test {


	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap() {
			{
				put(1, "I");
				put(5, "V");
				put(10, "X");
				put(50, "L");
				put(100, "C");
				put(500, "D");
				put(1000, "M");
				put(4, "IV");
				put(9, "IX");
				put(40, "XL");
				put(90, "XC");
				put(400, "CD");
				put(900, "CM");
			}
		};

		System.out.println(map);

	}
}
