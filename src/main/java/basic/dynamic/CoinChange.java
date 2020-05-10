package basic.dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 零钱兑换问题
 * 给定一组硬币数，找出一组最少的硬币数，来找换零钱N。
 * <p>
 * 比如，可用来找零的硬币为: 1、3、4   待找的钱数为 6。
 */
public class CoinChange {
    @Test
    public void test() {
        Assert.assertEquals(2,charge(new int[]{1, 3,4}, 6));
        Assert.assertEquals(3,charge(new int[]{1, 2, 5}, 11));
        Assert.assertEquals(3,charge2(new int[]{1, 2, 5}, 4));
    }

    /**leetcode322
     * 找零所需的硬币数
     * 用两个面值为3的硬币找零，最少硬币数为2。而不是 4，1，1
     * @param coinsValues 可用来找零的硬币 coinsValues.length是硬币的种类
     * @param n           待找的零钱
     * @return 最少硬币数目
     */
    public static int charge(int[] coinsValues, int amount) {
        //dp[i]钱数为i时的找零方案
        int[] dp = new int[amount + 1];
        //初始化临界值,最小的硬币是1，所以 amount 最多需要 amount 个硬币，amount+1 也就相当于当前的最大值了，注意这里不能用整型最大值来初始化，因为在后面的状态转移方程有加1的操作，有可能会溢出
        for (int i = 1; i < dp.length; i++) dp[i] = amount + 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coinsValues.length; j++) {
                if (coinsValues[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coinsValues[j]] + 1);
                }
            }
        }
        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    /**leetcode518
     * 有多少种找零方法
     * https://www.cnblogs.com/grandyang/p/7669088.html
     * @param coinsValues
     * @param amount
     * @return
     */
    public static int charge2(int[] coinsValues, int amount) {
        //dp[i][j],用前i种硬币找金额为j的钱，有多少种找零方法
        int[][] dp=new int[coinsValues.length+1][amount+1];
        dp[0][0]=1;
        //基本思路，一类一类硬币往上累加，每累加一枚硬币，就从头开始遍历到amount，更新此时的动态数组。
        //这样一类硬币就可能使用到多次
        for (int i=1;i<coinsValues.length+1;i++) {
            dp[i][0]=1;
            for (int j=1;j<amount+1;j++) {
                //不使用当前此类硬币的找零数+去掉当前硬币面值一次的找零数
                dp[i][j]=dp[i-1][j]+(j>=coinsValues[i-1]?dp[i][j-coinsValues[i-1]]:0);
            }
        }
        return dp[coinsValues.length][amount];
    }

}
