package com.bacujlb.webfluxdemo.controller;

import com.bacujlb.webfluxdemo.dto.Response;
import com.bacujlb.webfluxdemo.exception.InputValidationException;
import com.bacujlb.webfluxdemo.service.ReactiveMathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input){
        if (input < 10 || input > 20){
            throw new InputValidationException(input);
        }
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input){
        return Mono.just(input)
                    .handle((integer, responseSynchronousSink) -> {
                        if(integer >= 10 && integer <= 20){
                            responseSynchronousSink.next(integer);
                        } else {
                            responseSynchronousSink.error(new InputValidationException(integer));
                        }
                    })
                    .cast(Integer.class)
                    .flatMap(i -> this.reactiveMathService.findSquare(i));

    }
}
