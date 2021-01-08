package com.spring5webflux.webflux.webflux.service.impl;

import com.spring5webflux.webflux.webflux.entity.WebFluxPeach;
import com.spring5webflux.webflux.webflux.service.WebFluxPeachService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebFluxPeachServiceImpl implements WebFluxPeachService {

    private final Map<Integer,WebFluxPeach> map = new HashMap<>();

    public WebFluxPeachServiceImpl(){
        map.put(1,new WebFluxPeach("桃子1号",21));
        map.put(2,new WebFluxPeach("桃子2号",22));
    }

    /**
     * 通过Id获取
     *
     * @param id
     * @return
     */
    @Override
    public Mono<WebFluxPeach> getById(Integer id) {
        return Mono.justOrEmpty(this.map.get(id));
    }

    /**
     * 返回所有的
     *
     * @return
     */
    @Override
    public Flux<WebFluxPeach> getPeachs() {
        return Flux.fromIterable(map.values());
    }

    /**
     * 添加桃子
     *
     * @param peachMono
     * @return
     */
    @Override
    public Mono<Void> savePeach(Mono<WebFluxPeach> peachMono) {
        peachMono.doOnNext((peach) -> {
            int id = this.map.size() + 1;
            map.put(id,peach);
        }).thenEmpty(Mono.empty());
        return null;
    }
}
