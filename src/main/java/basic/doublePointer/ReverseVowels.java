package basic.doublePointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * leetcode345 翻转元音
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * <p>
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 * <p>
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !vowelSet.contains(s.charAt(i))) i++;
            while (i < j && !vowelSet.contains(s.charAt(j))) j--;
            if (i < j) {
                char temp = s.charAt(i);
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }
}
