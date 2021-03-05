package dev.snri.spring.reactive.demo.service.app.test;

import dev.snri.spring.reactive.demo.domain.app.Test;
import reactor.core.publisher.Flux;

public interface TestService {

    Flux<Test> findAll();
}
