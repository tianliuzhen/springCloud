package com.aaa.security_oauth2.entity;

import com.aaa.security_oauth2.constants.enums.Gender;
import com.aaa.security_oauth2.service.baseJpa.BaseEntity;
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
import java.util.Objects;

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
public class User extends BaseEntity {

    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "tel")
    private String tel;

    @Column(name = "gender")
    private Gender gender;


/*
    @Column(name = "is_del")
    private Integer isDel;*/


}
