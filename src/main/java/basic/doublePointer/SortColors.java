package basic.doublePointer;

import java.util.Arrays;

/**leetcode 75
 * 输入数组中的元素只可能是0，1,2，要求正序排序，空间复杂度O(1)，时间复杂度fastest
 * @author JunjunYang
 * @date 2019/12/15 9:15
 */
public class SortColors {
    public static void main(String[] args) {
        int[] arr={2,1,2,2,0,0,1,2};
//        sort(arr);
        sortColors2(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sortColors2(int[] nums) {
        int second=nums.length-1;
        int zero=0;
        for (int i=zero;i<=second;i++) {
            //不能调换两个while的顺序，因为是从左到右遍历，假设第一个while交换完之后，num[i]=0，正好可以用第二个while来处理，第二个while交换归来的num[i]不是0就是1
            //但如果调换顺序，第二个while交换过来的nums[i]=0,就无法处理了，直接被跳过了。
            while (nums[i]==2 && i<second) swap(nums,i,second--);
            while (nums[i]==0 && i>zero) swap(nums,i,zero++);
        }
    }
    /**
     * 输入数组中的元素只可能是0，1,2，要求正序排序，空间复杂度O(1)，时间复杂度fastest
     * eg.
     * input:[0,1,2,0,1,2]
     * output:[0,0,1,1,2,2]
     * 基本思想：用两个指针和一个迭代器
     * 之所以nums[i]==2的时候，i不加1，是因为我们不清楚从end交换过来的是啥值，所以还需要判断一次
     * 而nums[i]=0时，因为start指向的都是我们已经确定的0或者1，所以交换过来之后i可以直接加1，判断下一个元素。
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 1) return;
        int start=0;
        int end=nums.length -1;
        for (int i=start;i<=end;) {
            if(nums[i]==0) {
                swap(nums,i,start);
                start++;i++;
            }else if(nums[i]==2) {
                swap(nums,i,end);
                end--;
            }else {
                i++;
            }
        }
    }
    private static void swap(int[] arr,int i,int j) {
        if(i==j) return;
        arr[i]=arr[i]+arr[j];
        arr[j]=arr[i]-arr[j];
        arr[i]=arr[i]-arr[j];
    }
}
