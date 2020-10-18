package basic.doublePointer;

import java.util.*;

public class SumProblem {
    public static void main(String[] args) {
        SumProblem sumProblem=new SumProblem();
        sumProblem.kSum(new int[]{2,1,0,-1},2);
    }
    /**
     * 两个元素之和 leetcode1
     * 还有一道题目leetcode167，也是求两数之和，不过给定的数组是有序的
     * 此时可以直接用双指针求解
     * <p>
     * 给定一个数组nums和一个int值target, 返回数组中和为target的数组下标
     * 你可以假定只有一个满足要求的答案，不可用使用同一个元素两次
     * Example 1:
     * <p>
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     * <p>
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     * <p>
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 三数之和  leetcode15
     * 给定一个包含n个整数的数组nums，判断nums中是否存在三个元素a，b，c，使得a+b+c=0
     * 找出所有满足条件且不重复的三元组
     * Example 1:
     * <p>
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     * Example 2:
     * <p>
     * Input: nums = []
     * Output: []
     * Example 3:
     * <p>
     * Input: nums = [0]
     * Output: []
     * 此题的子问题是求两数之和，本题只要求给出满足条件的三元组，而不要求下标，因此我们可以先对数组进行排序
     * 然后遍历数组，固定当前值，然后用两个指针分别指向第二个元素和最后一个元素，判断这两者的和与-nums[i]是否相等，
     * 如果小于-nums[i],则左指针右移，如果大于，则右指针左移。
     * 时间复杂度 O(N*logN)+O(N*logN)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;
        Arrays.sort(nums);
        if (nums[nums.length - 1] < 0) return ans;
        //此处右下限设为nums.length-2.因为循环内我们还会有两个大于i的元素
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == -nums[i]) {
                    List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right]);
                    ans.add(list);
                    left++;
                    right--;
                    //不能出现重复三元组，去重
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < -nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

    /**
     * 四数之和，leetcode18
     * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
     * <p>
     * Notice that the solution set must not contain duplicate quadruplets.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,0,-1,0,-2,2], target = 0
     * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * Example 2:
     * <p>
     * Input: nums = [], target = 0
     * Output: []
     * 固定住两个数，然后转化为求two sum问题
     * 时间复杂度 O(n*n*n)
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] + nums[i] + nums[j] == target) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        ans.add(list);
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (nums[left] + nums[right] + nums[i] + nums[j] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * k sum之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> kSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        List<List<Integer>> ans = new ArrayList<>();
        if (index == nums.length || nums[index] * k > target || nums[nums.length - 1] * k < target) {
            return ans;
        }
        //终止条件
        if (k == 2) {
            return twoSum(nums, target, index);
        }
        //从index处开始遍历
        for (int i = index; i < nums.length - k + 1; i++) {
            if (i == index  || (nums[i] != nums[i - 1])) {
                List<List<Integer>> list = kSum(nums, target - nums[i], k - 1, i + 1);
                for (List<Integer> integerList : list) {
                    ans.add(new ArrayList<>(integerList));
                    ans.get(ans.size() - 1).add(nums[i]);
                }
            }

        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] nums, int target, int index) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 2) return lists;
        int left = index;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target || (left > index && nums[left] == nums[left - 1])) {
                left++;
            } else if (nums[left] + nums[right] > target || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                right--;
            } else {
                lists.add(Arrays.asList(nums[left++], nums[right--]));
            }
        }
        return lists;
    }
}
