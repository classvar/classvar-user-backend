package com.classvar.student.domain;

import lombok.Getter;

@Getter
public class StudentCreatedEvent {
    private final Student student;

    public StudentCreatedEvent(Student student) {
        this.student = student;
    }
}