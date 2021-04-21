//package zep.leetcode.java.ds.bloomFilter;
//
//import com.google.common.base.Charsets;
//import com.google.common.hash.BloomFilter;
//import com.google.common.hash.Funnels;
//
///**
// * @author Zhao Peng
// * @version V1.0.0
// * @date 2019/12/7 0007
// * @description java
// */
//public class TestBloomFilter {
//
//  public static void main(String[] args) {
//
//    int total = 1_000_000;
//    BloomFilter<CharSequence> filter = BloomFilter
//        .create(Funnels.stringFunnel(Charsets.UTF_8), total);
//    for (int i = 0; i < total; i++) {
//        filter.put("" + i);
//    }
//
//    int count = 0 ;
//    for (int i = 0; i < total + 10_000; i++) {
//      if (filter.mightContain("" + i)){
//        count ++;
//      }
//    }
//
//    System.out.println("已匹配的数量 = " + count);
//  }
//}
