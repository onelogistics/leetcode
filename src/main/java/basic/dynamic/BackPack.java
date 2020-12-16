package basic.dynamic;

import java.util.Arrays;

/**
 * 背包问题
 * 背包问题大的可以分为三类，它们的共性是需要注意动态数组的初始化和大小，需要初始化为new int[w.length+1][K+1]
 * 这是因为把第0件物品也算进去了。
 * 1. 01背包问题，要求一个物品要么使用一次，要么不用，需要注意的一点是，如果要求背包必须装满，那么在初始化动态数组时，除了dp[0][0],dp[0][i]都
 * 需要被初始化为Integer.MIN_VALUE.
 * 2. 完全背包问题，不限制物品使用次数，一个物品可以使用多次，相比01背包的区别是，在最内层加一个使用次数k的循环，使用k*w[i-1]<=j作为终止条件
 * 3. 多重背包问题，每个物品都有自己的限制使用次数m[i],相比完全背包，多了使用次数m[i]的限制
 *
 * 参考博客：https://www.cnblogs.com/mfrank/p/10587463.html
 *
 * @author JunjunYang
 * @date 2020/2/7 13:25
 */
public class BackPack {
    public static void main(String[] args) {
        /**
         * 有一个容量为 K的背包，和一些物品。这些物品分别有两个属性，体积 w 和价值 v，每种物品只有一个。要求用这个背包装下价值尽可能多的物品，求该最大价值，背包可以不被装满。
         * 不要求背包装满，则最优解是装入第一件物品和第三件物品
         * 最后sumW=6,sumV=11
         */
        print(backPack01(new int[]{2, 5, 4}, new int[]{3, 2, 8}, 7), 11);
        print(backPack01WithSPaceOptimized(new int[]{2, 5, 4}, new int[]{3, 2, 8}, 7), 11);
        /**
         * 要求背包必须装满，则最优解是装入第一件物品和第二件物品
         * 最后sumW=7，sumV=5
         */
        print(backPack01Var1(new int[]{2, 5, 4}, new int[]{3, 2, 8}, 7), 5);
        /**
         * 完全背包问题：放入两次第一件物品
         */
        print(backPackComplete(new int[]{5, 7}, new int[]{5, 8}, 10), 10);
        print(backPackCompleteWithSpaceOptimized(new int[]{5, 7}, new int[]{5, 8}, 10), 10);
        /**
         * 多重背包问题：一个物品最多可使用m[i]次
         */
        print(backPackLimitNum(new int[]{4, 3, 2}, new int[]{3, 4, 5}, new int[]{2, 3, 4}, 15), 11);
        print(backPackLimitNumWithSpaceOptimized(new int[]{4, 3, 2}, new int[]{3, 4, 5}, new int[]{2, 3, 4}, 15), 11);
    }

    private static void print(int actual, int expect) {
        System.out.println(String.format("actual:%d, expect:%d", actual, expect));
    }

    /**
     * 01背包问题
     * 有一个容量为 K的背包，和一些物品。这些物品分别有两个属性，体积 w 和价值 v，每种物品只有一个。要求用这个背包装下价值尽可能多的物品，求该最大价值，背包可以不被装满。
     * 找子问题：子问题有两个变量，物品本身（能否装下），物品体积（能装下时要不要装，是否会比相同体积不装此物品价值更大）。因此子问题可以确定为在背包容量为j时，前i个物品所能达到的最大价值。
     * 设第i个物品的体积为w，价值为v
     * 则，j<w时，即背包放不下第i个物品时：dp[i,j]=dp[i-1][j]，等于前i-1个物品在当前体积下所能达到的最大价值;
     * j>=w时，即背包装得下第i个物品时，dp[i,j]=max(dp[i-1][j-w[i]]+v , dp[i-1][j]) 放入该物品的价值和不放入该物品的价值之间的最大值
     * dp[0][i]=0，在前0个物品时最大价值始终为0
     *
     * @param w 物品的体积数组
     * @param v 物品的价值数组
     * @param K 背包的价值
     * @return
     */
    public static int backPack01(int[] w, int[] v, int K) {
        //dp[0][i]=0,预留了第0个物品的位置，所以长度要+1；
        int[][] dp = new int[w.length + 1][K + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 0; j <= K; j++) {
                //此处w[i-1]才是对应第i个物品的体积，数组v同理
                if (j < w[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - w[i - 1]] + v[i - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[w.length][K];
    }
    /**
     * 变种1：要求背包恰好装满，此时需要把dp[0][i]，i从1到k的值全赋值为MIN_VALUE,这是因为要求恰好装满时，只有dp[0][0]有解，解为0，而其他的dp[0][i]均得不到解，均处于无解状态，因此赋值为无穷小
     */
    public static int backPack01Var1(int[] w, int[] v, int K) {
        int[][] dp = new int[w.length + 1][K + 1];
        for (int i = 1; i <= K; i++) dp[0][i] = Integer.MIN_VALUE;
        for (int i = 1; i <= w.length; i++) {
            for (int j = 0; j <= K; j++) {
                //此处w[i-1]才是对应第i个物品的体积，数组v同理
                if (j < w[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - w[i - 1]] + v[i - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[w.length][K];
    }
    /**
     * 完全背包问题：相较于01背包问题，每个物品可以重复使用多次
     *将多次使用的同一个物品看做一个整体
     * @param w
     * @param v
     * @param K
     * @return
     */
    public static int backPackComplete(int[] w, int[] v, int K) {
        int[][] dp = new int[w.length + 1][K + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= K; j++) {
                //k代表当前物品的使用次数，限制范围是[0,j/w[i-1]]
                for (int k = 0; k * w[i - 1] <= j; k++) {
                    //状态转移方程
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i - 1]] + k * v[i - 1]);
                }
            }
        }
        return dp[w.length][K];
    }

    /**
     * 多重背包问题，相较于01背包，每个物品可以使用m[i]次
     * 解题思路和完全背包非常类似，只是多了一个限制条件
     *
     * @param m
     * @param w
     * @param v
     * @param K
     * @return
     */
    public static int backPackLimitNum(int[] m, int[] w, int[] v, int K) {
        int[][] dp = new int[w.length + 1][K + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= K; j++) {
                for (int k = 0; k <= m[i - 1] && w[i - 1] * k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i - 1]] + k * v[i - 1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[w.length][K];
    }

    /**
     * 优化1：优化空间复杂度到O(K)
     * 分析上基础的动态规划解法可以发现，dp[i][j]的值只和上一行相关，我们可以利用这个特性
     * 分析出一维数组的状态转移方程 dp[j]=max(dp[j-w[i]+v[i],dp[j]]),注意要倒序遍历赋值，因为一维数组的值是不断覆盖复用的，而dp[j]的值又和上一轮遍历时数组中0到j范围内的值有关系，所以从前往后遍历时有可能会出现数值被修改的问题，导致之后的dp无法获取到正确的值。
     */
    public static int backPack01WithSPaceOptimized(int[] w, int[] v, int K) {
        int[] dp = new int[K + 1];
        for (int i = 0; i < w.length; i++) {
            for (int j = K; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j - w[i]] + v[i], dp[j]);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[K];
    }

    /**
     * 完全背包问题的空间优化
     * https://www.cnblogs.com/mfrank/p/10803417.html
     *
     * @param w
     * @param v
     * @param K
     * @return
     */
    public static int backPackCompleteWithSpaceOptimized(int[] w, int[] v, int K) {
        int[] dp = new int[K + 1];
        for (int i = 0; i < w.length; i++) {
            for (int j = w[i]; j <= K; j++) {
                //因为完全背包中同一个物品可以重复使用，因此从前往后遍历，即可获得一个物品多次重复使用的效果
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[K];
    }

    /**
     * 多重背包空间复杂度优化
     * @param m
     * @param w
     * @param v
     * @param K
     * @return
     */
    public static int backPackLimitNumWithSpaceOptimized(int[] m, int[] w, int[] v, int K) {
        int[] dp = new int[K + 1];
        for (int i = 0; i < w.length; i++) {
            //次数*当前物品的体积已经超出了背包容积K，此时多重背包问题简化成了完全背包
            if (m[i] * w[i] >= K) {
                for (int j = w[i]; j <= K; j++) {
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            } else {
                //不能简化为完全背包问题，则需要从0~k之间挑出最大价值的背包方法
                for (int j = w[i]; j <= K; j++) {
                    //todo 此处条件需要设为k<m[i]且w[i]*k<j,而不是小于等于，为什么？？？
                    for (int k = 1; k < m[i] && w[i] * k < j; k++) {
                        dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[K];
    }


}
