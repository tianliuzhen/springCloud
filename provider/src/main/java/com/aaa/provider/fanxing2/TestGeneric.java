/*
package com.aaa.provider.fanxing2;

*/
/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/18
 *//*


import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

*/
/**
 * Created by CSH on 12/7/2015.
 *//*

//测试泛型
public class TestGeneric {

    public void test01(){
        Map map=new LinkedHashMap();
        new A<If<Father>>();    //不报错
        new B<If<Father>>();    //不报错
        new C<If<Father>>();    //不报错

        new A<If<Child>>();     //报错
        new B<If<Child>>();     //报错
        new C<If<Child>>();     //不报错

        new A<If<GrandFather>>();   //不报错
        new B<If<GrandFather>>();   //报错
        new C<If<GrandFather>>();   //报错
    }

}
class GrandFather {

}
class Father extends GrandFather{

}
class Child extends Father {

}

interface If<T>{
    void doSomething();
}

class A  <T extends If<? super Father>> {
}

class B <T extends If<Father>> {
}

class C <T extends If<? extends Father>>{
}*/
