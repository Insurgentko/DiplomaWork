package com.it_prom.jet.office.listener;

import com.github.benmanes.caffeine.cache.Cache;
import com.it_prom.jet.common.messages.Message;
import com.it_prom.jet.common.processor.MessageConverter;
import com.it_prom.jet.common.processor.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {

    private final MessageConverter messageConverter;
    private final Cache<String, WebSocketSession> sessionCache;

    @Autowired
    private Map<String, MessageProcessor<? extends Message>> processors = new HashMap<>();

    @KafkaListener(id = "OfficeGroupId", topics = "officeRoutes")
    public void kafkaListen(String data) {
        sendKafkaMessageToSocket(data);
        String code = messageConverter.extractCode(data);

        try {
            processors.get(code).process(data);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private void sendKafkaMessageToSocket(String data) {
        sessionCache.asMap().values().forEach(webSocketSession -> {
            try {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(new TextMessage(data));
                }
            } catch (IOException e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }
}
