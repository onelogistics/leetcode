package leetcode;

/**
 * @author JunjunYang
 * @date 2019/12/27 9:09
 */
public class ParseInt {
    public static void main(String[] args) {
        String[] s = {"123", "-12345", "5847"};
        for (String str : s) {
            System.out.println(Integer.parseInt(str));
            System.out.println(parseInt(str, 10));
        }
    }

    /**
     * 自行实现parseInt方法
     *
     * @param str
     * @param radix
     * @return
     */
    public static int parseInt(String str, int radix) {
        if (str == null) {
            throw new NullPointerException();
        }
        //验证基数是否合法
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix is less than " + Character.MIN_RADIX);
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix is larger than " + Character.MIN_RADIX);
        }
        int i = 0;
        int len = str.length();
        //此处之所以使用负数作为Limit，是想将范围溢出统一起来，否则正数需要比较最大值，负数需要比较最小值
        int limit = -Integer.MAX_VALUE;
        int digit;
        boolean negative = false;
        int result = 0;
        if (len > 0) {
            char firstChar = str.charAt(0);
            //验证首位是否合法
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    throw new NumberFormatException();
                }
                i++;
            }
            int multiMin = limit / radix;
            while (i < len) {
//                digit=str.charAt(i++)-48;
                digit = Character.digit(str.charAt(i++), radix);
                if (digit < 0) {
                    throw new NumberFormatException();
                }
                //验证result *= radix是否会溢出
                if (result < multiMin) {
                    throw new NumberFormatException();
                }
                result *= radix;
                //验证result -= digit是否会溢出
                if (result < limit + digit) {
                    throw new NumberFormatException();
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException();
        }
        return negative ? result : -result;
    }
}
