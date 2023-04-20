package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/20 8:54 PM
 *
 * 剑指 Offer II 074. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 *
 * 注意：本题与主站 56 题相同： https://leetcode-cn.com/problems/merge-intervals/
 */
@UtilityClass
public class Off74M {

    public static void main(String[] args) {
        int[][] matrix = Utils.gen2DArr(4, 3);
        Utils.print2DArr(matrix);
        Arrays.sort(matrix, (v1, v2) -> {
            int c1 = v1[0];
            int c2 = v2[0];

            return c1 - c2;
        });

        System.out.println();
        Utils.print2DArr(matrix);
    }

    public int[][] solution(int[][] matrix) {
        Arrays.sort(matrix, (v1, v2) -> {
            int c1 = v1[0];
            int c2 = v2[0];

            // 根据每一行的第一列排序；（只有两列）
            int r1 = c1 - c2;
            if (r1 == 0) {
                // 在第一个值相等的情况下，比较第二个值.
                return v1[1] - v2[1];
            }
            return r1;
        });

        List<int[]> merged = new ArrayList<>();

        // 遍历每一行.
        for (int i = 0; i < matrix.length; i++) {
            // 拿出这一行的 2 个值.
            int start = matrix[i][0];
            int end = matrix[i][1];

            // 跟 merged 中的最后一个比较一下.
            if (merged.isEmpty()) {
                merged.add(new int[]{start, end});
            } else {
                int[] last = merged.get(merged.size() - 1);
                // 检查下 start 是否 <= last.end
                if (start <= last[1]) {
                    // 直接替换. 替换的时候 还要检查  谁的 end 大一点;
                    last[1] = Math.max(end, last[1]);
                } else {
                    // 否则；插入；
                    merged.add(new int[] {start, end});
                }
            }
        }

        int[][] ret = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            ret[i] = merged.get(i);
        }

        return ret;
    }
}
