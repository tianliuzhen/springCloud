package com.aaa.security_oauth2.constants;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/22
 */

/**
 * 状态枚举
 * @author chenmc
 *
 */
public enum EStatus {

    disable("0"), enable("1"), deleted("2"),
    init("10"), start("11"), wait("12"), end("13");

    private final String value;

    private EStatus(String v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static EStatus get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EStatus get(String str) {
        for (EStatus e : values()) {
            if(e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }

    /*public static String getName(EStatus e, Locale locale) {
        return I18N.getEnumName(e, locale);
    }*/
}