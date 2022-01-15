package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@AttributeOverrides({
  @AttributeOverride(name = "uuid", column = @Column(name = "EXAM_TAKER_UUID")),
  @AttributeOverride(name = "role", column = @Column(name = "EXAM_TAKER_ROLE"))
})
public class ExamTaker extends User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String studentId;

  private UserRole role;

  private Boolean approved;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  protected ExamTaker() {}

  public ExamTaker(String name, String department, String studentId, String email) {
    this.setName(name);
    this.setDepartment(department);
    this.studentId = studentId;
    this.setEmail(email);
    this.setUuid(UUID.randomUUID().toString());
    this.approved = false;
    this.role = UserRole.ExamTaker;
  }

  public void updateExamTakerInfo(ExamTaker updatePayload) {
    this.setName(updatePayload.getName());
    this.setDepartment(updatePayload.getDepartment());
    this.setEmail(updatePayload.getEmail());
    this.studentId = updatePayload.getStudentId();
  }

  public void setApproved() {
    this.approved = true;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof ExamTaker)) return false;
    if (this == another) return true;

    return this.id == ((ExamTaker) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }
}
