package com.example.ant.annotation;

import java.lang.annotation.*;

/**
 * 描述：自定义注解，拦截rest请求
 * User: 曾远征
 * Date: 2018-09-17
 * Time: 16:32
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestLog {

    String description()  default "";
}
