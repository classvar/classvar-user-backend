package com.classvar.admin.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;

  private String name;
  private String department;

  private String salt;

  public Admin(String email, String password, String name, String department, String salt) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.department = department;
    this.salt = salt;
  }

  protected Admin() {}
}
