package com.aaa.provider.fanxing;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/18
 */
public class test {
    public static void main(String[] args) {
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        showKeyValue1(gInteger);
    }


    public static void showKeyValue1(Generic<?> obj){
        System.out.println("泛型测试key value is "+obj.getKey().toString());
    }


}
