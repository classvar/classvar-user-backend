package com.classvar.user.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;

  private String name;
  private String department;

  public User(String email, String password, String name, String department) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.department = department;
  }

  protected User() {}
}
