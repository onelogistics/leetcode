package com.ai.algorithm.greedy;

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
        int start=0;int total=0;int tank=0;
        for (int i = 0; i <gas.length ; i++) {
            if((tank+=gas[i]-cost[i])<0) {
                total+=tank;
                tank=0;
                start=i+1;
            }
        }
        //total+tank,补上最后汽车的油量
        return (total+tank)>=0?start:-1;
    }
    /**
     * 暴力遍历,依次尝试每一个加油站
     * Time Complexity:O(n*n)
     * @param gas
     * @param cost
     * @return
     */
    /*public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            int tank = gas[i];
            int count = 0;
            int j=i;
            while (true) {
                tank -= cost[j];
                if (tank < 0) break;
                int next = j == gas.length - 1 ? 0 : j + 1;
                tank += gas[next];
                count++;
                j=next;
                if (count == gas.length) return i;
            }
        }
        return -1;
    }*/
}
