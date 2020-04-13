package basic.greedy;

/**
 * leetcode 134
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 注意！！！如果题目有解，该答案即为唯一答案。则说明我们只要找到一个可能的起点即可
 示例1
 输入:
 gas  = [1,2,3,4,5]
 cost = [3,4,5,1,2]
 输出: 3
 解释:
 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 因此，3 可为起始索引。

 示例 2:
 输入: 
 gas  = [2,3,4]
 cost = [3,4,3]
 输出: -1
 解释:
 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 因此，无论怎样，你都不可能绕环路行驶一周。
 */
public class GasStation {
    public static void main(String[] args) {
        System.out.println(new GasStation().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    /**
     *
     * Time Complexity:O（N）
     * 1、总油量如果小于总消耗量，则不存在解
     * 2、假设从A点无法到达B点（B点是第一个无法到达的点），那么A和B之间的点肯定也无法到达B点，因为少加了A点的油
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //起始点
        int start=0;
        //不断累加最后剩余的油量
        int total=0;
        //油箱中的油量，当油量减空时，会重新从0开始
        int tank=0;
        for (int i = 0; i <gas.length ; i++) {
            total+=gas[i]-cost[i];
            tank+=gas[i]-cost[i];
            //如果油箱中没有足够的油了，从下一个点出发，油箱清空
            if(tank<0) {
                start=i+1;
                tank=0;
            }
        }
        //如果最后剩余油量大于等于0，则存在解，否则返回-1
        return total>=0?start:-1;
    }
}
