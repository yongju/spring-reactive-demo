package dev.snri.spring.reactive.demo.web.controller;

import dev.snri.spring.reactive.demo.domain.app.Test;
import dev.snri.spring.reactive.demo.service.app.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public Flux<Test> test() {
        return testService.findAll();
    }
}
