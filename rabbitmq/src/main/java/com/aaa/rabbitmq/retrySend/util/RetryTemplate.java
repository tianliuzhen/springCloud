package com.aaa.rabbitmq.retrySend.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * @author liuzhen.tian
 * @version 1.0 RetryTemplate.java  2020/8/17 15:53
 */
@Slf4j
public abstract class RetryTemplate {

    private static final int DEFAULT_RETRY_TIME = 1;
    private int retryTime = DEFAULT_RETRY_TIME;
    /**
     * 重试的睡眠时间
     */
    private int sleepTime = 0;

    public int getSleepTime() {
        return sleepTime;
    }

    public RetryTemplate setSleepTime(int sleepTime) {
        if(sleepTime < 0) {
            throw new IllegalArgumentException("sleepTime should equal or bigger than 0");
        }
        this.sleepTime = sleepTime;
        return this;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public RetryTemplate setRetryTime(int retryTime) {
        if (retryTime <= 0) {
            throw new IllegalArgumentException("retryTime should bigger than 0");
        }
        this.retryTime = retryTime;
        return this;
    }

    /**
     * 重试的业务执行代码
     * 失败时请抛出一个异常
     *
     * todo 确定返回的封装类，根据返回结果的状态来判定是否需要重试
     *
     * @return
     */
    protected abstract Object doBiz() throws Exception; //预留一个doBiz方法由业务方来实现，在其中书写需要重试的业务代码，然后执行即可

    @SneakyThrows
    public Object execute() {
        for (int i = 0; i < retryTime; i++) {
            try {
                return doBiz();
            } catch (Exception e) {
                Thread.sleep(sleepTime);
                e.printStackTrace();


            }
        }
        return null;
    }

    public Object submit(ExecutorService executorService) {
        if (executorService == null) {
            throw new IllegalArgumentException("please choose executorService!");
        }
        return executorService.submit((Callable) () -> execute());
    }
}
