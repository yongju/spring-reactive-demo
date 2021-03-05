package dev.snri.spring.reactive.demo.domain.app;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;

@Entity
@Table("test")
public class Test extends AbstractPersistable<Long> {

}
