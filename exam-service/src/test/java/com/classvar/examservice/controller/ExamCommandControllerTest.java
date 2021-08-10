package com.classvar.examservice.controller;

import com.classvar.examservice.application.ExamCommandExecutor;
import com.classvar.examservice.application.ExamQueryProcessor;
import com.classvar.examservice.application.dto.request.CreateOrUpdateAnnouncementDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateQuestionDto;
import com.classvar.examservice.domain.QuestionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class ExamCommandControllerTest {
  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper mapper;
  @MockBean private ExamCommandExecutor executor;
  @MockBean private ExamQueryProcessor processor;

  @Test
  public void createValidAndInvalidExamTest() throws Exception {
    String url = "/exam";
    String examName = "exam1";
    CreateOrUpdateExamDto validDto =
        new CreateOrUpdateExamDto(null, examName, LocalDateTime.now(), LocalDateTime.now());
    String json = mapper.writeValueAsString(validDto);

    mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());

    System.out.println("================================================");

    CreateOrUpdateExamDto invalidDto = new CreateOrUpdateExamDto(null, "", null, null);
    json = mapper.writeValueAsString(invalidDto);

    mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }

  @Test
  public void createValidAndInvalidQuestion() throws Exception {
    String url = "/exam/2/questions";
    String name = "question";
    QuestionType type = QuestionType.CHOICE;
    List<String> desc = Arrays.asList(new String("choice1"), new String("choice2"));
    int point = 5;
    CreateOrUpdateQuestionDto validDto =
        new CreateOrUpdateQuestionDto(null, name, type, desc, point);
    String json = mapper.writeValueAsString(validDto);
    mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());

    System.out.println("================================================");

    CreateOrUpdateQuestionDto invalidDto = new CreateOrUpdateQuestionDto(null, null, null, null, 0);
    json = mapper.writeValueAsString(invalidDto);
    mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }

  @Test
  public void createValidAndInvalidAnnouncementTest() throws Exception {
    String url = "/exam/2/announcements";
    String title = "announcement1";
    String content = "content1";
    CreateOrUpdateAnnouncementDto validDto =
        new CreateOrUpdateAnnouncementDto(null, title, content);
    String json = mapper.writeValueAsString(validDto);
    mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());

    System.out.println("================================================");

    CreateOrUpdateAnnouncementDto invalidDto = new CreateOrUpdateAnnouncementDto(null, null, null);
    json = mapper.writeValueAsString(invalidDto);
    mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
}
