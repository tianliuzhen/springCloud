package com.aaa.feign_gzip.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2019/1/22 16:44
 * @description
 */
@RestController
public class FeignController {
    @Autowired
    private   GiteeFeignClient giteeFeignClient;

    @GetMapping(value = "feign-gitee")
    public ResponseEntity<byte[]> feign(String query){
        return giteeFeignClient.searchRepo(query);
    }

}
