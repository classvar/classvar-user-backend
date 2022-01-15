package com.classvar.course.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class User {
  private String name;

  private String department;

  private String email;

  private String uuid;
}
