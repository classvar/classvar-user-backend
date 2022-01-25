package com.classvar.manager.domain;

import lombok.Getter;

@Getter
public class ManagerCreatedEvent {
    private final Manager manager;

    public ManagerCreatedEvent(Manager manager) {
        this.manager= manager;
    }
}
