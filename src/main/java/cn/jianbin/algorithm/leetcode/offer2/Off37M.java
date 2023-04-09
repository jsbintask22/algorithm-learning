package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author jianbin.
 * @date 2023/4/9 13:21
 *
 * 剑指 Offer II 037. 小行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 *
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 *
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * 示例 4：
 *
 * 输入：asteroids = [-2,-1,1,2]
 * 输出：[-2,-1,1,2]
 * 解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 *
 *
 * 提示：
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 *
 * 注意：本题与主站 735 题相同： https://leetcode-cn.com/problems/asteroid-collision/
 */
@UtilityClass
public class Off37M {

    public static void main(String[] args) {
        Utils.printArr(solution2(Utils.arr("5,10,-5")));

        Utils.printArr(solution(Utils.arr("-2,1,1,-1")));

        Utils.printArr(solution(Utils.arr("-2,-2,1,-1")));

        Utils.printArr(solution(Utils.arr("1,-1,1,-2")));


        Utils.printArr(solution(Utils.arr("-2,-1,1,2")));

        int[] arr = Utils.arr("10,2,-5");
        Utils.printArr(arr);

        Utils.printArr(solution(arr));
    }

    public int[] solution2(int[] asteroids) {
        // 方法2：基于 栈 去模拟碰撞过程；
        // 如果当前 cur < 0(左移） 才有可能发生碰撞，将cur 之前的 行星 放到栈中
        // 比较 栈顶和当前 cur
        LinkedList<Integer> stack = new LinkedList<>();

        for (int cur : asteroids) {
            boolean alive = true;

            while (alive && !stack.isEmpty() && cur < 0 && stack.peek() > 0) {
                // 当前行星 存活并且 栈顶有元素并且 当前 当前左移动 且栈顶右移动；
                alive = cur < -stack.peek();

                if (cur <= -stack.peek()) {
                    // 当前 大于 栈顶； 栈顶元素干掉；
                    stack.pop();
                }
            }

            // 经过了上面一轮操作以后，如果当前行星还活着，加到栈中
            if (alive) {
                stack.push(cur);
            }
        }

        int[] ret = new int[stack.size()];
        int n = ret.length - 1;

        while (n >= 0) {
            ret[n--] = stack.pop();
        }

        return ret;
    }

        public int[] solution(int[] asteroids) {
        // 解法：两个指针 left 和 right 分别从 头和尾开始找， left走有右边 移动的，
        // right 找往左边移动的；   left当前为 + 且 left_next 为 - 则停下比较；
        // right当前为 - 且 right_next 为 + 则停下比较；

        int left = 0;
        int right = left + 1;
        int len = asteroids.length;
        int ruined = 0;

        Map<Integer, Integer> exits = new HashMap<>();

        while (right < len) {

            while (right < len && (asteroids[left] < 0 || (asteroids[left] > 0 && asteroids[right] > 0))
                    ) {

                // 表示当前节点前一个不 为 0 的节点在哪里；
                if (asteroids[left] > 0 && left != right) {
                    exits.put(right, left);
                }
                // left 和 right 同号； 继续向前移动；
                left = right;
                right += 1;
            }

            if (right < len) {
                // 走到这里，说明 left 和 right 异号了；
                int leftVal = Math.abs(asteroids[left]);
                int rightVal = Math.abs(asteroids[right]);

                if (leftVal == rightVal) {
                    // 相等，两个都干掉；
                    asteroids[left] = 0;
                    asteroids[right] = 0;
                    ruined += 2;

                    left = exits.getOrDefault(left, right + 1);
                    right = right + 1;
                } else if (leftVal > rightVal) {
                    // 左边大于右边； 干掉右边吧；
                    asteroids[right] = 0;
                    ruined++;
                    right++;
                } else {
                    // 左边小于右边；干掉左边；
                    asteroids[left] = 0;
                    left = exits.getOrDefault(left, right);
                    ruined++;
                }
            }
        }

        int[] ret = new int[len - ruined];
        int i = 0;
        for (int asteroid : asteroids) {
            if (asteroid != 0) {
                ret[i++] = asteroid;
            }
        }

        return ret;
    }
}
