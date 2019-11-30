package com.ai.others.design.model;

public class Dog implements Animal {
    @Override
    public void run() {
        System.out.println("dog run");
    }

    @Override
    public void count(int n) {
        System.out.println("dog run " + n);
    }
}
