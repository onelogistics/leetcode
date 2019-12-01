package com.ai.algorithm.sort;
import java.util.Arrays;

/**
 * 算法名称：冒泡排序
 * 基本思想：每轮排序找到最大的值放到未排序部分的末尾，这样迭代length-1轮，数据就全部有序了。
 * 实现逻辑：见名知义，类似于冒泡，每轮迭代每个数都和它相邻的数进行比较，这样不断移动，最后将最大的数移到末尾，需要迭代length-1轮，第i轮需要比较length-1-i次，优化方法之一是加一个此轮迭代是否有序的标志位，这样在数组已经有序时就可以避免无意义的比较。
 * 算法限制：复杂度较高，思想可以借鉴，但平时开发不建议使用
 * Time Complexity：O(N*N)
 * Space Complexity: O(1)
 */
public class BubbleSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
        return array;
    }

    public void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }
}
