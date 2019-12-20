package com.ai.leetcode;

/**反转字符串，也可考虑用递归实现
 * @author JunjunYang
 * @date 2019/12/18 11:54
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left=0,right=s.length-1;
        while (left<right) {
            char temp=s[left];
            s[left++]=s[right];
            s[right--]=temp;
        }
    }
}
