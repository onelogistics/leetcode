package leetcode;

/**
 * leetcode 8 string转int 且忽略空白符和字母
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 *
 * @author JunjunYang
 * @date 2020/1/17 9:23
 */
public class MyAtoi {
    public static void main(String[] args) {
        System.out.println(new MyAtoi().myAtoi("   -42"));
    }

    /**
     * 空格和符号位出现在数字前面才有意义
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        int i = 0;
        boolean negative = false;
        int limit = -Integer.MAX_VALUE;
        int sum = 0;
        //跳过空白符
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        //判断一次符号位
        if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            negative = str.charAt(i++) == '-';
        }
        //进入数字循环
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int val = Character.digit(str.charAt(i), 10);
            if (sum < limit / 10)
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            sum = sum * 10;
            if (sum < limit + val) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            sum -= val;
            i++;
        }
        return negative ? sum : -sum;
    }
}
