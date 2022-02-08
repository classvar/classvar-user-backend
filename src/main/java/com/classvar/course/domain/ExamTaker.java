package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@AttributeOverride(name = "participantsId", column = @Column(name = "student_id"))
public class ExamTaker extends AbstractExamParticipant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  private UserRole role;

  private Boolean approved;

  @Column(columnDefinition = "BINARY(16)")
  private UUID examEntranceUUID;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  protected ExamTaker() {
    super(null, null, null, null);
  }

  public ExamTaker(
      String name, String department, String participantsId, String email, UserRole role) {
    super(name, department, participantsId, email);
    this.role = role;
    this.approved = false;
    this.examEntranceUUID = UUID.randomUUID();
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
