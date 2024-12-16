package com.it_prom.jet.ship.job;

import com.it_prom.jet.common.bean.Board;
import com.it_prom.jet.common.bean.RoutePath;
import com.it_prom.jet.common.messages.BoardStateMessage;
import com.it_prom.jet.common.processor.MessageConverter;
import com.it_prom.jet.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class BoardJob {

    private final BoardProvider boardProvider;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageConverter messageConverter;

    @Scheduled(initialDelay = 0, fixedDelay = 250)
    public void fly(){
        boardProvider.getBoard().stream()
                .filter(Board::hasRoute)
                .forEach(board -> {
                    board.getRoute().getPath().stream().filter(RoutePath::inProgress)
                            .findFirst()
                            .ifPresent(routePath -> {
                                board.calculatePosition(routePath);
                                routePath.addProgress(board.getSpeed());
                                if(routePath.done()){
                                    board.setLocation(routePath.getTo().getName());
                                }
                            });
                });
    }
    
    @Async
    @Scheduled(initialDelay = 0, fixedDelay = 1000)
    public void notifyState(){
        boardProvider.getBoard().stream()
                .filter(Board::isBusy)
                .forEach(board -> {
                    Optional<RoutePath> route = board.getRoute().getPath().stream()
                            .filter(RoutePath::inProgress)
                            .findAny();
                    if(route.isEmpty()){
                        List<RoutePath> path = board.getRoute().getPath();
                        board.setLocation(path.get(path.size()-1).getTo().getName();
                        board.setBusy(false);
                    }

                    BoardStateMessage msg = new BoardStateMessage(board);
                    kafkaTemplate.sendDefault(messageConverter.toJson(msg));
                });
    }
}
