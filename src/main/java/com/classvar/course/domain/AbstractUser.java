package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class AbstractUser {
  private String name;

  private String department;

  private String participantsId;

  private String email;

  public AbstractUser(String name, String department, String participantsId, String email) {
    this.name = name;
    this.department = department;
    this.participantsId = participantsId;
    this.email = email;
  }
}
