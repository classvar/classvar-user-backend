package com.classvar.admin.controller;

import com.classvar.admin.application.dto.response.GetAdminInfoDto;
import com.classvar.common.argumentresolver.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "회원 API")
@Controller
@RequiredArgsConstructor
public class AdminQueryController {

  @ApiOperation(value = "회원 정보", notes = "로그인한 회원의 정보를 가져옵니다.", tags = "회원 API")
  @GetMapping(value = "/admin")
  public ResponseEntity getAuthorizedAdmin(@Login GetAdminInfoDto dto) {
    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }
}
