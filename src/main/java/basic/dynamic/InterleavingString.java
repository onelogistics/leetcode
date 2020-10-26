package basic.dynamic;

/**
 * 97. Interleaving String
 * 给定字符串s1、s2、s3，判断s3是否是s1和s2交织而成的结果
 * 两个字符串的交织被定义为：他们被切割成非空的子串，如下：
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
 */
public class InterleavingString {
    public static void main(String[] args) {
        InterleavingString interleavingString=new InterleavingString();
        interleavingString.isInterleave("aabcc","dbbca","aadbbcbcac");
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        //todo 行不通
        int i=0,j=0;
        for (char ch : s3.toCharArray()) {
            if(i<s1.length() && s1.charAt(i) == ch) {
                i++;
            }else if(j<s2.length() && s2.charAt(j) == ch) {
                j++;
            }else {
                return false;
            }
        }
        return true;
    }
}
