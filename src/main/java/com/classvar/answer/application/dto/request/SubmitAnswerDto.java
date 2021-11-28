package com.classvar.answer.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitAnswerDto {

  private long examId;

  private List<AnswerSheetDto> answers;
}
