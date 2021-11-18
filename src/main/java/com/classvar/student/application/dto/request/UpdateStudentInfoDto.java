package com.classvar.student.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentInfoDto {

    @NotNull(message = "courseId 필드는 비어 있을 수 없습니다.")
    private Long courseId;

    @NotEmpty(message = "name 필드는 비어 있을 수 없습니다.")
    private String studentName;

    @NotEmpty(message = "department 필드는 비어 있을 수 없습니다.")
    private String department;

    @NotEmpty(message = "studentId 필드는 비어 있을 수 없습니다.")
    private String studentId;

    @NotEmpty(message = "email 필드는 비어 있을 수 없습니다.")
    private String studentEmail;
}
