package common_problem;

import java.util.Arrays;

/**
 * @author JunjunYang
 * @date 2019/12/15 9:15
 */
public class ShopeeInterView {
    public static void main(String[] args) {
        int[] arr={2,1,2,2,0,0,1,2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 输入数组中的元素只可能是0，1,2，要求正序排序，空间复杂度O(1)，时间复杂度fastest
     * eg.
     * input:[0,1,2,0,1,2]
     * output:[0,0,1,1,2,2]
     * 基本思想：只有三种可能的值，用两个指针分别指向0的最后一个元素和2的第一个元素，然后再用一个指针遍历数组
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length == 1) return;
        int last0Ptr=-1;
        int first2Ptr=arr.length;
        for (int i=0;i<first2Ptr;i++) {
            switch (arr[i]) {
                case 0:
                    swap(arr,++last0Ptr,i);
                    break;
                case 1:
                    continue;
                case 2:
                    //因为是从左到右遍历，所以左边交换过来的一定是1，但右边交换过来的有可能是任何值，所以需要i--重新检查一次
                    swap(arr,--first2Ptr,i--);
                    break;
                default:
                    break;
            }
        }
    }
    public static void swap(int[] arr,int i,int j) {
        if(i==j) return;
        arr[i]=arr[i]+arr[j];
        arr[j]=arr[i]-arr[j];
        arr[i]=arr[i]-arr[j];
    }
}
