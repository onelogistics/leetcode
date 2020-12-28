package basic.QueueAndStack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * 32. Longest Valid Parentheses
 * 给定一个字符串，字符串中只有"("和")"两种字符，求有效括号的最大长度
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 */
public class LongestValidParentheses {
    /**
     * 解法1
     * The idea only update the result (max) when we find a "pair" and push -1 to stack first covered all edge cases.
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        //覆盖边缘情况，如()
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            //只有找到匹配的一对，才更新maxLen，其他情况下将不匹配的元素直接压栈，用来计算i-stack.peek()
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                maxLen = Math.max(maxLen, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return maxLen;
    }

    /**
     * 使用两个变量left和right，分别记录左括号和右括号的数量，遍历字符穿
     * 如果左右括号数量相等，则更新最大数量
     * 如果右括号数量大于左括号，如())，则重新开始计数
     * 如果左括号数量大于右括号，如(()，也重新开始计数
     * 注：此种解法需要两个循环
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) =='(') {
                ++left;
            }else {
                ++right;
            }
            if(left==right) maxLen=Math.max(maxLen,2*right);
            if(right>left) left=right=0;
        }
        left=right=0;
        for (int i=s.length()-1;i>=0;i--) {
            if(s.charAt(i) =='(') {
                ++left;
            }else {
                ++right;
            }
            if(left==right) maxLen=Math.max(maxLen,2*right);
            if(left>right) left=right=0;
        }
        return maxLen;
    }
}
