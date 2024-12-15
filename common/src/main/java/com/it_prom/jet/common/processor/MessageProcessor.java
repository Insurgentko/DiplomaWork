package com.it_prom.jet.common.processor;

import com.it_prom.jet.common.messages.Message;

public interface MessageProcessor<T extends Message> {
    void process(String jsonMessage);
}
