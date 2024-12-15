package com.it_prom.jet.common.messages;

import com.it_prom.jet.common.bean.Board;
import com.it_prom.jet.common.bean.Source;
import com.it_prom.jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardStateMessage extends Message{
    private Board board;

    public BoardStateMessage(Board val) {
        this();
        this.board = val;
    }

    public BoardStateMessage() {
        this.source = Source.BOARD;
        this.type = Type.STATE;
    }
}
