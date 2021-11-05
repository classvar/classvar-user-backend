package com.classvar.manager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Manager {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String email;

  private String managerId;

  private Long courseId;

  protected Manager() {
  }

  public Manager(Long courseId, String email, String managerId) {
    this.courseId = courseId;
    this.email = email;
    this.managerId = managerId;
  }

  public void updateManagerInfo(String email, String managerId) {
    this.email = email;
    this.managerId = managerId;
  }
}
