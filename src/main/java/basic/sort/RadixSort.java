package basic.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序（非比较排序）
 * 基本思想：将整数按照位数切割成不同的数字，然后每位分别比较
 * 实现逻辑：求出最大值的位数，将其他待比较数值的前面补0，然后从最低位开始，将其分到0~9范围内的10个桶中，然后按照桶顺序及桶内的顺序取出，这样就完成了一次排序，这样一直迭代直到最高位，数组就变成了一个有序队列。
 * 算法限制：基数排序可以用来比较字符串或数字，是计数排序和桶排序的改进版,
 * Time Complexity:O(N*K)，K为最高位的位数
 * Space Complexity:O(N)
 *
 * @author JunjunYang
 * @date 2019/10/25 10:32
 */
public class RadixSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {
        if (sourceArray.length <= 1) return sourceArray;
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);
        int maxDigit = getMaxDigit(array);
        //创建0~9的桶
        int bucketNum = 10;
        ArrayList<Integer>[] bucket = new ArrayList[bucketNum];
        for (int i = 0; i < bucketNum; i++) bucket[i] = new ArrayList<>();
        int mod = 10;
        int div = 1;
        //多次分桶并排序
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int val : array) {
                int index = (val % mod) / div;
                bucket[index].add(val);
            }
            int pos = 0;
            for (ArrayList arrayList : bucket) {
                for (Object val : arrayList) {
                    array[pos++] = (int) val;
                }
                //注意每次排序完后需要清空list，以便下次迭代使用
                arrayList.clear();
            }
        }
        return array;
    }

    /**
     * 最大值的位数
     *
     * @param array
     * @return
     */
    public int getMaxDigit(int[] array) {
        int maxValue = array[0];
        for (int val : array) {
            if (val > maxValue) maxValue = val;
        }
        int maxDigit = 1;
        while ((maxValue /= 10) != 0) {
            maxDigit++;
        }
        return maxDigit;
    }
}
