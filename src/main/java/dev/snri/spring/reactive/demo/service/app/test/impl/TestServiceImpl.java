package dev.snri.spring.reactive.demo.service.app.test.impl;

import dev.snri.spring.reactive.demo.domain.app.Test;
import dev.snri.spring.reactive.demo.repositories.app.reactive.TestReactiveRepository;
import dev.snri.spring.reactive.demo.service.app.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestReactiveRepository testReactiveRepository;

    @Override
    public Flux<Test> findAll() {
        return testReactiveRepository.findAll();
    }
}
