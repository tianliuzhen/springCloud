package com.aaa.provider.controller;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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
public class ProviderController {
    @GetMapping("/query")
    public String queryByFeign(@RequestParam(value = "name") String  name){
        System.out.println("消费者的普通参数："+name);
//        exception();
        return name+"普通参数";
    }
    @GetMapping("/query3")
    public Map queryByFeign(@RequestBody  Map map){
//        exception();
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
        System.out.println(map.get("id"));
        map.put("status",200);
        return map;
    }
    @PostMapping("/query2")
    public Map queryByFeign2(@RequestBody  Map map)      {
        System.out.println("消费者的map参数："+map);
        Map map2=new HashMap();
        map2.put("se",getXml());
        return map2;
    }
    public void exception(){
        throw new ArrayIndexOutOfBoundsException ();
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
