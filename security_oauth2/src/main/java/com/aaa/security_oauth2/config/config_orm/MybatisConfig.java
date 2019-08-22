package com.aaa.security_oauth2.config.config_orm;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * description: 描述
 *  3. 做完以上操作后,我们需要替换下,以前默认的实现,
 *     刚好,mybatis-spring-boot上面有告诉我们怎么做,
 *     返回一个 ConfigurationCustomizer 的bean,
 *     通过匿名内部类实现覆盖默认的MapWrapper的findProperty函数
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setObjectWrapperFactory(new MapWrapperFactory());
            }
        };
    }

    /**
     * mybatis 分页插件
     * @return
     */
    @Bean
    public PageHelper  pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("offsetPageNum","true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}


