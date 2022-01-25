package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class AbstractExamParticipant {
  private String name;

  private String department;

  private String participantsId;

  private String email;

  public AbstractExamParticipant(
      String name, String department, String participantsId, String email) {
    this.name = name;
    this.department = department;
    this.participantsId = participantsId;
    this.email = email;
  }
}
