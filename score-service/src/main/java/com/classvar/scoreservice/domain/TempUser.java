package com.classvar.scoreservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class TempUser {
  private int userId;
  private String name;
  private String department;
  private String email;

  public TempUser(int userId, String name, String department, String email) {
    this.userId = userId;
    this.name = name;
    this.department = department;
    this.email = email;
  }

  protected TempUser() {}
}
