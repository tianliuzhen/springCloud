package com.aaa.provider.demo;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/18
 */
public class Demo1<T> {


    //自定义带泛型的方法
    public <T>T funtion(T t) {

        return null;
    }

    public <T,E,K>void b(T t,E e,K k) {

    }

    //静态方法泛型定义在static后
    public static<T> void c(T t) {

    }
}