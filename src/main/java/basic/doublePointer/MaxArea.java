package basic.doublePointer;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * leetcode 11 Container With Most Water
 * <p>
 * 给定n个非负整数，a1,a2,...an, 每个数字代表一个坐标(i, ai),在坐标上划n条垂直线，垂直线的两个端点分别是
 * (i, 0) 和 (i, ai), 找出其中的两条线，使得它们与x轴构成的容器可以盛最多的水,返回最大面积
 */
public class MaxArea {
    /**
     * 解法分析：
     * 本题是求最大面积，两条线之间的面积为(j-i)*Math.min(ai,aj)，即长和高的乘积为容器的面积，从题目中我们可以得知
     * 最大的长即为(0,length-1)，那么我们可以用两个指针分别指向数组首和尾，此时计算出一个面积，这时的面积受制于ai,aj中较小的一个
     * 所以我们移动ai,aj中较小的一个指针，寻找更大的面积
     * 时间复杂度O(N)
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) return 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (right > left) {
            maxArea = Math.max((right - left) * Math.min(height[left], height[right]), maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
