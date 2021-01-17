package basic.sort;


/**主体思想：建立一个临时数组，不断分而治之，直到left=right,然后开始merge
 * time complexity:O(NlogN)
 * space complexity:O(N)
 * stable:true
 */
public class MergeSort {
    public int[] sortArray(int[] nums) {
        int[] temp = new int[nums.length];
        sort(nums, temp, 0, nums.length - 1);
        return nums;
    }

    //分治，拆分到最细粒度
    public void sort(int[] nums, int[] temp, int left, int right) {
        if (left < right) {
            //避免溢出
            int mid = left + (right - left) / 2;
            sort(nums, temp, left, mid);
            sort(nums, temp, mid + 1, right);
            merge(nums, temp, left, mid, right);
        }
    }

    //合并两个有序数组
    public void merge(int[] nums, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        for (i = 0; i < k; i++) {
            nums[left++] = temp[i];
        }
    }
}
