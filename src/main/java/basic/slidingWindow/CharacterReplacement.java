package basic.slidingWindow;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意:
 * 字符串长度 和 k 不会超过 104。
 *
 * 示例 1:
 *
 * 输入:
 * s = "ABAB", k = 2
 *
 * 输出:
 * 4
 *
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 *
 * 输入:
 * s = "AABABBA", k = 1
 *
 * 输出:
 * 4
 *
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class CharacterReplacement {
    public int solution(String s, int k) {
        if(s == null || s.isEmpty()) return 0;
        char[] chars=s.toCharArray();
        int[] hash=new int[26];
        int left=0, count=0, result=0;
        for (int right=0;right<chars.length;right++) {
            hash[chars[right]-'A']++;
            //count记录的是同一个字符最大出现的次数
            count = Math.max(count,hash[chars[right]-'A']);
            //滑动窗口的总长度减去某个字符（X）的最大次数大于k，则说明此滑动窗口内非X的字符过多，需要移动左指针
            while (right-left+1-count>k) {
                hash[chars[left]-'A']--;
                left++;
            }
            result=Math.max(result,right-left+1);
        }
        return result;
    }
}
