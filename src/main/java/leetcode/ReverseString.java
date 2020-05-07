package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**反转字符串，也可考虑用递归实现
 * @author JunjunYang
 * @date 2019/12/18 11:54
 */
public class ReverseString {
    @Test
    public void  test() {
        Assert.assertEquals("dcba",reverseString("abcd"));
    }
    public String reverseString(String s) {
        char[] arr=s.toCharArray();
        reverseString(arr);
        return new String(arr);
    }
    public void reverseString(char[] s) {
        int left=0,right=s.length-1;
        while (left<right) {
            char temp=s[left];
            s[left++]=s[right];
            s[right--]=temp;
        }
    }
}
