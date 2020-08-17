package com.aaa.rabbitmq.retrySend;

import com.aaa.rabbitmq.retrySend.util.RetryTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 RetryCache.java  2020/8/17 14:00
 */
@Slf4j
@Component
public class RetryCache {

    @Resource
    private MessageSender messageSender;
    private static Map<String, MessageWithTime> map = new ConcurrentHashMap<>();

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class MessageWithTime {
        long time;
        Object message;
    }



    public static   void   add(String id, Object message) {
        map.put(id, new MessageWithTime(System.currentTimeMillis()/1000, message));
    }

    public static void del(String id) {
        map.remove(id);
    }

    /**
     * 重试机制，这里一般用于消息发的时候，服务器因网络抖动导致，消息没法出去
     */

    public void startRetry() {
            // 当map有未发出去的消息一直去尝试
            while (map.size()>0) {
                for (String key : map.keySet()) {
                    MessageWithTime messageWithTime = map.get(key);
                    // 处理方法1
                    // handlerV1(key, messageWithTime);

                    // 处理方法2 推荐
                    handlerV2(key, messageWithTime);

                }
            }
        }

    private void handlerV2(String key, MessageWithTime messageWithTime) {
        try {
            Object ans = new RetryTemplate() {
                @Override
                protected Object doBiz() {
                    DetailRes detailRes = messageSender.sendMsg(messageWithTime.getMessage());
                    if (detailRes.isSuccess()) {
                        del(key);
                    }
                    return null;
                }
            }.setRetryTime(3).setSleepTime(1000).execute();
        } finally {
            // 最后无论发送成功执行删除 key
            del(key);
        }
    }


    //
    // private void handlerV1(String key, MessageWithTime messageWithTime) {
    //     if (null != messageWithTime) {
    //         // 如果发送消息后三分钟仍然未发送成功，强制删除,意思也是重试三次
    //         if (messageWithTime.getTime() + Constants.RETRY_TIME * Constants.VALID_TIME < System.currentTimeMillis()/1000) {
    //             log.info("send message failed after 3 min " + messageWithTime);
    //             del(key);
    //             // 发送消息后一分钟后，自动触发重试
    //         } else if (messageWithTime.getTime() + Constants.VALID_TIME < System.currentTimeMillis()/1000) {
    //             DetailRes detailRes = sender.sendMsg(messageWithTime.getMessage());
    //
    //             if (detailRes.isSuccess()) {
    //                 del(key);
    //             }
    //         }
    //     }
    // }

    }
