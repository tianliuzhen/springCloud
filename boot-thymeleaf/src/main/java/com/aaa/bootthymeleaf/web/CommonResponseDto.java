package com.aaa.bootthymeleaf.web;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/28
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponseDto implements Serializable {

    private Integer code;

    private boolean success;

    private String data;

    public CommonResponseDto data(String data) {
        this.data = data;
        return this;
    }

    public CommonResponseDto code(Integer code){
        this.code = code;
        return this;
    }

    public CommonResponseDto success(boolean success){
        this.success = success;
        return this;
    }


}
