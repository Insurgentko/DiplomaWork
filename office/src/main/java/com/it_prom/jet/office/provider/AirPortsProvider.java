package com.it_prom.jet.office.provider;

import com.it_prom.jet.common.bean.AirPort;
import com.it_prom.jet.common.bean.RoutePoint;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "application")
public class AirPortsProvider {
    private final List<AirPort> ports = new ArrayList<>();

    public AirPort findAirPortAndRemoveBoard(String boardName){
        AtomicReference<AirPort> res = new AtomicReference<>();
        ports.stream().filter(airPort -> airPort.getBoards().contains(boardName))
                .findFirst()
                .ifPresent(airPort -> {
                    airPort.removeBoard(boardName);
                    res.set(airPort);
                });
        return res.get();
    }

    public AirPort getAirPort(String airPortName){
        return ports.stream().filter(airPort -> airPort.getName().equals(airPortName))
                .findFirst()
                .orElse(new AirPort());
    }

    public RoutePoint getRoutePoint(String airPortName){
        return new RoutePoint(getAirPort(airPortName));
    }
}
