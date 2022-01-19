package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class AbstractUser {
  private String name;

  private String department;

  private String email;

  private String uuid;

  public AbstractUser(String name, String department, String email, String uuid) {
    this.name = name;
    this.department = department;
    this.email = email;
    this.uuid = uuid;
  }
}
