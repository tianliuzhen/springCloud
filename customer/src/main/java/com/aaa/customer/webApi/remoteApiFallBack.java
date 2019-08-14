package com.aaa.customer.webApi;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/5/11
 */
@Component
class RemoteApiFallBack implements  RemoteApi{
    @Override
    public String queryByFeign(String name) {
        return "queryByFeign__默认返回值:__1";
    }

    @Override
    public String queryByFeign2(Map map) {
        return "queryByFeign__默认返回值:__2";
    }
}
