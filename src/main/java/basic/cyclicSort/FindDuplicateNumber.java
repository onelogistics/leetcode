package basic.cyclicSort;

/**
 * 287. Find the Duplicate Number | medium
 * 给定一个int数组，数组中包含n+1个元素，这n+1个元素都在[1,n]范围内，
 * 数组中只有一个重复元素，返回这个重复的元素
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * Example 3:
 *
 * Input: nums = [1,1]
 * Output: 1
 * Example 4:
 *
 * Input: nums = [1,1,2]
 * Output: 1
 */
public class FindDuplicateNumber {
    public static void main(String[] args) {

    }
    /**
     * 二分逼近法
     * 数组中的元素都在[1,n]范围内，那我们可以计算小于中位数（n+1)/2的元素个数，如果个数大于（n+1）/2
     * 说明重复元素在[1,(n+1)/2-1]之间，否则说明在[(n+1)/2+1,n]之间
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        //左闭右开区间[left,right],实际的数字范围是在[1,n]之内
        int left=1,right=nums.length;
        //终止条件是，left=right
        while (left<right) {
            int mid=left+(right-left)/2;
            int cnt=0;
            //求nums中小于等于中间值的元素数量
            for (int num:nums) {
                if(num<=mid) cnt++;
            }
            //如果小于等于mid的元素数量不足一半，则说明重复元素在右半区，left=mid+1
            if(cnt<=mid) {
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }

    /**
     * 快慢指针解法
     * n+1的数组长度，每个数字都在[1,n]之间，则一定有环
     * 链表中是fast.next.next
     * 在本题中将当前值作为next,nums[nums[fast]]
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int fast=0,slow=0;
        //找到相遇点
        while (true) {
            slow=nums[slow];
            fast=nums[nums[fast]];
            if(slow==fast) break;
        }
        fast=0;
        //一个从相遇点出发，一个从起点出发，会在环的入口处相遇
        while (true) {
            slow=nums[slow];
            fast=nums[fast];
            if(slow==fast) break;
        }
        return slow;
    }


}
