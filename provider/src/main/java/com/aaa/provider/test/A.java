package com.aaa.provider.test;

import java.io.Serializable;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/17
 */
class A implements Comparable<A> ,Serializable {

    private static final long serialVersionUID = -1377067922302029632L;
    private String name;
    private Integer order;

    public A(String name, Integer order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int compareTo(A a) {
        //升序
        return this.order.compareTo(a.getOrder());
        //降序
//        return a.getOrder().compareTo(this.order);
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
