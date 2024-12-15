package com.it_prom.jet.common.messages;

import com.it_prom.jet.common.bean.Board;
import com.it_prom.jet.common.bean.Route;
import com.it_prom.jet.common.bean.Source;
import com.it_prom.jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeRouteMessage extends Message {
    private Route route;

    public OfficeRouteMessage(Route val) {
        this();
        this.route = val;
    }

    public OfficeRouteMessage() {
        this.source = Source.OFFICE;
        this.type = Type.ROUTE;
    }
}
