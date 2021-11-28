package com.classvar.answer.application;

import com.classvar.answer.application.common.AnswerMapper;
import com.classvar.answer.application.dto.request.AnswerSheetDto;
import com.classvar.answer.application.dto.request.SubmitAnswerDto;
import com.classvar.answer.domain.Answer;
import com.classvar.answer.domain.AnswerRepository;
import com.classvar.answer.domain.AnswerSheet;
import com.classvar.exam.domain.Exam;
import com.classvar.exam.domain.ExamRepository;
import com.classvar.exam.domain.Question;
import com.classvar.exam.domain.QuestionRepository;
import com.classvar.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AnswerCommandExecutor {

  private final ExamRepository examRepository;
  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;
  private final AnswerMapper answerMapper;

  // 응시자는 시험을 여러번 제출할 수 있으며 List<AnswerSubmitDto> 에 있는 답안을 제출한다.
  // 만약 응시자 제출 답안 리스트 중에 이미 제출한 답안이 포함되었을 경우 기존의 답안은 삭제 후 새로운 답안을 만들어 저장한다.
  // 답안 리스트중 제출한 이력이 없는 답안은 새롭게 추가된다.
  @Transactional
  public void submitAnswer(SubmitAnswerDto dto, Student student) {
    final LocalTime currentTime = LocalTime.now();

    Exam exam =
        examRepository
            .findExamById(dto.getExamId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));

    if (exam.getStartTime().isBefore(currentTime)
        && exam.getEndTime().isAfter(currentTime)) { // 응시 시간 내에만 제출 가능

      // 응시자 답안 존재 확인
      Answer answer =
          answerRepository
              .findAnswerWithExamIdAndStudentId(dto.getExamId(), student.getId())
              .orElse(answerMapper.toAnswer(student, dto.getExamId()));

      answer.recordSubmitTime(); // 답안 제출 시간 기록

      for (AnswerSheetDto answerSheetDto : dto.getAnswers()) {
        Question question =
            questionRepository
                .findById(answerSheetDto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제 입니다."));

        AnswerSheet answerSheet = answerMapper.toAnswerSheet(answerSheetDto.getAnswer());

        answer.addOrUpdateAnswerSheet(question, answerSheet);
      }

      answerRepository.save(answer);
    }
  }
}
