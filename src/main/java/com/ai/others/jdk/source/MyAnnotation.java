package com.ai.others.jdk.source;

import java.lang.annotation.*;

/**
 * @author JunjunYang
 * @date 2019/12/14 19:49
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
    String name() default "ying";

    int age() default 18;
}