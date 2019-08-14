package com.aaa.security_oauth2.aop;

import com.aaa.security_oauth2.entity.TestDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/10
 */
public class testReflect {
    public static void main(String[] args) {
        try {
            Object demo1 = Class.forName("com.aaa.security_oauth2.web.testWebController").newInstance();
            // 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。
            Field field = demo1.getClass().getDeclaredField("testDTO");
            //跳过权限访问检查--暴力反射
            field.setAccessible(true);
            // 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
            TestDTO user =new TestDTO();
            user.setName("012");
            field.set(demo1, user);
            //返回指定对象上此 Field 表示的字段的值。
            System.out.println(field.get(demo1));
            testall();
                System.out.println("The class of " + args + " is " + args.getClass().getName());
        } catch (Exception e) {
        }
    }
   public   static void testall(){

       try {
           //获取Person类的Class对象
           Class clazz=Class.forName("com.aaa.security_oauth2.web.testWebController");

           //获取Person类的所有方法信息
           Method[] method=clazz.getDeclaredMethods();
           for(Method m:method){
               System.out.println(m.toString());
           }

           //获取Person类的所有成员属性信息
           Field[] field=clazz.getDeclaredFields();
           for(Field f:field){
               System.out.println(f.toString());
           }

           //获取Person类的所有构造方法信息
           Constructor[] constructor=clazz.getDeclaredConstructors();
           for(Constructor c:constructor){
               System.out.println(c.toString());
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
