package com.aaa.security_oauth2.entity;

import lombok.Data;

import java.util.Date;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/9
 */
@Data
public class TestDTO {

  private String readOnly ;

  private String name  ;

  private Date date;

  private Boolean isDel;

  private TestDTO testDTO;

}
