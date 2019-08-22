package com.aaa.security_oauth2.util;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/22
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class StrUtils {
    public StrUtils() {
    }

    public static String bytesToHexStr(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes != null && bytes.length > 0) {
            for(int i = 0; i < bytes.length; ++i) {
                int v = bytes[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static boolean isBlank(String str) {
        return str == null || "".equals(str);
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static String firstToLowerCase(String str) {
        return String.valueOf(str.toCharArray()[0]).toLowerCase() + str.substring(1);
    }

    public static String firstToUpperCase(String str) {
        return String.valueOf(str.toCharArray()[0]).toUpperCase() + str.substring(1);
    }

    public static String camelCaseToUnderLine(String str, boolean isToUpperCase) {
        StringBuilder buf = new StringBuilder(str);

        for(int i = 1; i < buf.length() - 1; ++i) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i)) && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }

        return isToUpperCase ? buf.toString().toUpperCase() : buf.toString().toLowerCase();
    }
}

