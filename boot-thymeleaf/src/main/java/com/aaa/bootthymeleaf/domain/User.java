package com.aaa.bootthymeleaf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 User.java  2022/11/19 19:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jc")
public class User {
    @Id
    private Integer id;

    private String jc;
    private String jcVersion;
    private String jcCreator;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date jcCreateTime;
    private String school;
}
