package com.aaa.customer.webApi;

import com.aaa.customer.controller.HelloWordController;
import feign.hystrix.FallbackFactory;
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
public class RemoteApiFallbackFactory  implements FallbackFactory<RemoteApi> {
    @Override
    public RemoteApi create(Throwable throwable) {
        return  new RemoteApi() {
            @Override
            public String queryByFeign(String name) {
                System.out.println("返回异常1____: "+throwable);
                return "FallbackFactory_熔断_返回异常1";
            }

            @Override
            public String queryByFeign2(Map map) {
                System.out.println("返回异常2____: "+throwable);
                return "FallbackFactory_熔断_返回异常2";
            }
        };
    }
}
