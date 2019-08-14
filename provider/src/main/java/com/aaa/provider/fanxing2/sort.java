package com.aaa.provider.fanxing2;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/19
 */
public class sort {
    public static void main(String[] args) {
        Person p1 = new Person("张三", "a",new BigDecimal("50.0"));
        Person p2 = new Person("王五","b", new BigDecimal("25.0"));
        Person p3 = new Person("李四","c", new BigDecimal("68.0"));
        Person p4 = new Person("李四","d", new BigDecimal("17.0"));
        Person p5 = new Person("张三","e", new BigDecimal("50.0"));
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.sort(Comparator.comparing(person -> person.getMoney()));
        System.out.println("元素的属性值正序："+list);
        List<Person> collect = list.stream().sorted(Comparator.comparing(Person::getMoney).reversed()).collect(Collectors.toList());
        System.out.println("元素的属性值倒序：" + collect);
        // 根据name去重
      List<Person> unique = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))), ArrayList::new)
        );
        System.out.println(unique);
        // 根据name,sex两个属性去重
        List<Person> unique2 = list.stream().collect(
                Collectors. collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getName() + "," + o.getMoney()))), ArrayList::new)
        );

        List<Person> filterList = list.stream().filter(p -> p.getSex().equals("a")).collect(Collectors.toList());

        System.out.println(filterList);

    }
}
