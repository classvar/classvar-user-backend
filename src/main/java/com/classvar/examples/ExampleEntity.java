package com.classvar.examples;

import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExampleEntity extends AbstractAggregateRoot<ExampleEntity> {

    // MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected ExampleEntity() {}

    public ExampleEntity(String name) {
        this.name = name;
        this.registerEvent(new ExampleEntityCreated(this));
    }

    public String getName() {
        return this.name;
    }
}
