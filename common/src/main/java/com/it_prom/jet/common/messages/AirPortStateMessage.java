package com.it_prom.jet.common.messages;

import com.it_prom.jet.common.bean.AirPort;
import com.it_prom.jet.common.bean.Source;
import com.it_prom.jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirPortStateMessage extends Message{
    private AirPort airPort;

    public AirPortStateMessage(AirPort airPort) {
        this();
        this.airPort = airPort;
    }

    public AirPortStateMessage() {
        this.source = Source.AIRPORT;
        this.type = Type.STATE;
    }
}
