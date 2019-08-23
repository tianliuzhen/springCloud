package com.aaa.security_oauth2.entity;

import com.aaa.security_oauth2.constants.enums.Gender;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
@Data
@Entity
@Table(name="sys_user")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)  // 实体类与json互转的时候 属性值为null的不参与序列化
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)  //多对一 传输JSON问题
@SQLDelete(sql = "update sys_user set is_del = 1 where id = ?") //设置逻辑删除 保证数据完整性
@Where(clause = "is_del = 0")   // 解决逻辑删除问题查询问题
public class User {

    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String userName;

    private String password;

    private String tel;

    private Gender gender;

    /**
     *  因为是 jackson 所以 只有在  @ResponseBody 下才会正常显示转换后的格式
     * @param
     * @return
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Integer is_del;
}
