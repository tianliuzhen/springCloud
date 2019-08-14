package com.aaa.provider.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/17
 */
public class demo {
    public static void main(String[] args) {

        TestA testA1 = new TestA("老张", 3);
        TestA testA2 = new TestA("老李", 1);
        TestA testA3 = new TestA("老王", 2);
        List<TestA> list  = new ArrayList<>();
        list.add(testA1);
        list.add(testA2);
        list.add(testA3);
        System.out.println(listRe(list,true));
        System.out.println(toStringHex2("e6b58be8af95e98080e6acbe"));
    }
    public static   List<TestA> listRe(List<TestA> list,boolean b){
        System.out.println("排序前--:"+list.toString());
        Collections.sort(list, new Comparator<TestA>() {
            @Override
            public int compare(TestA o1, TestA o2) {
                if(b){
                    //升序
                    return o1.getAge().compareTo(o2.getAge());
                }else{
                    //降序
                    return o2.getAge().compareTo(o1.getAge());
                }
            }
        });
        System.out.println("排序后--:"+list.toString());

        return  list;
    }

    public static String toStringHex2(String s) {
        // 转化十六进制编码为字符串
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            // UTF-16le:Not
            s = new String(baKeyword, "utf-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}
