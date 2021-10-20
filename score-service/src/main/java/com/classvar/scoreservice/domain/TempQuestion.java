package com.classvar.scoreservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Getter
public class TempQuestion {
  private int id;
  private String name;
  private String type;
  private List<String> description;
  private int point;

  public TempQuestion(Integer id, String name, List<String> description, int point) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.point = point;
  }

  protected TempQuestion() {}
}
