package com.ai.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 求一个数组的排列组合
 * 如，输入1,2,3,输出这三个元素所有的排序组合可能性
 */
public class Permute {
    private  List<List<Integer>> lists;
    public List<List<Integer>> permute(int[] nums) {
        lists=new ArrayList<>();
        dfs(nums,0);
        return lists;
    }
    public void dfs(int[] nums,int index) {
        if(index==nums.length) {
            List<Integer> list=new ArrayList<>();
            for (int i=0;i<nums.length;i++) {
                list.add(nums[i]);
            }
            lists.add(list);
        }
        for (int i=index;i<nums.length;i++) {
            //分别将元素移到首位来
            swap(nums,index,i);
            dfs(nums,index+1);
            swap(nums,index,i);
        }
    }
    public void swap(int[] nums,int i,int j) {
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
 }
