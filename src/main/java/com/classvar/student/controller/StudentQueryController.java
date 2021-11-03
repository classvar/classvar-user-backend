package com.classvar.student.controller;

import com.classvar.student.application.StudentQueryProcessor;
import com.classvar.student.application.dto.response.GetStudentListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentQueryController {

    private final StudentQueryProcessor studentQueryProcessor;

    @GetMapping(value = "/courses/{courseId}/students")
    public ResponseEntity getAllStudentInfo(@PathVariable("courseId") long courseId){
        GetStudentListDto students = studentQueryProcessor.getStudentList(courseId);

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
}

