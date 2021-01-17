package basic.sort;

import java.util.Arrays;

/**
 * 快排比其他两个O(NlogN)的算法（归并和堆）快的原因:
 * cpu cache局部性原理，快排每次比较的都是相邻的元素
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a={2,3,5,1,4,1,4,6};
        new QuickSort().quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * Time complexity:O(NlogN),最坏的结果是有序，O(N*N)
     * Space complexity:O(1)
     * stable:false
     * @param nums
     * @param L
     * @param R
     */
    public void quickSort(int[] nums,int L,int R) {
        if(L<R) {
            int position=partition(nums,L,R);
            quickSort(nums,L,position-1);
            quickSort(nums,position+1,R);
        }
    }

    /**单向扫描
     * 以高位做轴，从左到右遍历元素，当遇到元素小于等于轴的值时，将其交换到低位，
     * 遍历结束后可以确保i的左边都是不大于轴的值，此时交换i和值得位置，即完成了一次分组
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    public int partition(int[] nums,int lo,int hi) {
        int pivot=nums[hi];
        int i=lo;
        for (int j=lo;j<hi;j++) {
            if (nums[j] <=pivot) {
                if(i!=j)  swap(nums,i,j);
                i++;
            }
        }
        swap(nums,i,hi);
        return i;
    }

    /**
     * 双轴扫描，填坑式
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    public int partition2(int[] nums,int lo,int hi) {
        int pivot=nums[lo];
        while (lo<hi) {
            while (lo<hi && nums[hi]>pivot) hi--;
            nums[lo]=nums[hi];
            while (lo<hi && nums[lo]<=pivot) lo++;
            nums[hi]=nums[lo];
        }
        nums[lo]=pivot;
        return lo;
    }
    public void swap(int[] nums,int a,int b) {
        int tmp=nums[a];
        nums[a]=nums[b];
        nums[b]=tmp;
    }


}
