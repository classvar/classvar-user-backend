package com.classvar.error.exception;

import com.classvar.error.exception.policy.BusinessExceptionPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessExceptionReason implements BusinessExceptionPolicy {

  //기본 코드만 표시하고 싶을 때
  //  "message": "No such id"
  NO_SUCH_ID("No such id", HttpStatus.BAD_REQUEST),

  //예외 발생 이유를 상세하게 표시하고 싶을 때
  //new BusinessException(BusinessExceptionReason.NO_SUCH_ID_DETAIL, "지울 ID의 시험이 없습니다.");
  //Output) "message": "No such id: 지울 ID의 시험이 없습니다."
  NO_SUCH_ID_DETAIL("No such id: %s", HttpStatus.BAD_REQUEST);

  private final String code = BusinessExceptionReason.class.getSimpleName();
  private final String message;
  private final HttpStatus httpStatus;
}
