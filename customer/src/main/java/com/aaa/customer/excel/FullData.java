package com.aaa.customer.excel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * description: 满减满折统计数据
 *
 * @author 蔡荣茂(cairongmao @ haoxiaec.com)
 * @version 1.0
 * @date 2019/3/18
 */
@Entity
@Table(name="full_data")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class FullData {

    @ApiModelProperty("活动id")
    @Column(name = "act_id",columnDefinition = "活动id")
    private String actId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("日期")
    @Column(name = "date",columnDefinition = "日期")
    private LocalDate date;

    @ApiModelProperty("下单数")
    @Column(name = "order_num",columnDefinition = "下单人数")
    private String orderNum;

    @ApiModelProperty("下单人数")
    @Column(name = "order_user_num",columnDefinition = "下单人数")
    private String orderUserNum;

    @ApiModelProperty("支付数")
    @Column(name = "pay_num",columnDefinition = "支付人数")
    private String payNum;

    @ApiModelProperty("支付人数")
    @Column(name = "pay_user_num",columnDefinition = "支付人数")
    private String payUserNum;

    @ApiModelProperty("实付总额")
    private String sumMoneyPaid;

    @ApiModelProperty("优惠总额")
    private String discountMoney;

    @ApiModelProperty("优惠券总额")
    private String couponMoney;

    @ApiModelProperty("该活动所影响的优惠金额")
    private String discountMoneyByAct;

    public void setDiscountMoneyByAct(){
        this.discountMoneyByAct = new BigDecimal(this.discountMoney).subtract(new BigDecimal(this.couponMoney)).toString();
    }

}
