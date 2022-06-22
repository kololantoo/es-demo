package com.kololantoo.esdemo.config;

import com.kololantoo.esdemo.utils.SnowflakeIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhengyang
 * @date 2022/6/22
 * @description
 */
@Component
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeIdUtil snowflakeIdUtil() {
        return new SnowflakeIdUtil(1,1);
    }
}
