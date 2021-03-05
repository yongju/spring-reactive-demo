package dev.snri.spring.reactive.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private ReactiveRedisOperations<String, String> reactiveRedisOperations;

    @RequestMapping("/{path}")
    public Mono test(@PathVariable String path) {
        return Mono.empty().thenReturn("hello " + path);
    }
}
