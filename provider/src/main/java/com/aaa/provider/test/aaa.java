package com.aaa.provider.test;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/17
 */
public class aaa{
    public static void main(String[] args) {
        List<String> list1 = new ArrayList();
        list1.add("3");
        list1.add("4");
        list1.add("1");
        list1.add("1");
        System.out.println("排序前--:"+list1.toString());
        Collections.sort(list1);
        System.out.println("排序前后--:"+list1.toString());
        list1= list1.stream().distinct().collect(Collectors.toList());;
        System.out.println(list1);
    }
}
