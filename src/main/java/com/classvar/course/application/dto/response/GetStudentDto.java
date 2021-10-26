package com.classvar.course.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentDto {
    private Long id;
    private String name;
    private String department;
    private String studentId;
    private String email;
    private Boolean verified;
}
