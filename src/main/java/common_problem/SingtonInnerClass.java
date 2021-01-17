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
public class SingtonInnerClass {
    private SingtonInnerClass() {

    }
    public static SingtonInnerClass getInstance() {
        return Instance.sington;
    }
    private static class Instance {
        static SingtonInnerClass sington=new SingtonInnerClass();
    }
}
