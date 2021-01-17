package basic.dynamic;

/**
 * 时间复杂度O(n*n)
 * 最长回文字符串 leetcode 5
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 */
public class LongestPalindrome {
    static LongestPalindrome main=new LongestPalindrome();
    //起始位置
    int lo;
    //回文长度
    int maxLen;

    public static void main(String[] args) {
        System.out.println(main.solution("cbbd"));
    }

    /**时间复杂度O(N2)
     * 动态规划解法
     * dp[i][j]代表s[i]到s[j]的子字符串是否是回文字符串
     * dp[i][j] == dp[i+1][j-1] && s.charAr(i)==s.charAt(j)
     *
     * @param s
     * @return
     */
    public String dynamicPlan(String s) {
        String ans="";
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i=s.length()-1;i>=0;i--) {
            for (int j=i;j<s.length();j++) {
                //j-i<3且s.charAt(i)==s.charAt(j)时也可说明i到j是回文字符串
                dp[i][j]=s.charAt(i)==s.charAt(j) && (j-i<3 || dp[i+1][j-1]);
                if(dp[i][j] && j-i+1 > ans.length()) {
                    ans=s.substring(i,j+1);
                }
            }
        }
        return ans;
    }
    /**
     * 时间复杂度O(N*N)
     * @param s
     * @return
     */
    public String solution(String s) {
        lo = 0;
        maxLen = 0;
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            extendPalindrome(s, i, i);//尝试以自身为中心扩大
            extendPalindrome(s, i, i + 1);//尝试以i和i+1中间作为中心扩大
        }
        return s.substring(lo, lo + maxLen);
    }

    /**
     * 以j和k作为中心，尝试扩大回文范围
     *
     * @param s
     * @param j
     * @param k
     */
    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        //(k-1)-(j+1)+1
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

}
