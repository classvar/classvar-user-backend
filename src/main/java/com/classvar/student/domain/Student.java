package com.classvar.student.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String department;
  private String studentId;
  private String email;
  private Boolean verified;

  private String uuid;

  private Long courseId;

  protected Student() {}

  public Student(Long courseId, String email) {
    this.courseId = courseId;
    this.email = email;
    this.uuid = UUID.randomUUID().toString();
    this.verified = false;
  }

  public void updateStudentInfo(String name, String department, String studentId, String email) {
    this.name = name;
    this.department = department;
    this.studentId = studentId;
    this.email = email;
  }


  public void verified() {
    verified = true;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof Student)) return false;
    if (this == another) return true;

    return this.id == ((Student) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.department, this.studentId, this.uuid);
  }
}
