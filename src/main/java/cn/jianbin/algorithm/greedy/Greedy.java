package cn.jianbin.algorithm.greedy;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.jianbin.algorithm.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author aaron.zou
 * @date 2022/4/8 9:38 下午
 */
@UtilityClass
public class Greedy {
    public static void main(String[] args) {
        ArrayList<Meeting> meetings = ListUtil.toList(new Meeting(0, 10),
                new Meeting(8, 20),
                new Meeting(10, 30),
                new Meeting(10, 15),
                new Meeting(15, 20),
                new Meeting(30, 35),
                new Meeting(36, 40));

        System.out.println("------------------------------------------------------");
        System.out.println("暴力计算最多能排多少场会议: " + mostMeeting(meetings, 0, 0));
        System.out.println("贪心算法: " + mostMeetingByGreedy(meetings));


        System.out.println("------------------------------------------------------");
        String str = ".x...xx..x......x.";
        System.out.println(str);
        System.out.println("暴力计算最少需要多少灯照亮所有字符串: " + leastLantern(str.toCharArray(), 0, new HashSet<>()));
        System.out.println("贪心算法: " + leastLanternByGreedy(str.toCharArray()));

        System.out.println("------------------------------------------------------");
        int[] target = new int[]{40, 30, 15, 10};
        Utils.printArr(target);
        System.out.println("贪心算法求解最少需要的切分成本铜块: " + lessMoney(target));



        System.out.println("------------------------------------------------------");
        Project[] projects = new Project[]{
                new Project("A", 1, 11),
                new Project("B", 10, 10),
                new Project("C", 2, 1),
                new Project("D", 50, 20),
                new Project("E", 1, 5),
                new Project("F", 20, 50),
                new Project("G", 5, 10)
        };

        System.out.println(ListUtil.toList(projects));
        System.out.println("暴力枚举求解最终生产总值最大: " + maxProduct(projects, 10, 1, 3));
        System.out.println("贪心算法求解最终生产总值最大: " + maxProductByGreedy(projects, 10, 3));

    }

    /**
     * 会议有 开始时间、结束时间；
     * 给定指定个会议，求最多可以安排多少会议？
     * <p>
     * 0-10
     * 8 - 20
     * 10-30
     * 10 - 15
     * 15 - 20
     * 30 - 35
     * 36 - 40
     * <p>
     * 这个例子最多能排两场;
     *
     * @param meetings    当前剩余会议
     * @param done        已经安排了多少场会议
     * @param currentTime 当前到了哪场会议了
     */
    public int mostMeeting(List<Meeting> meetings, int done, int currentTime) {
        if (meetings.size() == 0) {
            // 说明没有会议要排了
            return done;
        }

        // 还有会议要排
        int ret = done;
        for (Meeting meeting : meetings) {

            ArrayList<Meeting> newMeetings = new ArrayList<>(meetings);
            newMeetings.remove(meeting);

            if (meeting.start >= currentTime) {
                // 能排这个会议
                ret = Math.max(ret, mostMeeting(newMeetings, done + 1, meeting.end));
            }
        }


        return ret;
    }

    /**
     * 贪心算法，直接从结束时间最小贪心
     */
    public int mostMeetingByGreedy(List<Meeting> meetings) {
        // 按照结束时间排序
        meetings.sort(Comparator.comparingInt(c -> c.end));

        int ret = 0;
        int currentTime = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= currentTime) {
                currentTime = meeting.end;
                ret++;
            }
        }

        return ret;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meeting {
        int start;
        int end;
    }

    /**
     * 贪心2： 一个字符串中只有  "."  和  "X"，
     * . 表示灯笼位置， X 表示墙；   .X...XX..X
     * 一个灯笼可以照亮周边的两个   "."
     * <p>
     * 问题：最少需要多少灯笼才能照亮所有  "."
     *
     * @param chars      chars[0...used] 已经选择好了放的灯
     *                   chars[used+1...] 还可以选择放灯还是不放灯的坑位
     * @param lightsIdxs [0...used] 具体选择的是哪些位置放的灯
     */
    public int leastLantern(char[] chars, int used, Set<Integer> lightsIdxs) {
        if (used == chars.length) {
            // 已经选择的位置到了最后了； 可以直接返回
            // 检查下是否已经能够照亮所有位置
            for (int i = 0; i < chars.length; i++) {
                char pos = chars[i];
                // 验证是否所有 . 都被照亮了
                if (pos == '.') {
                    // 要么当前亮了、要么前面一个、要么后面一个亮了
                    if (!lightsIdxs.contains(i)
                            && !lightsIdxs.contains(i - 1)
                            && !lightsIdxs.contains(i + 1)) {
                        // -1 表示这种方式没有照亮所有地方，不能用这个解
                        return -1;
                    }
                }
            }

            // 照亮了所有地方了； 返回所有灯的 size
            return lightsIdxs.size();
        }

        int ret = Integer.MAX_VALUE;
        char pos = chars[used];
        // 让当前位置选择放灯还是不放灯
        if (pos == '.') {
            // 选择放灯
            lightsIdxs.add(used);
            int curRet = leastLantern(chars, used + 1, lightsIdxs);
            ret = curRet == -1 ? ret : Math.min(ret, curRet);
            // 恢复现场
            lightsIdxs.remove(used);

            // 选择不放灯
            curRet = leastLantern(chars, used + 1, lightsIdxs);
            ret = curRet == -1 ? ret : Math.min(ret, curRet);
        } else {
            // 当前位置是 'X'，没得选，只能不放灯
            int curRet = leastLantern(chars, used + 1, lightsIdxs);
            ret = curRet == -1 ? ret : Math.min(ret, curRet);
        }


        return ret;
    }

    /**
     * 贪心求解；  永远只关注当前步骤；
     * 假设当前位置 是   i；
     *    则：
     *       i = X；  没得选，不能放灯，跳到下一步去做决定
     *
     *
     *       i = .   i为坑位，看下一个能不能放灯
     *
     *
     *
     * @param chars
     * @return
     */
    public int leastLanternByGreedy(char[] chars) {
        int ret = 0;
        int idx = 0;

        while (idx < chars.length) {
            char pos = chars[idx];

            if (pos == 'x') {
                // 当前是 墙，没法放灯，直接跳到下一个；
                idx++;
            } else {
                // 当前位置是坑位； 不管三七二十一；直接先放一个灯
                ret++;

                if (idx == chars.length - 1) {
                    // 已经到了最后一个位置了；
                    break;
                }

                // 还没到最后一个位置
                if (chars[idx + 1] == '.') {
                    idx = idx + 3;
                } else {
                    idx = idx + 2;
                }
            }
        }

        return ret;
    }

    /**
     * 切金条；  每次可以切一刀；
     *
     * 切出来后要使用 切后相同的铜块；
     *
     * 问：给定指定的目标金条长度，花费的铜块最少；
     *
     * 40 30 20 10
     *
     * 金条长度 = 60；
     *
     */
    public int lessMoney(int[] target) {
        // 构造小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i : target) {
            priorityQueue.add(i);
        }

        int sum = 0;
        while (priorityQueue.size() > 1) {
            // 弹出两个
            int cur = priorityQueue.poll() + priorityQueue.poll();
            sum += cur;
            priorityQueue.add(cur);
        }

        return sum;
    }

    public int maxProduct(Project[] projects, int W, int C, int Y) {
        if (projects.length == 0 || C > Y) {
            // 如果当前已经没有剩余 项目 或者 已经选择的项目次数到了，直接返回总 生产
            return W;
        }

        // 还没到
        int ret = W;
        for (int i = 0; i < projects.length; i++) {
            Project project = projects[i];
            // 按顺序选一个
            if (W >= project.cost) {
                // 可以选中
                List<Project> newProjects = ListUtil.toList(projects);
                newProjects.remove(project);
                int total = maxProduct(ArrayUtil.toArray(newProjects, Project.class), W + project.profit, C + 1, Y);

                ret = Math.max(total, ret);
            }
        }

        return ret;
    }

    /**
     * 最大生产值问题： 一个项目由 成本和利润组成；   k  v
     * 给定 N 个项目，初始资金 x，要求投资项目  y 个；  怎么最大化生产总值
     *
     * 贪心策略： 先贪 成本最小的。 然后在成本最小中的选择 利润 + 成本 最大的
     */
    public int maxProductByGreedy(Project[] projects, int x, int y) {
        // 准备两个堆； 一个按照 成本 cost 小根堆
        // 一个按照 总值 大根堆
        PriorityQueue<Project> costMinQueue = new PriorityQueue<>(Comparator.comparingInt(c -> c.cost));

        PriorityQueue<Project> totalMaxQueue = new PriorityQueue<>((p1, p2) -> {
            return p2.profit - p1.profit;
        });

        costMinQueue.addAll(Arrays.asList(projects));
        System.out.println(costMinQueue);

        for (int i = 0; i < y; i++) {
            while (!costMinQueue.isEmpty() && costMinQueue.peek().cost <= x) {
                totalMaxQueue.add(costMinQueue.poll());
            }

            if (totalMaxQueue.isEmpty()) {
                break;
            }

            Project poll = totalMaxQueue.poll();
            System.out.println("choose : " + poll);
            x += poll.profit;
        }


        return x;
    }

    @AllArgsConstructor
    @ToString
    static class Project {
        String name;
        int cost;
        int profit;
    }
}
