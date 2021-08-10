package com.classvar.examservice.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
public class Announcement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private String content;

  protected Announcement() {}

  public Announcement(Integer id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  // announcement를 수정하려면 setter밖에 답이 없다.
  // 빈약한 도메인 모델이므로, setter/getter이 되게 큰 비중일 수 밖에 없다.
  public void changeAnnouncement(String title, String content) {
    this.title = title;
    this.content = content;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof Announcement)) return false;
    if (this == another) return true;

    return this.id == ((Announcement) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content);
  }
}
