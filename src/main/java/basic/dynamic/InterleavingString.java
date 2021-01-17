package basic.dynamic;

import java.util.Arrays;

/**https://leetcode.com/problems/interleaving-string/
 * 97. Interleaving String
 * 给定字符串s1、s2、s3，判断s3是否是s1和s2交织而成的结果
 * 两个字符串的交织被定义为：他们被切割成非空的子串，如下：
 * s1、t1不一定代表一个字符，可能是多个字符
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Example 3:
 *
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 *
 * Constraints:
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lower-case English letters.
 * 注意：用双指针法并不能找出所有可能符合条件的组合
 */
public class InterleavingString {
    public static void main(String[] args) {
        InterleavingString interleavingString=new InterleavingString();
        System.out.println(interleavingString.isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(interleavingString.isInterleave2("aabcc", "dbbca", "aadbbbaccc"));
    }


    /**
     * 暴力解法
     * 本题是个最优解问题，求是否存在s3是s1和s2交织之后的结果
     * 利用递归，遍历找到所有可能的交织结果，然后与s3进行比较
     * 时间复杂度 O(2的(m+n)次方)
     * 空间复杂度 O(m+n)
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave1(String s1, String s2, String s3) {
        return isInterleave1(s1,0,s2,0,s3,"");
    }
    private boolean isInterleave1(String s1, int i, String s2, int j, String s3, String ans) {
        //找到符合条件的交织组合
        if(ans.equals(s3) && i==s1.length() && j==s2.length()) {
            return true;
        }
        boolean res = false;
        //在数组范围内时，继续寻找下一层，是否有符合条件的交织组合
        if(i<s1.length()) {
            res |= isInterleave1(s1,i+1,s2,j,s3,ans+s1.charAt(i));
        }
        if(j<s2.length()) {
            res |= isInterleave1(s1,i,s2,j+1,s3,ans+s2.charAt(j));
        }
        return res;
    }

    /**
     * 记忆数组解法 DFS+cache
     * 上一种暴力破解法，穷举了所有可能的交织组合，然后判断是否有等于参数给出的S3，
     * 其实在递归过程中，只要我们计算过(s1,i)和(s2,j)的组合是否可以交织出s3的后半部分
     * 那么在之后的组合过程中，我们就不必再去计算(s1,i)和(s2,j)的交织结果，直接拿来用即可
     * 因此创建一个二维数组用来保存中间结果，memo[i][j]代表(s1,i)和(s2,j)是否可以交织成s3的后半部分
     * 时间复杂度 O(m*n)，每个memo[i][j]只用计算一次
     * 空间复杂度 O(m*n)
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        //memo[i][j]的值代表s1从i以后的元素，和s2从j以后的元素，是否可以交织成s3的剩余部分
        //1代表可以，-1代表不可以
        int[][] memo = new int[s1.length()][s2.length()];
        boolean ans = isInterleave2(s1,0,s2,0,s3,0,memo);
        System.out.println(Arrays.deepToString(memo));
        return ans;
    }
    private boolean isInterleave2(String s1, int i, String s2, int j, String s3,int k,int[][] memo) {
        if(i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if(j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if(memo[i][j] != 0) {
            System.out.println(String.format("s1=%s,i=%d,s2=%s,j=%d,s3=%s,k=%d",s1,i,s2,j,s3,k));
            return memo[i][j] == 1;
        }
        boolean ans=false;
        if(s1.charAt(i) == s3.charAt(k) && isInterleave2(s1,i+1,s2,j,s3,k+1,memo)
        || s2.charAt(j) == s3.charAt(k) && isInterleave2(s1,i,s2,j+1,s3,k+1,memo)) {
            ans =true;
        }
        memo[i][j] = ans ? 1 : -1;
        return ans;
    }

    /**
     * 动态规划二维数组解法
     * 从第二种解法：记忆数组法可以看出，只要我们确定前i+1个元素是交织而成的，且第i个元素等于s1、s2中的任一元素，则可以
     * 递推出前i个元素都是交织成的
     * 创建二维数组dy[i][j]=new boolean[s1.length+1][s2.length+1]，dy[i][j]代表s1的前i个元素和s2的前j个元素的结果
     * 我们很容易求出递推方程
     * //第j个元素，下标为j-1
     * dy[i][j]=(dy[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1)) || (dy[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        boolean[][] dy=new boolean[s1.length()+1][s2.length()+1];
        dy[0][0] = true;
        for (int i=0;i<=s1.length();i++) {
            for (int j=0;j<=s2.length();j++) {
                if(i>0) {
                    dy[i][j] = dy[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                }
                if(j>0) {
                    dy[i][j] |= dy[i][j-1] &&  s2.charAt(j-1) ==s3.charAt(i+j-1);
                }
            }
        }
        return dy[s1.length()][s2.length()];
    }

    /**
     * 一维数组解法
     * dy[i][j]是否为true来源于正上方dy[i-1][j]和左边dy[i][j-1]的递推
     * 我们只使用一个数组，滚动计算dy[i][j]的值
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave4(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        boolean[] dy=new boolean[s2.length()+1];
        dy[0]=true;
        for (int i=0;i<=s1.length();i++) {
            for (int j=0;j<=s2.length();j++) {
                if(i>0) {
                    //此处的dy[j]为上一轮的值，即dy[i-1][j]
                    dy[j] = dy[j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                }
                if(j>0) {
                    dy[j] |= dy[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
                }
            }
        }
        return dy[s2.length()];
    }
}
