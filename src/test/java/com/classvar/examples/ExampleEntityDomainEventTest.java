package com.classvar.examples;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ExampleEntityDomainEventTest {

    @Autowired
    ExampleCommandProcessor processor;

    @MockBean
    ExampleEventListener listener;

    @Test
    public void DomainEventIsPublishedAfterTransaction() {
        processor.doExampleOperation();

        verify(listener, times(1)).handle(Mockito.isA(ExampleEntityCreated.class));
    }
}
