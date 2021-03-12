package dev.snri.spring.reactive.demo.web.demo;

import dev.snri.spring.reactive.demo.service.app.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DemoHandler {

    @Autowired
    private TestService testService;

    public Mono<ServerResponse> test(ServerRequest request) {
        return ServerResponse.ok().body(testService.findAll(), Object.class);
    }
}
