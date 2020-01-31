package com.ai.algorithm.dynamic;

/**
 * leetcode121
 * Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * @author JunjunYang
 * @date 2020/1/27 13:32
 */
public class MaxProfit {
    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /**
     * 买卖股票
     * 最多进行一次买卖
     *
     * @param prices 每天的股票价格列表
     * @return 买卖一次的最大利润，扩展：返回哪天买入哪天卖出
     */
    public int maxProfit(int[] prices) {
        //最终结果，最终结果对应的左右下标
        int L = 0, R = 0, ans = 0;
        //局部最大值,局部最大值对应的左右下标
        int localMax = Integer.MIN_VALUE, localL = 0, localR = 0;
        for (int i = 1; i < prices.length; i++) {
            //如果之前累加的局部最大值小于0，则从当前节点开始重新累加
            if (localMax < 0) {
                localMax = prices[i] - prices[i - 1];
                localL = i - 1;
                localR = i;
            } else {
                localMax = localMax + prices[i] - prices[i - 1];
                localR = i;
            }
            //取各个局部最大值中的最大值
            if (localMax > ans) {
                ans = localMax;
                L = localL;
                R = localR;
            }
        }
        System.out.println(String.format("left index:%d, right index:%d, max profit:%d", L, R, ans));
        return ans;
    }

}
