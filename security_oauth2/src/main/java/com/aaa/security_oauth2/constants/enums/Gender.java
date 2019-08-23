package com.aaa.security_oauth2.constants.enums;

import com.aaa.security_oauth2.config.config_enum.CodeEnum;
import com.aaa.security_oauth2.config.config_enum.handlerJson.EnumDeserializerByCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using= EnumDeserializerByCode.class)
public enum Gender implements CodeEnum {
    /**
     * 未设置性别
     */
    NULL(3,""),
    /**
     * {}
     */
    FEMALE(0,"女"),
    /**
     * 男
     */
    MALE(1,"男");

    private int code;
    private String name;

    /**
     * api文档说明
     */
    public static final String CODES = "[0：女，1：男]";

/*
    private static Map<String,Gender> NAME_MAP = Maps.newConcurrentMap();

    static {
        for (Gender gender :Gender.values()){
            if(gender.name.length() > 0){
                NAME_MAP.put(gender.name, gender);
            }
        }
    }

    public static String names(){
        return Arrays.toString(NAME_MAP.keySet().toArray());
    }

    public static Gender valueOfName(String name){
        if(StringUtil.isNotEmpty(name)) {
            return NULL;
        }
        Gender gender = NAME_MAP.get(name);
        return gender == null ? NULL : gender;
    }

    *//**
     * 通过 code 获取 Gender
     * @param code
     * @return
     *//*
    public static Gender getStatusByCode(Integer code) {
        switch (code) {
            case 0:
                return NULL;
            case 1:
                return FEMALE;
            default:
                return MALE;
        }
    }*/
}
