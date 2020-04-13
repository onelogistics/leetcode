package basic.greedy;

import java.util.Arrays;
import java.util.Stack;

/**
 * 从一个数组中不改变元素顺序的选出len个元素，使他们顺序排列代表的十进制数最大
 * eg. [3,1,4,2] len=2 结果为42
 */
public class CreateMaxNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CreateMaxNumber().maxArray(new int[]{3,1,4,2}, 2)));
    }
    private int[] maxArray(int[] nums, int len) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < nums.length; i++) {
            while (stack.size() + nums.length - i > len && !stack.empty() && stack.peek() < nums[i]) {
                stack.pop();
            }
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
