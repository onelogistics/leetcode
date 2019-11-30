package com.ai.others.design.model;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGlibAgent implements MethodInterceptor {
    private Object proxy;

    public static void main(String[] args) {
        CGlibAgent cGlibAgent = new CGlibAgent();
        Dog dog = (Dog) cGlibAgent.getInstance(new Dog());
        dog.run();
    }

    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxy.getClass());
        //创建回调对象
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("日志织入");
        Object res = methodProxy.invokeSuper(o, objects);
        return res;
    }
}
