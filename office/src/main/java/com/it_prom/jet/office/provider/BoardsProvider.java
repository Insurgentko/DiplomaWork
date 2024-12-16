package com.it_prom.jet.office.provider;

import com.it_prom.jet.common.bean.Board;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class BoardsProvider {
    private final List<Board> boards = new ArrayList<>();
    private final Lock lock = new ReentrantLock(true);

    public Optional<Board> getBoard(String boardName){
        return boards.stream().filter(board -> board.getName().equals(boardName))
                .findFirst();
    }

    public void addBoard(Board board){
        try {
            lock.lock();
            Optional<Board> optionalBoard = getBoard(board.getName());
            if (optionalBoard.isPresent()){
                int index = boards.indexOf(optionalBoard.get());
                boards.set(index, board);
            }else{
                boards.add(board);
            }
        } finally {
            lock.unlock();
        }
    }

}
