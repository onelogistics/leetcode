package basic.doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSumProblem {
    /**
     * nums中 k个数之和等于target
     * @param nums
     * @param target
     * @param k
     * @return k个数的组合
     */
    public List<List<Integer>> kSum(int[] nums, int target, int k) {
        Arrays.sort(nums);
        return kSum(nums,target,k,0);
    }

    /**
     * nums中 k个数之和等于target,从index下标开始计算
     * @param nums
     * @param target
     * @param k
     * @param index
     * @return
     */
    private List<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        List<List<Integer>> ans = new ArrayList<>();
        if(index == nums.length || nums[index]*k > target || nums[nums.length-1]*k < target) {
            return ans;
        }
        if(k==2) {
            return twoSum(nums, target, index);
        }
        for (int i=index;i<nums.length;i++) {
            if(i == index || nums[i] != nums[i-1]) {
                List<List<Integer>> recursionList= kSum(nums,target-nums[i],k-1,index+1);
                for (List<Integer> list :recursionList) {
                    list.add(nums[i]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    private List<List<Integer>> twoSum(int[] nums,int target, int index) {
        List<List<Integer>> ans = new ArrayList<>();
        if(index == nums.length || nums[index]*2 > target || nums[nums.length-1]*2 < target) {
            return ans;
        }
        int left=index;
        int right=nums.length-1;
        while (left<right) {
            if(nums[left]+nums[right]<target || (left>index && nums[left]==nums[left-1])) {
                left++;
            }else if (nums[left] + nums[right] > target || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                right--;
            } else {
                ans.add(new ArrayList<>(Arrays.asList(nums[left++], nums[right--])));
            }
        }
        return ans;
    }

}
