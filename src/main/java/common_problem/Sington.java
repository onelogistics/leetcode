package common_problem;

/**
 * 两种可以延时加载的单例模式
 * 静态内部类实现:需要注意内部类需要是静态的，私有的
 * 懒汉式：需要注意用volatile关键字修饰，利用其write before read特性防止因指令重排出现的线程安全问题
 * 单例模式的应用场景
 * 1、浏览器的弹窗提示需要做成单例的，多次点击只会弹出一次
 * 单例模式相较于全局变量和静态方法的优点
 * 1、需要传入运行时参数时最符合规范的用法就是懒汉式单例，全局变量和静态方法都不行
 * 2、方法论层面，根据面向对象的思想来思考 该用哪个
 */
public class Sington {
    private Sington() {

    }
    public static Sington getInstance() {
        return Instance.sington;
    }
    private static class Instance {
          static Sington sington=new Sington();
    }
}
class Sington2 {
    private Sington2(){}
    //since jdk1.5
    private  volatile static Sington2 instance;
    public static Sington2 getInstance() {
        if(instance==null) {
            synchronized(Sington2.class) {
                if(instance==null) {
                    instance=new Sington2();
                }
            }
        }
        return instance;
    }
}

/**
 * 饿汉式
 */
enum Sington3 {
    INSTANCE;
    private Object data;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data=data;
    }
}
abstract class Test1 {
    Test1() {
        //这里的this指的是实际调用此构造函数的对象
        System.out.println(this.getClass().getName());
    }
}
class Test2 extends Test1{
    Test2() {
    }
}
