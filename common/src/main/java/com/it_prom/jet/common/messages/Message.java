package com.it_prom.jet.common.messages;

import com.it_prom.jet.common.bean.Source;
import com.it_prom.jet.common.bean.Type;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {
    protected Type type;
    protected Source source;

    public String getCode(){
        return source.name() + "_" + type.name();
    }
}
