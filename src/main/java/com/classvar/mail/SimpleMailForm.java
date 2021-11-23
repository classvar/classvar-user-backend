package com.classvar.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimpleMailForm {
    private String toAddress;
    private String fromAddress;
    private String title;
    private String message;
}
