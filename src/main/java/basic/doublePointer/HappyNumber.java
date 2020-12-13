package basic.doublePointer;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy Number
 * https://leetcode.com/problems/happy-number/
 * 快乐数，给定一个正整数
 * 遍历正整数的每一位数字，将其平方和累加得到新的整数，不断重复这个过程，如果最终得到的正整数为1，那么
 * 就说这个数是个快乐数，如果最终陷入了无限循环，则认为这个数字不是快乐数
 * Example:
 * <p>
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 02 = 1
 */
public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber happyNumber=new HappyNumber();
        happyNumber.isHappy(19);
    }
    /**
     * 这道题有一种通用解法，将历史出现过的数字存在set中，每次得到新数之后判断是否出现过，如果出现过且不为1的话，就return false。
     * 另一种做法则是借鉴快慢指针的做法。
     * 这个题目中一定是存在循环的。
     * 如果是快乐数，则最终的循环数就是1，1经过一轮将其平方和累加得到的又是1，所以最终的循环数是1.
     * 而如果不是快乐数，则肯定会在某个非1的数中出现循环
     * 基于此，我们使用快慢指针来解答，如果快慢指针相等，则判断此时相遇节点的数值即可
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slow=n,fast=n;
        do {
            slow = findNextValue(slow);
            fast = findNextValue(findNextValue(fast));
        } while (slow != fast);
        return fast==1;
    }
    private int findNextValue(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;
    }
}
