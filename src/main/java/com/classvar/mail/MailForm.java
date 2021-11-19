package com.classvar.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MailForm {
    private String toAddress;
    private String fromAddress;
    private String title;
    private String message;
}
