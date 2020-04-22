package basic.dynamic;

/**
 * 最长公共子串，与子序列类似，不过子序列不要求连续，子串要求连续
 */
public class LongestCommonSubString {
    public int solution(String text1, String text2) {
        int[][] ans = new int[text1.length() + 1][text2.length() + 1];
        //记录过程中出现的最大值
        int maxLen = 0;
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    ans[i + 1][j + 1] = ans[i][j] + 1;
                    maxLen = Math.max(maxLen, ans[i + 1][j + 1]);
                }
            }
        }
        return maxLen;
    }
}
