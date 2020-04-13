package common_problem;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicAgent {
    /**
     * 测试代码
     *
     * @param args
     */
    public static void main(String[] args) {
        Animal animal = (Animal) DynamicAgent.agent(Animal.class, new Dog());
        animal.run();
    }

    static class MyHandler implements InvocationHandler {
        private Object proxy;

        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        /**
         * 对于被代理的类的操作都会由invoke方法实现
         *
         * @param proxy  代理实例包装之后的对象，内含this指针
         * @param method 调用被代理的类的方法
         * @param args   该方法需要的参数
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("日志织入");
            Object res = method.invoke(this.proxy, args);
            return res;
        }
    }

    public static Object agent(Class interfaceClazz, Object proxy) {
        /**
         * loader：被代理的类的类加载器
         * interfaces：被代理类的接口数组
         * invocationHandler：就是刚刚介绍的调用处理器类的对象实例
         * 该方法会返回一个被修改过的类的实例
         */
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz}, new MyHandler(proxy));
    }
}
