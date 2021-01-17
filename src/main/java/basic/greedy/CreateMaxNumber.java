package basic.greedy;

import java.util.Arrays;
import java.util.Stack;

/**leetcode321
 * 从一个数组中不改变元素顺序的选出len个元素，使他们顺序排列代表的十进制数最大
 * eg. [3,1,4,2] len=2 结果为42
 * eg. [3,1,4,2] len=3 结果为342
 */
public class CreateMaxNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CreateMaxNumber().maxArray(new int[]{3,1,4,2}, 3)));
    }

    /**
     * 关键
     * 栈中元素数量+剩余元素>len 并且 栈顶元素更小时， 可以循环弹出
     * @param nums
     * @param len
     * @return
     */
    private int[] maxArray(int[] nums, int len) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < nums.length; i++) {
            //满足弹出条件(剩余个数大于len，并且栈顶元素小于第i个元素)时，循环弹出
            while (stack.size() + nums.length - i > len && !stack.empty() && stack.peek() < nums[i]) {
                stack.pop();
            }
            //如果栈中元素个数小于len，放入此元素。
            if (stack.size() < len) {
                stack.push(nums[i]);
            }
        }
        int[] result = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}
