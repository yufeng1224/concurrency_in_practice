package com.yufeng.extend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description
 *      自定义注解
 * @author yufeng
 * @create 2020-04-08
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    /** 注解的参数: 参数类型 + 参数名 */
    String name() default "";

    int age() default 0;

    String[] schools() default "";
}
