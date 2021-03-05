package dev.snri.spring.reactive.demo.domain.app;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class Test extends AbstractPersistable<Long> {

}
