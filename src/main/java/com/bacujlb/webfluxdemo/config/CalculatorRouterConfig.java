package com.bacujlb.webfluxdemo.config;

import com.bacujlb.webfluxdemo.exception.InputValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class CalculatorRouterConfig {

    private CalculatorHandler calculatorHandler;

    public CalculatorRouterConfig(CalculatorHandler calculatorHandler) {
        this.calculatorHandler = calculatorHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> highLevelCalculatorRouter() {
        return RouterFunctions.route()
                .path("calculator", this::serverResponseRouterCalculator)
                .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterCalculator(){
        return RouterFunctions.route()
                .GET("{a}/{b}", isOperation("+"), calculatorHandler::additionHandler)
                .GET("{a}/{b}", isOperation("-"), calculatorHandler::subtractionHandler)
                .GET("{a}/{b}", isOperation("*"), calculatorHandler::multiplicationHandler)
                .GET("{a}/{b}", isOperation("/"), calculatorHandler::divisionHandler)
                .GET("{a}/{b}", req -> ServerResponse.badRequest().bodyValue("incorrect OP"))
                .build();
    }

    private RequestPredicate isOperation(String operation) {
        return RequestPredicates.headers(headers -> operation.equals(headers.asHttpHeaders()
                                                                        .toSingleValueMap()
                                                                        .get("OP"))
        );
    }
}
