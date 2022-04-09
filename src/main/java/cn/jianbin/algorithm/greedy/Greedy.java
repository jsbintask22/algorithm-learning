package cn.jianbin.algorithm.greedy;

import cn.hutool.core.collection.ListUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

    }

    /**
     * 会议有 开始时间、结束时间；
     * 给定指定个会议，求最多可以安排多少会议？
     *
     *  0-10
     *   8 - 20
     *      10-30
     *      10 - 15
     *      15 - 20
     *      30 - 35
     *      36 - 40
     *
     *   这个例子最多能排两场;
     *
     * @param meetings 当前剩余会议
     * @param done 已经安排了多少场会议
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
     *  贪心2：
     */
}
