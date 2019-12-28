package com.ai.algorithm.tree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author JunjunYang
 * @date 2019/12/27 8:46
 */
public class BinaryTreeTest {
    @Test
    public void reConstructor() throws Exception {
        BinaryTree binaryTree=BinaryTree.reConstructor(new int[]{1,2,4,7,3,5,6,8},new int[]{4,7,2,1,5,3,8,6});
        binaryTree.preOrder(binaryTree);
    }

    @Test
    public void preOrder() throws Exception {
    }

}