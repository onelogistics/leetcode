package com.ai.algorithm.greedy;

import java.util.Arrays;
import java.util.Stack;

public class CreateMaxNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CreateMaxNumber().maxArray(new int[]{1, 2, 3, 4}, 2)));
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
