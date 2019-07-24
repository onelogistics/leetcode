package com.ai.algorithm.search;

import org.junit.Test;

public class SearchTest {
    int[][] matrix0 = {
            {1,2,3},
            {4,5,6}
    };

    int[][] matrix1 = {
            {300, 500, 560, 400, 160},
            {1000, 100, 200, 340, 690},
            {600, 500, 500, 460, 320},
            {300, 400, 250, 210, 760}
    };

    int[][] matrix2 = {
            {300, 500, 2560, 400},
            {1000, 100, 200, 340},
            {600, 500, 500, 460},
            {300, 400, 250, 210},
            {860, 690, 320, 760}
    };
    @Test
    public void testDFS()  {
        DeepFirstSearch dp = new DeepFirstSearch();

        System.out.println(dp.getMaxAward(matrix0));
        dp.printBestPath();
        System.out.println(dp.getMaxAward(matrix1));
        dp.printBestPath();
        System.out.println(dp.getMaxAward(matrix2));
        dp.printBestPath();
    }
    @Test
    public void testBFS() {
        BreadthFirstSearch bd=new BreadthFirstSearch();
        System.out.println(bd.getMaxAward(matrix0));
        bd.printBestPath();
        System.out.println(bd.getMaxAward(matrix1));
        bd.printBestPath();
        System.out.println(bd.getMaxAward(matrix2));
        bd.printBestPath();
    }
    @Test
    public void testDP() {
        DynamicProgramming dynamicProgramming=new DynamicProgramming();
        System.out.println(dynamicProgramming.getMaxAward(matrix0));
        dynamicProgramming.printBestPath();
        System.out.println(dynamicProgramming.getMaxAward(matrix1));
        dynamicProgramming.printBestPath();
        System.out.println(dynamicProgramming.getMaxAward(matrix2));
        dynamicProgramming.printBestPath();
    }
}
