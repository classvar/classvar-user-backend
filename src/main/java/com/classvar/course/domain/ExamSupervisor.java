package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@AttributeOverride(name = "participantsId", column = @Column(name = "MANAGER_ID"))
public class ExamSupervisor extends AbstractUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  private UserRole role;

  private String uuid;

  private Boolean approved;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  protected ExamSupervisor() {
    super(null, null, null, null);
  }

  public ExamSupervisor(
      String name, String department, String participantsId, String email, UserRole role) {
    super(name, department, participantsId, email);
    this.role = role;
    this.approved = false;
    this.uuid = UUID.randomUUID().toString();
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
    if (!(another instanceof ExamSupervisor)) return false;
    if (this == another) return true;

    return this.id == ((ExamSupervisor) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.uuid);
  }
}
