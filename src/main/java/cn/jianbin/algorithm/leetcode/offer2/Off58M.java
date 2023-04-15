package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author aaron.zou
 * @date 2023/4/15 2:55 PM
 * <p>
 * 剑指 Offer II 058. 日程表
 * 请实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 * <p>
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * <p>
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入:
 * ["MyCalendar","book","book","book"]
 * [[],[10,20],[15,25],[20,30]]
 * 输出: [null,true,false,true]
 * 解释:
 * MyCalendar myCalendar = new MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false ，第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了
 * MyCalendar.book(20, 30); // returns true ，第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
 * 0 <= start < end <= 109
 * <p>
 * <p>
 * 注意：本题与主站 729 题相同： https://leetcode-cn.com/problems/my-calendar-i/
 */
@UtilityClass
public class Off58M {

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();

        System.out.println(myCalendar.book(47, 50));
        System.out.println(myCalendar.book(33, 41));
        System.out.println(myCalendar.book(39, 45));
        System.out.println(myCalendar.book(33, 42));


    }

    public static class MyCalendar {
        private TreeSet<Meeting> meetings;

        public MyCalendar() {
            // 按照 会议开始时间从小 到大 排序；
            // 10 - 11   14 - 15

            meetings = new TreeSet<>(Comparator.comparingInt(m -> m.start));
        }

        public boolean book(int start, int end) {
            // 15 - 16
            // 14 - 15
            // 先构造一个不存在的会议；
            Meeting meeting = new Meeting();
            meeting.start = start;
            meeting.end = end;

            // 从已有会议中找出大于当前会议的；
            Meeting next = meetings.ceiling(meeting);
            if (next == null) {
                // next = null，说明 start 已经大于了 最后一个会议的开始时间了； 判断下
                // 是否大于 最后一个会议的结束时间即可；
                Meeting lastMeeting = meetings.isEmpty() ? null : meetings.last();

                if (lastMeeting == null || start >= lastMeeting.end) {
                    meetings.add(meeting);
                    return true;
                }

                return false;
            }

            // next 不为空，把前面一个会议也找出来；
            Meeting prev = meetings.lower(next);

            // prev 不为空.
            if ((prev == null && end <= next.start) || (prev != null && start >= prev.end && end <= next.start)) {
                // 前面一个也为空。直接判断 end <= next.start
                meetings.add(meeting);
                return true;
            }

            return false;
        }


        public static class Meeting {
            private Integer start;

            private Integer end;
        }
    }
}
