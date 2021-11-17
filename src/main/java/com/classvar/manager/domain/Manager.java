package com.classvar.manager.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
public class Manager {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String name;
  private String email;
  private String managerId;
  private Long courseId;
  private Boolean verified;

  private String uuid;

  protected Manager() {
  }

  public Manager(Long courseId, String email) {
    this.email = email;
    this.courseId = courseId;
    this.uuid = UUID.randomUUID().toString();
    this.verified = false;
  }

  public void updateManagerInfo(String name, String managerId, String email) {
    this.name = name;
    this.managerId = managerId;
    this.email = email;
  }

  public void verified(){
    this.verified = true;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof Manager)) return false;
    if (this == another) return true;

    return this.id == ((Manager) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.managerId, this.uuid);
  }
}
