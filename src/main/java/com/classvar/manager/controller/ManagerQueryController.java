package com.classvar.manager.controller;

import com.classvar.manager.application.ManagerQueryProcessor;
import com.classvar.manager.application.dto.response.GetManagerListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "감독관 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerQueryController {

  private final ManagerQueryProcessor managerQueryProcessor;

  @ApiOperation(value = "감독관 목록", notes = "감독관 목록을 가져옵니다.", tags = "감독관 API")
  @GetMapping(value = "/managers")
  public ResponseEntity getManagersInfo(@RequestParam long courseId) {
    GetManagerListDto managersInfo = managerQueryProcessor.getManagerList(courseId);
    return ResponseEntity.status(HttpStatus.OK).body(managersInfo);
  }
}
