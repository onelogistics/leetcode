package common_problem;

/**
 * @author JunjunYang
 * @date 2020/4/13 15:45
 */
public class SingtionVolatile {
    //since jdk1.5
    private volatile static SingtionVolatile instance;

    private SingtionVolatile() {
    }

    public static SingtionVolatile getInstance() {
        if (instance == null) {
            synchronized (SingtionVolatile.class) {
                if (instance == null) {
                    instance = new SingtionVolatile();
                }
            }
        }
        return instance;
    }
}
