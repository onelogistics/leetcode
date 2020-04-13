package common_problem;

/**
 * @author JunjunYang
 * @date 2020/4/13 15:46
 */
public enum  SingtonEnum {
    INSTANCE;
    private Object data;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data=data;
    }
}
