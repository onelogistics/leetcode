package com.ai.algorithm.sort;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author JunjunYang
 * @date 2019/10/24 9:05
 */
public class SortTest {
    private static final Logger LOG = LoggerFactory.getLogger(SortTest.class);
    private static List<int[]> list = new ArrayList<>();

    @BeforeClass
    public static void getTestcase() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] array = new int[500];
            for (int j = 0; j < array.length; j++) {
                array[j] = random.nextInt(400);
            }
            list.add(array);
        }
    }

    @Test
    public void testCountingSort() {
        sort(new CountingSort());
    }

    @Test
    public void testBucketSort() {
        sort(new BucketSort());
    }

    public void sort(IArraySort iArraySort) {
        for (int[] array : list) {
            int[] copyArray = Arrays.copyOf(array, array.length);
            Arrays.sort(copyArray);
            Assert.assertArrayEquals(copyArray, iArraySort.sort(array));
        }
    }
}
