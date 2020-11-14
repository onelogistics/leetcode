package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JunjunYang
 * @date 2020/2/4 13:29
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0,1);
        list.add(0,2);
        list.add(2,3);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
