package cn.jianbin.alogthrim.datastructure;

/**
 * @author aaron.zou
 * @date 2022/1/14 7:52 下午
 */
public class BitMap {
    private long[] data;
    private long max;

    public BitMap(int max) {
        this.max = max;

        // =  (max + 64) / 64 = (max / 64) + 1
        // max = 0 - 63    => 1
        // max = 64 - 127  => 2
        // max = 128 - 191 => 3
        // max = 192 - 255 => 4
        data = new long[(max + 64) >> 6];
    }

    /**
     *  添加一个数
     */
    public void add(int num) {
        // 1. 先找到这个数在数组中的哪个 idx 位置
        //  num / 64 即可
        // 2. 在找到这个数在数组idx位的第几位
        // num % 64 即可
        // 比如 64 在 arr[1] 的第0位上

        // 3. 再将该位置为 1
        // 用 |

        data[num >> 6] |= (1L << (num & 63));
    }

    /**
     *  判断一个数是否存在
     */
    public boolean contains(int num) {
        // 1. 先找到在数组中的位置
        // 2. 找到该数属于 第几位
        // 3. 判断该位置的 位 是否 = 1

        return (data[num >> 6] & (1L << (num & 63))) != 0L;
    }

    public void remove(int num) {
        // 1. 先找到在数组中的位置
        // 2. 找到 第几位
        // 3. 将该位 置 0
        data[num >> 6] &= ~(1L << (num & 63));
    }
}
