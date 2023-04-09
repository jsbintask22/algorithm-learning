package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jianbin.
 * @date 2023/4/9 10:41
 *
 * 剑指 Offer II 035. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 *
 *
 * 示例 1：
 *
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 *
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *
 *
 * 提示：
 *
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 *
 *
 * 注意：本题与主站 539 题相同： https://leetcode-cn.com/problems/minimum-time-difference/
 */
@UtilityClass
public class Off35M {

    public static void main(String[] args) {
        List<String> points = new ArrayList<>();

        points.add("00:00");
        points.add("23:59");
        //points.add("00:00");
        System.out.println(solution(points));
    }

    public int solution(List<String> timePoints) {
        if (timePoints == null) {
            return 0;
        }

        if (timePoints.size() == 1) {
            return diff("00:00", timePoints.get(0));
        }
        // 解法：排序再找出两两 最小的；

        Collections.sort(timePoints);

        int len = timePoints.size();

        int i = 0;
        int j = i + 1;

        // 排序以后 最小值只会出现在 两两之间 或者 首 尾（考虑24h）
        String[] t1 = timePoints.get(0).split(":");
        String[] t2 = timePoints.get(len - 1).split(":");
        int min = (24 - Integer.parseInt(t2[0])) * 60 - Integer.parseInt(t2[1])
                + Integer.parseInt(t1[0]) * 60 + Integer.parseInt(t1[1]);

        while (j < len) {
            min = Math.min(min, diff(timePoints.get(i), timePoints.get(j)));
            i++;
            j++;
        }

        return min;
    }

    private int diff(String s1, String s2) {
        //  ["00:00","23:59","00:00"]
        //  10:30  11:20

        String[] t1 = s1.split(":");
        String[] t2 = s2.split(":");


        return (Integer.parseInt(t2[0]) - Integer.parseInt(t1[0])) * 60 + (Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]));
    }
}
