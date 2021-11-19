package com.classvar.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail(MailForm mailForm){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailForm.getToAddress());
        message.setFrom(mailForm.getFromAddress());
        message.setSubject(mailForm.getTitle());
        message.setText(mailForm.getMessage());

        mailSender.send(message);
    }
}
