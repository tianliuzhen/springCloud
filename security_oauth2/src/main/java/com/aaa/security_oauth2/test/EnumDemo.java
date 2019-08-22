package com.aaa.security_oauth2.test;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/22
 */
public class EnumDemo {
    public static void main(String[] args) {
//      System.out.println();

        //枚举转换整型
        int winter = Weather.winter.ordinal();
        System.out.println(winter);
        System.out.println(Weather.spring.ordinal());
        System.out.println(Weather.summer.ordinal());
        System.out.println(Weather.fall.ordinal());
        //枚举转换字符串。
        String win = Weather.winter.toString();
        String win2 = Weather.winter.name();
        System.out.println(win);
        System.out.println(win2);

        //字符转换枚举
        Weather weather = Weather.valueOf("spring");
        System.out.println(weather);

        //整型转换枚举
        int winter0 = 2;
        Weather weather2 = Weather.values()[winter0];

        System.out.println(weather2);

    }
}
