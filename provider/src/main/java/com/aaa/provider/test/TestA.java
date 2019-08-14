package com.aaa.provider.test;

import javax.annotation.sql.DataSourceDefinition;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/17
 */

public class TestA {
    private String name;
    private Integer age;

    public TestA(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public TestA() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestA{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
