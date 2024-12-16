package com.it_prom.jet.office.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.it_prom.jet.common.processor.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.TimeUnit;

@Configuration
public class ApplicationConfig {

    @Bean
    public MessageConverter converter() {
        return new MessageConverter();
    }

    @Bean
    public Cache<String, WebSocketSession> sessionCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .build();
    }

}
