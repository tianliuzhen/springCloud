package com.aaa.security_oauth2.entity;

import com.aaa.security_oauth2.constants.ActObj;
import com.aaa.security_oauth2.constants.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
@Data
public class User2 {

    private String name;

    private String username;

    private String password;

    private String tel;

    private ActObj actObj;

    /**
     * 只有在    @ResponseBody 下才会正常显示转换后的格式
     * @param
     * @return
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
