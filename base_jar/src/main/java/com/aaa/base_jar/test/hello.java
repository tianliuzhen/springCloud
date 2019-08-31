package com.aaa.base_jar.test;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/31
 */
public class hello {
    public static void main(String[] args) {
        System.out.println(getTest(1));
    }

    public static Integer getTest(Integer i) {
        i = i + 1;
        return i;
    }
}
