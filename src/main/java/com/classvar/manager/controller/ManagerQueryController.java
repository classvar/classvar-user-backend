package com.classvar.manager.controller;

import com.classvar.manager.application.ManagerQueryProcessor;
import com.classvar.manager.application.dto.response.GetManagerListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerQueryController {

  private final ManagerQueryProcessor managerQueryProcessor;

  @GetMapping(value = "/courses/{courseId}/managers")
  public ResponseEntity getAllManagerInfo(@PathVariable long courseId) {
    GetManagerListDto managersInfo = managerQueryProcessor.getManagerList(courseId);
    return ResponseEntity.status(HttpStatus.OK).body(managersInfo);
  }
}
