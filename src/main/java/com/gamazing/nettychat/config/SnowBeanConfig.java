package com.gamazing.nettychat.config;

import com.gamazing.nettychat.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowBeanConfig {

    @Bean
    public IdWorker getIdWorker() {
        return new IdWorker(0, 0);
    }
}
