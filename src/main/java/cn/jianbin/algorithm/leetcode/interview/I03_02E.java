package cn.jianbin.algorithm.leetcode.interview;

/**
 * @author jianbin.
 * @date 2023/9/12 21:59
 *
 * 面试题 03.02. 栈的最小值
 * 提示
 * 简单
 * 95
 * 相关企业
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
 *
 *
 * 示例：
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class I03_02E {

    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push(5);
        stack.push(2);
        stack.push(-1);
        stack.push(0);
        stack.push(3);

        System.out.println(stack.top());
        System.out.println(stack.getMin());

        stack.pop();
        stack.pop();
        stack.pop();


        System.out.println(stack.top());
        System.out.println(stack.getMin());

        stack.push(9);
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }

    public static class Stack {
        private int[] stack;
        private int size;
        private int[] minStack;

        private int topPtr = -1;

        public Stack(int size) {
            this.size = size;
            stack = new int[size];
            minStack = new int[size];
        }

        public void push(int x) {
            // 入栈；
            if (topPtr == size - 1) {
                // 满了；
                throw new IllegalArgumentException("栈已经满了");
            }

            // 5 2 0 1 3
            topPtr++;
            stack[topPtr] = x;
            // 最小值用当前栈的 最小值放置； 比较栈顶元素 跟当前元素哪个最大；
            minStack[topPtr] = (topPtr == 0 ? x : Math.min(minStack[topPtr - 1], x));
        }

        public void pop() {
            // 出栈；
            if (topPtr < 0) {
                throw new IllegalArgumentException("栈顶没有元素；");
            }

            topPtr--;
        }

        public int top() {
            // 返回栈顶元素；
            if (topPtr < 0) {
                throw new IllegalArgumentException("栈顶没有元素；");
            }

            return stack[topPtr];
        }

        public int getMin() {
            return minStack[topPtr];
        }
    }
}
