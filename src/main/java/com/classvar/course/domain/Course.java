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

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ExamTaker> examTakers = new HashSet<>();

  public Course(String name) {
    this.name = name;
  }

  protected Course() {}

  public void changeCourseName(String name) {
    this.name = name;
  }

  public void addExamTaker(ExamTaker examTaker) {
    examTaker.setCourse(this);
    this.examTakers.add(examTaker);
  }

  public void removeExamTaker(Long examTakerId) {
    if (!this.examTakers.removeIf(examTaker -> examTaker.getId() == examTakerId)) {
      throw new IllegalArgumentException("존재하지 않는 응시자 입니다.");
    }
  }

  public void approveExamTaker(Long examTakerId) {
    for (ExamTaker examTaker : this.examTakers) {
      if (examTaker.getId() == examTakerId) {
        examTaker.setApproved();
        return;
      }
    }
    throw new IllegalArgumentException("존재하지 않는 응시자 입니다.");
  }
}
