package com.classvar.student.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentsDto {

    @NotNull(message = "courseId 필드는 비어 있을 수 없습니다.")
    private Long courseId;

    //collection validation 추가하겠습니다.
    private List<String> emails;
}
