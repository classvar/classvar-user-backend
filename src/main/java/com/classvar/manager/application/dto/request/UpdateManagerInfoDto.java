package com.classvar.manager.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateManagerInfoDto {

    @NotNull(message = "courseId 필드는 비어 있을 수 없습니다.")
    private Long courseId;

    @NotEmpty(message = "name 필드는 비어 있을 수 없습니다.")
    private String managerName;

    @NotEmpty(message = "studentId 필드는 비어 있을 수 없습니다.")
    private String managerId;

    @NotEmpty(message = "email 필드는 비어 있을 수 없습니다.")
    private String managerEmail;
}
