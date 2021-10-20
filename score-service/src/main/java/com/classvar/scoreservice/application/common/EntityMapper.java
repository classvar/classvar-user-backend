package com.classvar.scoreservice.application.common;

import com.classvar.scoreservice.application.dto.GetScoreWithTotalPointDto;
import com.classvar.scoreservice.application.dto.request.SubmittedAnswerDto;
import com.classvar.scoreservice.application.dto.response.GetAnswerDto;
import com.classvar.scoreservice.application.dto.response.GetSubmittedAnswerDto;
import com.classvar.scoreservice.application.dto.response.TempQuestionDto;
import com.classvar.scoreservice.domain.Answer;
import com.classvar.scoreservice.domain.SubmittedAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  @Mapping(target = "totalScore", ignore = true)
  Answer toAnswer(Answer dto);

  SubmittedAnswerDto toScoreDto(Answer answer);

  GetScoreWithTotalPointDto toGetScoreWithTotalPointDto(Answer answer);

  // totalScore = 0
  SubmittedAnswer toSubmittedAnswer(SubmittedAnswerDto dto);

  default GetAnswerDto toGetAnswerDto(Answer answer) {
    GetAnswerDto getAnswerDto = new GetAnswerDto();
    List<GetSubmittedAnswerDto> getSubmittedAnswerDtoList = new ArrayList<>();

    for (SubmittedAnswer submittedAnswer : answer.getSubmittedAnswers()) {
      GetSubmittedAnswerDto getSubmittedAnswerDto = new GetSubmittedAnswerDto();

      // exam-service에 API요청
      TempQuestionDto tempQuestionDto =
          getTempQuestionDto(answer.getExamId(), submittedAnswer.getQuestionId());

      // Dto Mapping하는 부분
      getSubmittedAnswerDtoList.add(
          toGetSubmittedAnswerDto(tempQuestionDto, submittedAnswer.getAnswer()));
    }

    getAnswerDto.setId(answer.getId());
    getAnswerDto.setSubmittedAnswers(getSubmittedAnswerDtoList);

    return getAnswerDto;
  }

  default GetSubmittedAnswerDto toGetSubmittedAnswerDto(
      TempQuestionDto tempQuestionDto, String answer) {
    return new GetSubmittedAnswerDto(
        tempQuestionDto.getId(),
        tempQuestionDto.getName(),
        tempQuestionDto.getDescription(),
        answer);
  }

  default TempQuestionDto getTempQuestionDto(int examId, int questionId) {
    String url = String.format("http://exam/%s/questions/%s", examId, questionId);

    ResponseEntity<TempQuestionDto> responseData =
        restTemplate.exchange(url, HttpMethod.GET, null, TempQuestionDto.class);
    return responseData.getBody();
    return new TempQuestionDto(questionId, "문제", "", null, 0);
  }
}
