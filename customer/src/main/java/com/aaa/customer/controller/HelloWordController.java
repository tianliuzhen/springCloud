package com.aaa.customer.controller;

import com.aaa.customer.webApi.RemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/5/10
 */
@RestController
public class HelloWordController {
    @Autowired
    private RemoteApi remoteApi;

    @GetMapping("/query")
    public  String queryByFeign(){
        return "feign:"+remoteApi.queryByFeign("xx");
    }
    @GetMapping("/query2")
    public String queryByFeign2()      {
        Map map=new HashMap();
        map.put("1","aa");
        map.put("2","bb");
        map.put("2","cc");
      String str=  remoteApi.queryByFeign2(map);
        return "map参数"+str;
    }
    public String  getXml(){
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            StringBuffer buffer = new StringBuffer();
            BufferedReader bf= new BufferedReader(new FileReader("D:\\input.txt"));
            String s = null;
            //使用readLine方法，一次读一行
            while((s = bf.readLine())!=null){
                buffer.append(s.trim());
            }
            String xml = buffer.toString();
            System.out.println(xml);
            return  xml;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
