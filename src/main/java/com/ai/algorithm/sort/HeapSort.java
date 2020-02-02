package com.ai.algorithm.sort;

import com.ai.algorithm.utils.ArrayUtils;

/**
 * @author JunjunYang
 * @date 2019/9/23 15:20
 */
public class HeapSort {
    private int capacity;
    private int size;
    private int[] array;

    /**
     * Time complexity:建堆O(N)+调整O(NlogN)
     * Space complexity: O(1)
     * stable: 不稳定
     *
     * @param array
     */
    public void sort(int[] array) {
        //建堆,从最后一个非叶子节点开始，最后一个节点的下标为length-1,则其父节点为(length-1-1)/2=length/2-1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        //调整
        for (int i = array.length - 1; i >= 0; i--) {
            ArrayUtils.swap(array, 0, i);
            adjustHeap(array, 0, i);
        }
    }

    /**
     * 自上而下调整
     *
     * @param array
     * @param i      当前需要调整的节点下标
     * @param length 需要调整的节点尾下标，不包含此下标。
     */
    public void adjustHeap(int[] array, int i, int length) {
        //记录需要调整的节点值
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //右子节点较大时，使用右子节点
            if (k + 1 < length && array[k + 1] > array[k]) k++;
            //子节点值大于父节点，将子节点值赋给父节点（无需交换）
            if (array[k] > temp) {
                array[i] = array[k];
                //记录最新的子节点下标
                i = k;
            } else { //如果子树已经有序则跳出本次调整
                break;
            }
        }
        //将temp赋值给最新空缺的子节点
        array[i] = temp;
    }

    /**
     * 删除堆顶元素
     */
    public void remove() {
        ArrayUtils.swap(array, 0, size - 1);
        size--;
        adjustHeap(array, 0, size);
    }

    /**
     * 末尾插入元素,父节点是(i-1)/2
     *
     * @param value
     */
    public void insert(int value) {
        array[size] = value;
        int i = size;
        size++;
        while (i > 0) {
            int parentIdx = (i - 1) / 2;
            if (array[parentIdx] < array[i]) {
                ArrayUtils.swap(array, parentIdx, i);
            } else {
                break;
            }
            i = parentIdx;
        }
    }
}