package com.aaa.provider.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/17
 */
public class testAA {
    public static void main(String[] args) {
        List< A> list = new ArrayList< A>();

        A testA1 = new A("老张", 3);
        A testA2 = new A("老李", 1);
        A testA3 = new A("老王", 2);
        list.add(testA1); list.add(testA2); list.add(testA3);

        Collections.sort(list);
        System.out.println(list);
    }
}
