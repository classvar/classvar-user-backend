package com.classvar.manager.controller;

import com.classvar.manager.application.ManagerCommandExecutor;
import com.classvar.manager.application.dto.request.CreateManagerDto;
import com.classvar.manager.application.dto.request.DeleteManagersDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerCommandController {

  private final ManagerCommandExecutor managerCommandExecutor;

  @PostMapping(value = "/courses/{courseId}/managers")
  public ResponseEntity createManager(@PathVariable long courseId,
      @RequestBody @Valid CreateManagerDto dto) {
    managerCommandExecutor.createManager(courseId, dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  //다수의 감독관을 지우기 위해 delete 동사를 덧붙여 Post 형태로 받음.
  @PostMapping(value = "/courses/{courseId}/managers/delete")
  public ResponseEntity deleteManagers(@PathVariable long courseId,
      @RequestBody @Valid DeleteManagersDto dto) {
    managerCommandExecutor.deleteManagers(courseId, dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
