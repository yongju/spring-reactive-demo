package dev.snri.spring.demo.domain.demo;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class Test extends AbstractPersistable<Long> {

}
