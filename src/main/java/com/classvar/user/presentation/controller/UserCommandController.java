package com.classvar.user.presentation.controller;

import com.classvar.user.application.UserCommandExecutor;
import com.classvar.user.application.dto.LoginDto;
import com.classvar.user.presentation.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class UserCommandController {

    private final UserCommandExecutor userCommandExecutor;

    @PostMapping(value = "/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto dto, HttpServletRequest request){

        Long loginId = userCommandExecutor.login(dto);

        //login fail
        if(loginId == null){

        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_ID, loginId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}