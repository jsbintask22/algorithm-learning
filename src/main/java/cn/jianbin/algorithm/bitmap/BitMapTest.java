package cn.jianbin.algorithm.bitmap;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.alogthrim.datastructure.BitMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BitMapTest {
    public static void main(String[] args) {
        int a = 1318112412;
        System.out.println(a % 256);
        System.out.println(a & 255);

        BitMap b1 = new BitMap(127);
        b1.add(127);
        System.out.println(b1.contains(127));

        System.out.println("----------------------------------");
        int max = RandomUtil.randomInt(63, 111113011);
        System.out.println("max = " + max + ", bit mapsize : " + ((max / 64) + 1));

        BitMap bitMap = new BitMap(max);
        for (int i = 0; i < 10; i++) {
            int val = RandomUtil.randomInt(0, max);
            System.out.print("val : " + val);
            System.out.print(" contains: " + bitMap.contains(val));
            bitMap.add(val);
            System.out.print(" added contains: " + bitMap.contains(val));
            bitMap.remove(val);
            System.out.print(" removed contains: " + bitMap.contains(val));
            System.out.println();
        }
    }
}
