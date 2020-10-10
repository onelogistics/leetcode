package basic.slidingWindow;

/**
 * leetcode 992 Subarrays with K Different Integers
 * 解法2请参考：https://juejin.im/post/6844903837447225358
 * 给定一个数组和一个参数值K，求数组的所有子数组中，不同元素的个数刚好为K个的子数组的数量
 * Example 1:
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

 * Example 2:
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 */
public class SubarraysWithKDistinct {
    public int subarraysWithKDistinct(int[] A, int K) {
        //最多有k个不同元素的子数组个数减去最多有k-1个不同元素的子数组个数，恰好等于元素数量为K个的子数组个数
        return subArraysWithMostKDistinct(A,K)- subArraysWithMostKDistinct(A,K-1);
    }

    /**
     * 求最多有k个不同元素的子数组个数
     * @param A
     * @param K
     * @return
     */
    public int subArraysWithMostKDistinct(int[] A, int K) {
        int left=0,ans=0,count=0;
        int[] hash=new int[A.length+1];
        for (int right=0;right<A.length;right++) {
            //记录不同元素的数量
            if(++hash[A[right]]==1) {
                count++;
            }
            while (count > K) {
                if(--hash[A[left]]==0) {
                    count--;
                }
                left++;
            }
            //以right结尾的子数组个数为right-left+1个
            ans+=right-left+1;
        }
        return ans;
    }
}
