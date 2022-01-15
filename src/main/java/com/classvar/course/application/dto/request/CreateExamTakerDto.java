package com.classvar.course.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExamTakerDto {
  // collection validation 추가하겠습니다.
  private List<String> emails;
}
