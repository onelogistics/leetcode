package basic.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**leetcode 340
 * https://www.cnblogs.com/grandyang/p/5351347.html
 * 输入一个string数组和一个指定值K，找出最多有k个不同字符的最长子串
 *Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class LengthOfLongestSubstringKDistinct {

    static LengthOfLongestSubstringKDistinct main = new LengthOfLongestSubstringKDistinct();

    public static void main(String[] args) {
        System.out.println(main.solutionUseMapByIndex("eceba", 2));
    }
    /**
     * 基本思路：使用map存储每个字符及其出现的次数，使用指针left指向子串最左边的位置
     * 遍历字符串，分别将每个字符及其次数存储，同时判断map的元素数量是否大于k
     * 如果大于k，则从left开始滑动窗口，将map中left指向的字符出现次数减1之后，判断该字符出现次数是否为0
     * 如果等于0.说明此字符只出现了这一次，直接删除即可。left滑动向下一位
     * 如果不为0，说明此字符在之后还出现过，不能直接删除，次数减1即可，left滑动向下一位
     * 最终当map数量再次小于等于k时，left到i之间，又是一个新的子串
     * 以eceba，k=2为例
     * 第一阶段，map中的元素数量不超过2，则子串为ece
     * 第二阶段，map中加入b之后，元素数量超过了2，则从left=0开始遍历
     * 最终从map中删除掉了c，e的次数也变为了1，此时子串为eb
     * @param s
     * @param k
     * @return
     */
    public int solutionUseMapByTime(String s,int k){
        Map<Character,Integer> map = new HashMap<>();
        int left=0;
        int result=0;
        for (int i=0; i<s.length();i++) {
           increase(map,s.charAt(i));
           while (map.size()>2) {
               if(decrease(map,s.charAt(left))==0) {
                   map.remove(s.charAt(left));
               }
               left++;
           }
           result=Math.max(result,i-left+1);
        }
        return result;
    }

    /**
     * 解法2：遍历s，在map中存入每个元素和其最新的下标
     * 当map中的元素个数大于k时，从左边开始滑动窗口，如果当前元素在map中的映射值刚好等于下标
     * 则说明此元素已经是最新的一个了，所以直接删除即可，否则则说明右边还有相同字符串，需要跳过
     * @param s
     * @param k
     * @return
     */
    public int solutionUseMapByIndex(String s,int k) {
        Map<Character,Integer> map = new HashMap<>();
        int left=0;
        int result=0;
        for (int i=0;i<s.length();i++) {
            map.put(s.charAt(i),i);
            while (map.size()>k) {
                if(map.get(s.charAt(left))==left) {
                    map.remove(s.charAt(left));
                }
                left++;

            }
            result=Math.max(result,i-left+1);
        }
        return result;
    }


    /**
     * 难度升级，返回最长子串，而不仅仅是长度
     * @param s
     * @param k
     * @return
     */
    public String solution(String s,int k) {
        return null;
    }

    private int decrease(Map<Character,Integer> map,Character c) {
        int re=map.get(c)-1;
        map.put(c,re);
        return re;
    }
    private void increase(Map<Character,Integer> map,Character c) {
        if(map.containsKey(c)) {
            map.put(c,map.get(c)+1);
        }else {
            map.put(c,1);
        }
    }

}
