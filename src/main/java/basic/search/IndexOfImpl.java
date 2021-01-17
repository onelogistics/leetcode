package basic.search;

/**
 * todo KMP算法不熟练，需要临时背下来（https://www.zhihu.com/question/21923021）
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 * 实现类似{@link String#indexOf(String)}
 * leetcode 28
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * @author JunjunYang
 * @date 2020/1/13 20:00
 */
public class IndexOfImpl {
    public static void main(String[] args) {
        System.out.println(new IndexOfImpl().solution("mississippi", "issipp"));
        System.out.println(new IndexOfImpl().solutionKMP("mississippi", "issipp"));
        System.out.println(new IndexOfImpl().getNext("abababca"));
    }

    /**
     * 时间复杂度O(M*N)
     * 暴力搜索法
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int solution(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        /**
         * 最大需要比较的边界
         */
        int max = haystack.length() - needle.length();
        if (max < 0) {
            return -1;
        }
//        for (int i = 0; i < haystack.length(); i++) {
        for (int i = 0; i <= max; i++) {
            int cur = i;
            int j = 0;
            for (; j < needle.length() && cur < haystack.length(); j++) {
                if (haystack.charAt(cur++) != needle.charAt(j)) {
                    break;
                }
            }
            //匹配到needle字符串尾
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 代码简洁
     *
     * @param haystack
     * @param needle
     */
    public int solution2(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                //匹配到了最后一个字符则说明匹配成功
                if (j == needle.length()) return i;
                //i+j等于haystack的长度说明haystack剩下的长度已经不足以和needle对比
                if (i + j == haystack.length()) return -1;
                //i是fromIndex，i+j代表本轮要参与对比的字符
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    /**
     * KMP解法
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int solutionKMP(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int i = 0, j = 0;
        int[] next = getNext(needle);
        while (i < haystack.length() && j < needle.length()) {
            //j==-1说明上一次是在j=0处匹配失败的，则本次j++重新定位到j=0处开始比较，i++定位到下一索引开始比较
            //其余情况直接从断掉的匹配索引位开始比较
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                //只需要从next[j]处来开始比较
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - j;
        }
        return -1;
    }

    /**
     * 获取next数组
     * next数组其实是PMP(partial match table)部分匹配表的一种变形
     * 获取next数组的过程中也可以看做是一次字符串匹配，从当前字符串后缀中找到匹配前缀的字符串
     *
     * @param str
     * @return
     */
    public int[] getNext(String str) {
        int[] next = new int[str.length()];
        next[0] = -1;
        //i指向后缀字符串
        //j指向前缀字符串
        int i = 0, j = -1;
        while (i < str.length()-1) {
            if (j == -1 || str.charAt(i) == str.charAt(j)) {
                ++i;
                ++j;
                //++j即代表匹配到的最大前缀长度，++i代表将上一索引位的PMT放在了当前位置上，因为kmp算法回退的位置和上一索引位的pmt相关
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
