package com.classvar.examservice.controller;

import com.classvar.examservice.application.ExamQueryProcessor;
import com.classvar.examservice.application.dto.response.GetAnnouncementDto;
import com.classvar.examservice.application.dto.response.GetExamDto;
import com.classvar.examservice.application.dto.response.GetQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamQueryController {
  @Autowired private ExamQueryProcessor examQueryProcessor;

  @GetMapping("/exam/{examId}")
  public ResponseEntity<GetExamDto> GetExamDetail(@PathVariable int examId) {
    GetExamDto getExam = examQueryProcessor.getExamWithQuestionAndAnnouncement(examId);
    return new ResponseEntity<>(getExam, HttpStatus.OK);
  }

  @GetMapping("/exam/{examId}/questions/{questionId}")
  public ResponseEntity<GetQuestionDto> GetQuestionDetail(
      @PathVariable int examId, @PathVariable int questionId) {
    GetQuestionDto getQuestion = examQueryProcessor.getQuestionFromExamWithId(examId, questionId);
    return new ResponseEntity<>(getQuestion, HttpStatus.OK);
  }

  @GetMapping("/exam/{examId}/questions")
  public ResponseEntity<List<GetQuestionDto>> GetQuestionList(@PathVariable int examId) {
    List<GetQuestionDto> getQuestionList = examQueryProcessor.getQuestionListFromExamWithId(examId);
    return new ResponseEntity<List<GetQuestionDto>>(getQuestionList, HttpStatus.OK);
  }

  @GetMapping("/exam/{examId}/announcements")
  public ResponseEntity<List<GetAnnouncementDto>> GetAnnouncementList(@PathVariable int examId) {
    List<GetAnnouncementDto> getAnnouncementList =
        examQueryProcessor.getAnnouncementListFromExamWithId(examId);
    return new ResponseEntity<List<GetAnnouncementDto>>(getAnnouncementList, HttpStatus.OK);
  }
}
