package com.bacujlb.webfluxdemo.config;

import com.bacujlb.webfluxdemo.dto.InputFailedValidationResponse;
import com.bacujlb.webfluxdemo.exception.InputValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    private RequestHandler requestHandler;

    public RouterConfig(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> highLevel() {
        return RouterFunctions.route()
                    .path("router1", this::serverResponseHighLevel)
                    .build();
    }
    private RouterFunction<ServerResponse> serverResponseHighLevel(){
        return RouterFunctions.route()
                .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")), requestHandler:: squareHandler)
                .GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("only 10-19 allowed"))
                .GET("table/{input}", requestHandler::tableHandler)
                .GET("table/{input}/stream", requestHandler::tableStreamHandler)
                .POST("multiply", requestHandler::multiplyHandler)
                .GET("square/{input}/validation", requestHandler:: squareWithValidationHandler)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                    .GET("router/square/{input}", requestHandler:: squareHandler)
                    .GET("router/table/{input}", requestHandler::tableHandler)
                    .GET("router/table/{input}/stream", requestHandler::tableStreamHandler)
                    .POST("router/multiply", requestHandler::multiplyHandler)
                    .GET("router/square/{input}/validation", requestHandler:: squareWithValidationHandler)
                    .onError(InputValidationException.class, exceptionHandler())
                    .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return (err, req) -> {
            InputValidationException ex = (InputValidationException) err;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
