package com.it_prom.jet.ship.listener.processor;

import com.it_prom.jet.common.messages.BoardStateMessage;
import com.it_prom.jet.common.processor.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BOARD_STATE")
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {

    @Override
    public void process(String jsonMessage) {
        //IGNORE
    }
}
