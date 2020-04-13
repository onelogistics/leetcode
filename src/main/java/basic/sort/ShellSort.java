package basic.sort;

import org.junit.Test;
import utils.ArrayUtils;

import java.util.Arrays;

/**希尔排序，基于简单插入的改动
 * 时间复杂度取决于gap的选择，当gap取1时就退化为了插入排序
 * @author JunjunYang
 * @date 2020/4/13 20:11
 */
public class ShellSort implements IArraySort{
    @Test
    public void test() {
        System.out.println(Arrays.toString(sort(new int[]{5,4,3,6,8,1,7})));
    }
    @Override
    public int[] sort(int[] sourceArray) {
        for (int gap=sourceArray.length/2;gap>0;gap=gap/2) {
            for (int i=gap;i<sourceArray.length;i++) {
                int j=i;
                //循环与同组的进行比较
                while (j-gap>=0 && sourceArray[j]<sourceArray[j-gap]) {
                    ArrayUtils.swap(sourceArray,j,j-gap);
                    j-=gap;
                }
            }
        }
        return sourceArray;
    }
}
