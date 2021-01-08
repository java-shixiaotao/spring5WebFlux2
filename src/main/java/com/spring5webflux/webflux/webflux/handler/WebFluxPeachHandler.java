package com.spring5webflux.webflux.webflux.handler;

import com.spring5webflux.webflux.webflux.entity.WebFluxPeach;
import com.spring5webflux.webflux.webflux.service.WebFluxPeachService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

public class WebFluxPeachHandler {

    private final WebFluxPeachService webFluxPeachService;

    public WebFluxPeachHandler(WebFluxPeachService webFluxPeachService){
        this.webFluxPeachService = webFluxPeachService;
    }

    /**
     * 通过Id获取值
     * @param request
     * @return
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> build = ServerResponse.notFound().build();
        Mono<WebFluxPeach> byId = webFluxPeachService.getById(id);
        return byId.flatMap(peach -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(peach)))
                .switchIfEmpty(build);
    }

    public Mono<ServerResponse> getAllPeachs(ServerRequest requet){
        Flux<WebFluxPeach> peachs = webFluxPeachService.getPeachs();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(peachs,WebFluxPeach.class);
    }

    public Mono<ServerResponse> savePeahc(ServerRequest request){
        Mono<WebFluxPeach> peachMono = request.bodyToMono(WebFluxPeach.class);
        return ServerResponse.ok().build(this.webFluxPeachService.savePeach(peachMono));
    }

}




