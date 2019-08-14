package com.aaa.provider.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/18
 */
public class test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            Object name =  list.get(i);
            System.out.println("name:" + name);
        }
        Box<Integer> name = new Box<Integer>(90);
        Box<?>  name2 = new Box<String>("2");
        List list2= new LinkedList();
    }

}
