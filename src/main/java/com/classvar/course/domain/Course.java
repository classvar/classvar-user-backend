package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  public Course(String name) {
    this.name = name;
  }

  protected Course() {}

  public void changeCourseName(String name) {
    this.name = name;
  }
}
