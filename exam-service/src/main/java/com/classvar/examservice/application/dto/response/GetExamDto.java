package com.classvar.examservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetExamDto {
  private String name;
  private LocalDateTime beginAt;
  private LocalDateTime endAt;
  private int questionAmount;
  private List<GetQuestionDto> questions;
  private List<GetAnnouncementDto> announcements;
}
