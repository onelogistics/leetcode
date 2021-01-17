package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JunjunYang
 * @date 2020/2/4 13:29
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(stoneGameVII(new int[]{7,90,5,1,100,10,10,2}));
    }
    public static int stoneGameVII(int[] stones) {
        int AliceScore = 0;
        int BobScore = 0;
        int left = 0;
        int right = stones.length-1;
        boolean FirstRace = true;
        int sum=0;
        for(int i=0;i<stones.length;i++) {
            sum+=stones[i];
        }
        while(left+1 < right) {
            if(FirstRace) {
                if(stones[left]< stones[right]) {
                    sum=sum-stones[left];
                    AliceScore+=sum;
                    left++;
                }else {
                    sum=sum-stones[right];
                    AliceScore+=sum;
                    right--;
                }
            }else {
                if(stones[left]< stones[right]) {
                    sum=sum-stones[right];
                    BobScore+=sum;
                    right--;
                }else {
                    sum=sum-stones[left];
                    BobScore+=sum;
                    left++;
                }
            }
            FirstRace = !FirstRace;
        }
        if(right == left+1) {
            if(FirstRace) {
                AliceScore += Math.max(stones[left],stones[right]);
            }else {
                BobScore += Math.max(stones[left],stones[right]);
            }
        }
        return AliceScore-BobScore;

    }
}
