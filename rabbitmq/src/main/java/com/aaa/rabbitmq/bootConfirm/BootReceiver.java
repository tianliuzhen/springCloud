package com.aaa.rabbitmq.bootConfirm;

/**
 * description: 消费确认
 * 参考：https://www.jianshu.com/p/1581f9dcdda4
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/27
 */

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@Slf4j
public class BootReceiver {
    @RabbitListener(queues = "transition")
    public void process(Message message, Channel channel) throws IOException {
        // 限流处理：消息体大小不限制，每次限制消费一条，只作用于该Consumer层，不作用于Channel
        //限制于消费级别
        channel.basicQos(0, 1, false);
        log.info("TransactionReceiver2  : " + new String(message.getBody()));
        String messageText = new String(message.getBody());
        try {
            if(validate(messageText)){
                try {
                    log.info("[receiver] confirm");
                    //确认消息接收
                    // 如果不加下面这句话是自动确认，采用手动应答模式, 手动确认应答更为安全稳定
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                    int i=1/0;
                } catch (Exception e) {
                    //最后一个参数requeue一般都会为true，此次没调用到数据，把这个消息返回到队列中再消费，
                    // 如果代码中出现了int i=1/0,那么还是会造成死循环。
                    // 给Queue绑定死信队列，使用nack（requque为false）确认消息消费失败
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                }
            }else{
                log.info("[receiver] reject");
                //拒绝消息接收
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validate(String messageText) {
        return !(messageText!=null && messageText.indexOf("fuck")>-1);
    }
}

