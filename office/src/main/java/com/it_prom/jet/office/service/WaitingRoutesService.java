package com.it_prom.jet.office.service;

import com.it_prom.jet.common.bean.Route;
import com.it_prom.jet.common.bean.RoutePath;
import com.it_prom.jet.common.bean.RoutePoint;
import com.it_prom.jet.office.provider.AirPortsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class WaitingRoutesService {
    private final AirPortsProvider airPortsProvider;
    private final Lock lock = new ReentrantLock(true);
    private final List<Route> list = new ArrayList<>();

    public List<Route> list(){
        return list;
    }
    public void add(Route route){
        try {
            lock.lock();
            list.add(route);
        } finally {
            lock.unlock();
        }
    }

    public void remove(Route route){
        try {
            lock.lock();
            list.removeIf(route::equals);
        } finally {
            lock.unlock();
        }
    }

}
