package leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * dfs深度遍历，求一个数组的排列组合
 * 如，输入1,2,3,输出这三个元素所有的排序组合可能性
 */
public class Permute {
    //[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1],[3,1,2]]
    @Test
    public void test() {
        System.out.println(JSON.toJSONString(permute(new int[]{1, 2, 3})));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        dfs(nums, 0, res);
        return res;
    }

    /**
     * 不断交换元素来列出所有可能的排列
     *
     * @param nums
     * @param index
     * @param res
     */
    private void dfs(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList();
            for (int i : nums) list.add(i);
            res.add(list);
        }
        for (int i = index; i < nums.length; i++) {
            ArrayUtils.swap(nums, index, i);
            dfs(nums, index + 1, res);
            ArrayUtils.swap(nums, index, i);
        }
    }
}
