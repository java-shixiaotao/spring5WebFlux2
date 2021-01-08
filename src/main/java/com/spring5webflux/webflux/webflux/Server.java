package com.spring5webflux.webflux.webflux;

import com.spring5webflux.webflux.webflux.handler.WebFluxPeachHandler;
import com.spring5webflux.webflux.webflux.service.WebFluxPeachService;
import com.spring5webflux.webflux.webflux.service.impl.WebFluxPeachServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.creatReactorToServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    //1.创建路由Router
    public RouterFunction<ServerResponse> routingFunction(){
        WebFluxPeachService fluxPeachService = new WebFluxPeachServiceImpl();
        WebFluxPeachHandler handler = new WebFluxPeachHandler(fluxPeachService);

       return RouterFunctions.route(GET("/peach/{id}").and(accept(APPLICATION_JSON)),handler::getById)
                        .andRoute(GET("/peachs").and(accept(APPLICATION_JSON)),handler::getAllPeachs);
    }
    //2.创建服务器完成适配
    public void creatReactorToServer(){
        //路由和适配器
        RouterFunction<ServerResponse> router = routingFunction();
        HttpHandler httpHandler = toHttpHandler(router);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }
}
