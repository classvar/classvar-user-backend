package com.classvar.scoreservice.utils;

import com.classvar.scoreservice.application.dto.AnswerSheetDto;
import com.classvar.scoreservice.application.dto.GetScoreWithTotalPointDto;
import com.classvar.scoreservice.application.dto.ScoreDto;
import com.classvar.scoreservice.domain.AnswerSheet;
import com.classvar.scoreservice.domain.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  @Mapping(target = "totalScore", ignore = true)
  Score toScore(ScoreDto dto);

  ScoreDto toScoreDto(Score score);

  GetScoreWithTotalPointDto toGetScoreWithTotalPointDto(Score score);

  AnswerSheet toAnswerSheet(AnswerSheetDto dto);

  AnswerSheetDto toAnswerSheetDto(AnswerSheet answerSheet);
}
