package com.classvar.scoreservice.application;

import com.classvar.scoreservice.application.common.EntityMapper;
import com.classvar.scoreservice.application.dto.GetScoreWithTotalPointDto;
import com.classvar.scoreservice.application.dto.request.AnswerDto;
import com.classvar.scoreservice.application.dto.response.GetAnswerDto;
import com.classvar.scoreservice.application.dto.response.GetSubmittedAnswerDto;
import com.classvar.scoreservice.application.dto.response.TempQuestionDto;
import com.classvar.scoreservice.application.dto.response.TempUserDto;
import com.classvar.scoreservice.domain.Answer;
import com.classvar.scoreservice.domain.AnswerRepository;
import com.classvar.scoreservice.domain.SubmittedAnswer;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreQueryProcessor {

  private AnswerRepository answerRepository;
  private EntityMapper entityMapper;

  public ScoreQueryProcessor(AnswerRepository answerRepository, EntityMapper entityMapper) {
    this.answerRepository = answerRepository;
    this.entityMapper = entityMapper;
  }

  public List<GetAnswerDto getSubmittedAnswerWithCourseIdAndExamIdAndTempUserId(int answerId) {
    Answer answer =
        answerRepository
            .findById(answerId)
            .orElseThrow(() -> new IllegalArgumentException("채점 데이터가 존재하지 않습니다."));

    GetAnswerDto getAnswerDto = new GetAnswerDto();
    List<GetSubmittedAnswerDto> getSubmittedAnswerDtoList = new ArrayList<>();

    for (SubmittedAnswer submittedAnswer : answer.getSubmittedAnswers()) {
      GetSubmittedAnswerDto getSubmittedAnswerDto = new GetSubmittedAnswerDto();

      // exam-service에 API요청
      TempQuestionDto tempQuestionDto =
          getTempQuestionDto(answer.getExamId(), submittedAnswer.getQuestionId());

      // Dto Mapping하는 부분
      getSubmittedAnswerDtoList.add(
          entityMapper.toGetSubmittedAnswerDto(tempQuestionDto, submittedAnswer.getAnswer()));
    }

    getAnswerDto.setId(answer.getId());
    getAnswerDto.setSubmittedAnswers(getSubmittedAnswerDtoList);

    return getAnswerDto;
  }

  public List<GetAnswerDto> getAnswerListWithExamId(int examId) {
    List<Answer> answers = answerRepository.findAllAnswerWithExamId(examId);

    List<GetAnswerDto> getAnswerDtoList = new ArrayList<>();

    for (Answer answer : answers) {
      GetAnswerDto getAnswerDto = new GetAnswerDto();
      List<GetSubmittedAnswerDto> getSubmittedAnswerDtoList = new ArrayList<>();

      for (SubmittedAnswer submittedAnswer : answer.getSubmittedAnswers()) {
        GetSubmittedAnswerDto getSubmittedAnswerDto = new GetSubmittedAnswerDto();

        // exam-service에 API요청
        TempQuestionDto tempQuestionDto =
            getTempQuestionDto(answer.getExamId(), submittedAnswer.getQuestionId());

        // Dto Mapping하는 부분
        getSubmittedAnswerDtoList.add(
            entityMapper.toGetSubmittedAnswerDto(tempQuestionDto, submittedAnswer.getAnswer()));
      }

      getAnswerDto.setId(answer.getId());
      getAnswerDto.setSubmittedAnswers(getSubmittedAnswerDtoList);

      getAnswerDtoList.add(getAnswerDto);
    }

    return getAnswerDtoList;
  }

  public AnswerDto getAnswerWithExamId(int examId) {
    return answerRepository.findByExamId(examId).stream()
        .map(score -> entityMapper.toScoreDto(score))
        .collect(Collectors.toList());
  }

  public List<GetScoreWithTotalPointDto> getScoreResultWithExamId(int examId) {
    return answerRepository.findByExamIdOrderByTotalScoreDesc(examId).stream()
        .map(score -> entityMapper.toGetScoreWithTotalPointDto(score))
        .collect(Collectors.toList());
  }

  public List<GetScoreWithTotalPointDto> getScoreResultFileWithExamId(int examId) {
    return answerRepository.findByExamIdOrderByTotalScoreDesc(examId).stream()
        .map(score -> entityMapper.toGetScoreWithTotalPointDto(score))
        .collect(Collectors.toList());
  }

  private List<TempUserDto> getTempUserDtoList(int courseId) {
    String url = String.format("http://courses/%s/temp_users", courseId);

    ResponseEntity<List<TempUserDto>> responseData =
        restTemplate.exchange(url, HttpMethod.GET, null, TempUserDto.class);
    return responseData.getBody();
  }

  public TempQuestionDto getTempQuestionDto(int examId, int questionId) {
    //    String url = String.format("http://exam/%s/questions/%s", examId, questionId);
    //
    //    ResponseEntity<TempQuestionDto> responseData =
    //        restTemplate.exchange(url, HttpMethod.GET, null, TempQuestionDto.class);
    //    return responseData.getBody();
    return new TempQuestionDto(questionId, "문제", "", null, 0);
  }

  private List<TempQuestionDto> getTempQuestionDtoList(int examId, int questionId) {
    List<GetAnswerDto> answerDtoList = new ArrayList<>();

    for (SubmittedAnswer submittedAnswer : submittedAnswers) {
      // exam-service에 QuestionId로 조회요청 ex) /exam/{examId}/questions/{questionId}
      TempQuestionDto tempQuestionDto = new TempQuestionDto(0, "문제", "", null, 0);

      GetAnswerDto answerDto =
          new GetAnswerDto(
              tempQuestionDto.getName(),
              tempQuestionDto.getDescription(),
              submittedAnswer.getAnswer(),
              tempQuestionDto.getPoint());
      answerDtoList.add(answerDto);
    }
    return answerDtoList;
  }

  TempQuestionDto tempQuestionDto = new TempQuestionDto(0, "문제", "CHOICE", null, 10);
}
