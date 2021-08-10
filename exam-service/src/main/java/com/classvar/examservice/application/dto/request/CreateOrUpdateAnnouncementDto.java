package com.classvar.examservice.application.dto.request;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateOrUpdateAnnouncementDto {

  private Integer id;

  @NotBlank(message = "공지사항 제목에 빈 문자열은 허용하지 않습니다.")
  private String title;

  @Nullable private String content;
}
