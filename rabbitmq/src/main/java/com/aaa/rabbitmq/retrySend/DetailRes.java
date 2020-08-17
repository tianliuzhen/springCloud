package com.aaa.rabbitmq.retrySend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 DetailRes.java  2020/8/17 14:08
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRes {
    boolean isSuccess;
    String msg;
}
