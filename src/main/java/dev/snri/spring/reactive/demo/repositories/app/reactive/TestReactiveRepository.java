package dev.snri.spring.reactive.demo.repositories.app.reactive;

import dev.snri.spring.reactive.demo.domain.app.Test;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestReactiveRepository extends ReactiveSortingRepository<Test, Long> {

}
