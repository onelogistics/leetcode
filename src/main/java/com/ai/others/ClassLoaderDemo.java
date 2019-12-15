package com.ai.others;

/**类加载器
 * @author JunjunYang
 * @date 2019/11/17 15:39
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
