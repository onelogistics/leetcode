package com.ai.algorithm.QueueAndStack;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int lo=0;
        int hi=nums.length-1;
        int preK=nums.length-k;
        while(lo<hi) {
            int j=partition(nums,lo,hi);
            if(j>preK) {
                hi=j-1;
            }else if(j<preK) {
                lo=j+1;
            }else{
                break;
            }
        }
        return nums[preK];
    }
    //借鉴快排思想
    public int partition(int[] nums,int lo,int hi) {
        int pivot=nums[hi];
        int i=lo;
        for( int j=lo;  j<hi; j++) {
            //保证i左边的数据都小于pivot
            if(nums[j]<=pivot) {
                swap(nums,i,j);
                i++;
            }
        }
        swap(nums,hi,i);
        return i;
    }
    public void swap(int[] nums,int a,int b) {
        int tmp=nums[a];
        nums[a]=nums[b];
        nums[b]=tmp;
    }
}
