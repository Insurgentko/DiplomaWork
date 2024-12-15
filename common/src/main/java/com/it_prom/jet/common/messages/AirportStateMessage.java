package com.it_prom.jet.common.messages;

import com.it_prom.jet.common.bean.Airport;
import com.it_prom.jet.common.bean.Source;
import com.it_prom.jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportStateMessage extends Message{
    private Airport airport;

    public AirportStateMessage(Airport airport) {
        this();
        this.airport = airport;
    }

    public AirportStateMessage() {
        this.source = Source.AIRPORT;
        this.type = Type.STATE;
    }
}
