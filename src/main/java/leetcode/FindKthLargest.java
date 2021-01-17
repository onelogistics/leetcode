package leetcode;

/**
 * leetcode 215 求数组中第k大的数
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        //第K大的数字，需要转化为第preK小的数字。
        int prek=nums.length-k;
        int position=partition(nums,0,nums.length-1);
        //postion不是第prek小的数字时，进入循环
        while(position!=prek) {
            if(position>prek) {
                position=partition(nums,0,position-1);
            }else {
                position=partition(nums,position+1,nums.length-1);
            }
        }
        return nums[position];
    }
    //借鉴快排思想
    public int partition(int[] nums,int L,int R) {
        int pivot=nums[L];
        while(L<R) {
            //注意不要忘记在循环内部也要判断L<R
            while(L<R && nums[R]>=pivot) R--;
            nums[L]=nums[R];
            while(L<R && nums[L]<pivot) L++;
            nums[R]=nums[L];
        }
        nums[L]=pivot;
        return L;
    }
}
