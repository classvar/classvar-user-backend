package com.classvar.exam.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentExamAnswerDto {

  private GetStudentInfoDto student;

  private List<GetAnswerDto> answers;
}
