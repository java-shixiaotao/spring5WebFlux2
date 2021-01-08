package com.spring5webflux.webflux.webflux.service;


import com.spring5webflux.webflux.webflux.entity.WebFluxPeach;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface WebFluxPeachService {

    /**
     * 通过Id获取
     * @param id
     * @return
     */
    Mono<WebFluxPeach> getById(Integer id);

    /**
     * 返回所有的
     * @return
     */
    Flux<WebFluxPeach> getPeachs();

    /**
     * 添加桃子
     * @param peachMono
     * @return
     */
    Mono<Void> savePeach(Mono<WebFluxPeach> peachMono);

}
