package com.classvar.examples;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExampleCommandProcessor {

    private ExampleRepository exampleRepository;

    public ExampleCommandProcessor(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Transactional
    public void doExampleOperation() {
        ExampleEntity toCreate = new ExampleEntity("name");
        exampleRepository.save(toCreate);
    }
}
