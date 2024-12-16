package com.it_prom.jet.ship.listener.processor;

import com.it_prom.jet.common.bean.Route;
import com.it_prom.jet.common.messages.OfficeRouteMessage;
import com.it_prom.jet.common.processor.MessageConverter;
import com.it_prom.jet.common.processor.MessageProcessor;
import com.it_prom.jet.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_ROUTE")
@RequiredArgsConstructor
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {

    private final BoardProvider boardProvider;
    private final MessageConverter messageConverter;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        OfficeRouteMessage msg = messageConverter.extractMessage(jsonMessage, OfficeRouteMessage.class);
        Route route = msg.getRoute();
        boardProvider.getBoard().stream()
                .filter(board -> board.noBusy() && route.getBoardName().equals(board.getName()))
                .findFirst()
                .ifPresent(board -> {
                    board.setRoute(route);
                    board.setBusy(true);
                    board.setLocation(null);
                });
    }
}
