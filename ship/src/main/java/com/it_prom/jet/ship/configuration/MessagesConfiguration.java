package com.it_prom.jet.ship.configuration;

import com.it_prom.jet.common.processor.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagesConfiguration {
    @Bean
    public MessageConverter converter(){
        return new MessageConverter();
    }
}
