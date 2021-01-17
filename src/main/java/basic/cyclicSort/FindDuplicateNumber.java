package basic.cyclicSort;

/**
 * 287. Find the Duplicate Number | medium
 * 给定一个int数组，数组中包含n+1个元素，这n+1个元素都在[1,n]范围内，
 * 数组中只有一个重复元素，返回这个重复的元素
 *Follow-ups:
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem without modifying the array nums?
 * Can you solve the problem using only constant, O(1) extra space?
 * Can you solve the problem with runtime complexity less than O(n2)?
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [1,1]
 * Output: 1
 *
 * Example 4:
 * Input: nums = [1,1,2]
 * Output: 1
 */
public class FindDuplicateNumber {
    static FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
    public static void main(String[] args) {
        System.out.println(findDuplicateNumber.findDuplicate1(new int[]{4,3,2,2,1}));
    }

    /**
     * 但这种解法破坏了第二条注意事项，修改了数组nums
     * 将num[i]放到i的位置，由于num[i]在1到n范围内，所以最后留在下标0位置的数即是重复的数
     * 时间复杂度：O(N)
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        while (nums[nums[0]]!=nums[0]) {
            int temp = nums[0];
            nums[0]=nums[nums[0]];
            nums[temp]=temp;
        }
        return nums[0];
    }
    /**
     * 二分逼近法
     * 数组中的元素都在[1,n]范围内，那我们可以计算小于中位数（n+1)/2的元素个数，如果个数大于（n+1）/2
     * 说明重复元素在[1,(n+1)/2-1]之间，否则说明在[(n+1)/2+1,n]之间
     *时间复杂度 O(NlogN)
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        //左闭右开区间[left,right),实际的数字范围是在[1,n]之内,初始化时left为1，right为n+1
        int left=1,right=nums.length;
        //终止条件是，left=right
        while (left<right) {
            //避免溢出
            int mid=left+(right-left)/2;
            int cnt=0;
            //求nums中小于等于中间值的元素数量
            for (int num:nums) {
                if(num<=mid) cnt++;
            }
            //如果小于等于mid的元素数量不足一半，则说明重复元素在较大的右半区，left=mid+1
            if(cnt<=mid) {
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }

    /**
     * 时间复杂度：O(N)
     * 快慢指针解法
     * n+1的数组长度，每个数字都在[1,n]之间，则一定有环
     * 链表中是fast.next.next
     * 在本题中将当前值作为next,nums[nums[fast]]
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int fast=0,slow=0;
        //找到相遇点,从0点出发的两个指针，由于重复元素的存在，总会相遇
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
