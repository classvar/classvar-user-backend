package com.classvar.course.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentInfoDto {

    //NotNull NotEmpty 생각해보기

    @NotEmpty(message = "name 필드는 비어 있을 수 없습니다.")
    private String studentName;

    @NotEmpty(message = "department 필드는 비어 있을 수 없습니다.")
    private String department;

    @NotEmpty(message = "studentId 필드는 비어 있을 수 없습니다.")
    private String studentId;

    @NotEmpty(message = "email 필드는 비어 있을 수 없습니다.")
    private String studentEmail;
}
