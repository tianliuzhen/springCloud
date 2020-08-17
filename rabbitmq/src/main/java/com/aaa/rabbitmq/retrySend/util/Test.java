package com.aaa.rabbitmq.retrySend.util;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/8/17 15:54
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //方法1. 基于线程
        retryDemo();

        //方法2. 基于反射
        // mainMethod();
    }


    public static void retryDemo() throws InterruptedException {
        Object ans = new RetryTemplate() {
            @Override
            protected Object doBiz() throws Exception {
                int temp = (int) (Math.random() * 10);
                if (2 > 1) {

                    throw new Exception("generate value bigger then 3! need retry");
                }
                return temp;
            }
        }.setRetryTime(2).setSleepTime(1000).execute();
        System.out.println(ans);
    }

    public static void mainMethod() {
        subMethod("123", "456");
    }

    public static void subMethod(String param1, String param2) {
        System.out.println(param1 + param2);
        RetryUtil.setRetryTimes(1).retry(param1, param2);
    }
}
