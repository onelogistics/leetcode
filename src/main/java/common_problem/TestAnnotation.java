package common_problem;

/**
 * 自定义注解
 *
 * @author JunjunYang
 * @date 2019/12/14 19:44
 */


public class TestAnnotation {
    public static void main(String[] args) throws NoSuchFieldException {
        /**
         * 获取类注解属性
         */
        Class<Father> fatherClass = Father.class;
        boolean annotationPresent = fatherClass.isAnnotationPresent(MyAnnotation.class);
        if (annotationPresent) {
            MyAnnotation annotation = fatherClass.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.name());
            System.out.println(annotation.age());
        }
    }

}

@MyAnnotation(name = "jun", age = 22)
class Father {

}