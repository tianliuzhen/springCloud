package com.aaa.feign_gzip.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author laiyy
 * @date 2019/1/22 16:35
 * @description
 */

@Component
@FeignClient(name = "gitee-client", url = "https://www.gitee.com/", configuration = GiteeFeignConfiguration.class)
public interface GiteeFeignClient {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String query);

}
