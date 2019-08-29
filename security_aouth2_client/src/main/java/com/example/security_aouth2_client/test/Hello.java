package com.example.security_aouth2_client.test;

import com.example.security_aouth2_client.test.test1.test2;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/28
 */
public class Hello {

    public static void main(String[] args) throws Exception {
        test2 test=new test2();

        System.out.println(test.getTest(2));
    }
    public Integer getTest(Integer i){
        return i++;
    }
}
