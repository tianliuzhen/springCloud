package com.aaa.security_oauth2.constants;

import com.aaa.security_oauth2.config.config_enum.CodeEnum;
import com.aaa.security_oauth2.config.config_enum.handlerJson.EnumDeserializerByCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.enums.Enum;

/**
 * description: 面向用户群体 枚举
 *
 * @author 蔡荣茂(cairongmao @ haoxiaec.com)
 * @version 1.0
 * @date 2019/2/20
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using= EnumDeserializerByCode.class)
public enum ActObj implements CodeEnum {

    ALL(0,"全部用户"),

    NEW(1,"新用户"),

    OLD(2,"老用户"),
    ;

    private int code;
    private String name;

}
