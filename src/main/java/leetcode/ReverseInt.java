package leetcode;

/**反转整数
 * 内存溢出时返回0
 * Integer范围
 * +2147483647
 * -2147483648
 * @author JunjunYang
 * @date 2019/12/18 12:02
 */
public class ReverseInt {

    public  int reverse(int x) {
        int sum = 0;
        while (x != 0) {
            int mod = x % 10;
            x /= 10;
            if (sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && mod > 7)) return 0;
            if (sum < Integer.MIN_VALUE / 10 || (sum == Integer.MIN_VALUE / 10 && mod < -8)) return 0;
            sum = sum * 10 + mod;
        }
        return sum;
    }
}
