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
  private Set<Exam> exams = new HashSet<>();

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Student> students = new HashSet<>();

  public Course(String name) {
    this.name = name;
  }

  protected Course() {}

  public void changeCourseName(String name) {
    this.name = name;
  }

  public void addExam(Exam exam) {
    exam.setCourse(this);
    this.exams.add(exam);
  }

  public void deleteExam(long examId) {
    if (!this.exams.removeIf(exam -> exam.getId() == examId)) {
      throw new IllegalArgumentException("존재하지 않는 시험 입니다.");
    }
  }

  public void addStudent(Student student) {
    student.setCourse(this);
    this.students.add(student);
  }

  public void approveStudent(long studentId) {
    for (Student student : this.students) {
      if (student.getId() == studentId) {
        student.isVerified();
      }
    }
  }

  public void deleteStudent(long studentId) {
    if (!this.students.removeIf(student -> student.getId() == studentId)) {
      throw new IllegalArgumentException("존재하지 않는 응시자 입니다.");
    }
  }
}
