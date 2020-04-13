package basic.sort;
import java.util.Arrays;

/**
 * 计数排序（非比较排序）
 * 基本思想：将输入的值转化为键存储在额外空间里
 * 实现逻辑：分别求出最大值和最小值，然后创建一个length为max-min+1的桶数组，遍历源数组将其数值转化为桶数组下标存储，桶数组的值即为数值出现的次数，然后遍历桶数组，将结果写入源数组。
 * 算法限制：要求输入的值必须是有确定范围的整数
 * Time Complexity:O(N+K)，K为桶数组的length
 * Space Complexity:O(K)
 *
 * @author JunjunYang
 * @date 2019/10/24 8:47
 */
public class CountingSort implements IArraySort{
    @Override
    public int[] sort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);
        int max = getMaxValue(array);
        int min = getMinValue(array);
        int[] bucket = new int[max - min + 1];
        for (int a : array) {
            bucket[a - min]++;
        }
        int sortIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                //由于转为下标时有-min操作，转回来时要记得+min操作
                array[sortIndex++] = i+min;
                bucket[i]--;
            }
        }
        return array;
    }

    public int getMaxValue(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        return max;
    }

    public int getMinValue(int[] array) {
        int min = array[0];
        for (int a : array) {
            if (a < min) min = a;
        }
        return min;
    }

}
