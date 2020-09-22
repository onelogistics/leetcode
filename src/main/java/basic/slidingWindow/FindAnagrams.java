package basic.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 寻找指定字符串的同位异构词
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length()<p.length()) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        //26个字符，存储p中每个字符出现的次数
        int[] hash=new int[26];
        for (int i=0;i<p.length();i++) {
            hash[p.charAt(i)-'a']++;
        }
        //count为计数器，累加s中出现p内相同字符的个数
        int left=0,count=0;
        for (int right=0;right<s.length();right++) {
            //当前字符指向的次数减一，如果减完后仍然大于等于0，则说明此字符同时在s和p中出现过，则count++
            if(--hash[s.charAt(right)-'a']>=0) {
                count++;
            }
            //如果滑动窗口的大小超出了p的长度，移动左指针，保证滑动窗口的长度和p的长度一致
            if(right-left+1>p.length()) {
                //left指代的字符，需要次数加一进行回滚，如果回滚后的结果大于0，说明此字符是同时在s和p中出现过的，所以需要把count也回滚掉
                if(++hash[s.charAt(left)-'a']>0) {
                    count--;
                }
                left++;
            }
            //如果count的长度等于p.length，代表找到了一个同位异构词
            if(count==p.length()) {
                result.add(left);
            }
        }
        return result;
    }
}
