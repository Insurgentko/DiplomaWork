package com.it_prom.jet.common.bean;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Route {
    private String boardName;
    private List<RoutePath> path = new ArrayList<>();

    public boolean notAssigned(){
        return  boardName == null;
    }
}
