package com.ai.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 算法名称：桶排序
 * 基本思想：基于非比较的排序，将数组中的数值按照大小分到不同的桶中，桶内排序可以使用任意算法排序(插入、快排)，排序完成后按顺序整合桶内数据即可。
 * 实现逻辑：统计数组中的最大值和最小值，根据数据分布确定桶数BucketNum，计算每个桶的数据范围BucketRange；创建一个链表数组，将源数组中的数据根据BucketRange进行分割，分到不同的桶中；桶内进行排序，然后依次遍历每一个桶，将桶内的数据赋给数组。
 * 算法限制：桶排序的效率很大程度上取决于分桶情况，数据越均匀、每个桶内分到的数据量相差不大时，才适合用桶排序
 * Time Complexity：O(N+N*log(N/K))
 * Space Complexity: O(N)
 */
public class BucketSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);
        int bucketNum = 5;
        int maxValue = array[0];
        int minValue = array[0];
        for (int val : array) {
            if (val > maxValue) maxValue = val;
            if (val < minValue) minValue = val;
        }
        int bucketRange = (int) (Math.floor((maxValue - minValue) / bucketNum) + 1);
        ArrayList<Integer>[] bucket = new ArrayList[bucketNum];
        //数据分到不同的桶内
        for (int i = 0; i < bucketNum; i++) {
            bucket[i] = new ArrayList<Integer>();
        }
        for (int val : array) {
            bucket[(int) (Math.floor(val - minValue) / bucketRange)].add(val);
        }
        //桶内排序
        for (ArrayList arrayList : bucket) {
            Collections.sort(arrayList);
        }
        int sortIndex = 0;
        for (ArrayList arrayList : bucket) {
            for (Object val : arrayList) {
                array[sortIndex++] = (int) val;
            }
        }
        return array;
    }
}
