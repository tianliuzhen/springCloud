package com.aaa.securityweb.web;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * description:  赠品活动的输入值
 *
 * @author tianliuzhen(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/3/15
 */
@Getter
@Setter
public class GiftActSpec {

    private String actType = "GIFT_ACT";

    @NotBlank(message = "活动名称 不能为空")
    private String name;

}
