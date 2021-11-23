package com.classvar.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * application.yml 의 설정을 토대로 spring bean 에 JavaMailSenderImpl 가 등록된다.
 * bean 에 등록된 JavaMailSenderImpl 을 mailSender 에 생성자 주입으로 받아서 사용한다.
 */
@Service
@RequiredArgsConstructor
public class SpringMailService implements MailService{

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(SimpleMailForm mailForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailForm.getToAddress());
        message.setFrom(mailForm.getFromAddress());
        message.setSubject(mailForm.getTitle());
        message.setText(mailForm.getMessage());

        mailSender.send(message);
    }
}