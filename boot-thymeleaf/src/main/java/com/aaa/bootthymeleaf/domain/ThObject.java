package com.aaa.bootthymeleaf.domain;

import lombok.Data;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/28
 */
@Data
public class ThObject {
    private Integer id;
    private String name;
    private String desc;

    public ThObject(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
}
