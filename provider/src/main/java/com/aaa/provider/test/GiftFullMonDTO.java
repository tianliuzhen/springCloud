package com.aaa.provider.test;



/**
 * description: 指定金额赠送记录
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/9
 */
public class GiftFullMonDTO {
    private  String  pid;
    private  Double  mon;
    private  Boolean coupon_overlap ;
    private  Integer num;
    private  Double  amountMon;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Double getMon() {
        return mon;
    }

    public void setMon(Double mon) {
        this.mon = mon;
    }

    public Boolean getCoupon_overlap() {
        return coupon_overlap;
    }

    public void setCoupon_overlap(Boolean coupon_overlap) {
        this.coupon_overlap = coupon_overlap;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getAmountMon() {
        return amountMon;
    }

    public void setAmountMon(Double amountMon) {
        this.amountMon = amountMon;
    }

    @Override
    public String toString() {
        return "GiftFullMonDTO{" +
                "pid='" + pid + '\'' +
                ", mon=" + mon +
                ", coupon_overlap=" + coupon_overlap +
                ", num=" + num +
                ", amountMon=" + amountMon +
                '}';
    }
}
