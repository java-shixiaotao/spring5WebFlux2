package com.spring5webflux.webflux.webflux;

import com.spring5webflux.webflux.webflux.entity.WebFluxPeach;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class Client {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://127.0.0.1:6209");

        //根据Id查询
        Integer id = 1;
        WebFluxPeach block = webClient.get().uri("/peach/{id}",id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(WebFluxPeach.class).block();
        System.out.println(block.getName());

        //查询所有

        Flux<WebFluxPeach> result = webClient.get().uri("/peachs").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(WebFluxPeach.class);
        result.map(p -> p.getName()).buffer().doOnNext(System.out::println).blockFirst();

    }
}
