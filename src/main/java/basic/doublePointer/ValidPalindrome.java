package basic.doublePointer;

/**
 * 有效回文2
 * leetcode 680 Valid Palindrome II
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome {
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        //循环直到不满足回文条件为止
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        //最多可以删除一个字母，那就尝试删除左边的字母或右边的字母，只要有一个满足条件，就返回true
        return isTrue(s, i + 1, j) || isTrue(s, i, j - 1);
    }

    private boolean isTrue(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
