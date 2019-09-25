package com.ai.others;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class HashSourceCode {
    public static void main(String[] args) {
        HashMap<Test,Integer> hashMap=new HashMap<>(16);
        hashMap.put(new Test(-1),-1);
        for (int i=0;i<12;i++) {
            hashMap.put(new Test(i),i);
        }
        hashMap.put(new Test(13),13);
        hashMap.put(new Test(14),14);
        System.out.println(hashMap.size());
    }
}
class Test {
    int a;
    public Test(int a) {
        this.a=a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return a == test.a;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
