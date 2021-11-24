package com.classvar.mail;

import com.classvar.student.domain.Student;
import com.classvar.student.domain.StudentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * RegistryMailSender 는 eventPublisher 에 의해
 * StudentCreatedEvent or ManagerCreatedEvent 가 발행되면
 * 해당 클래스의 TransactionalEventListener 에 의해 각각의 method 가 실행 됨
 */
@Component
@RequiredArgsConstructor
public class RegistryMailSender {

    private final MailService mailService;

    private final String FROM_ADDRESS = "registry@classvar.com";

    @TransactionalEventListener
    public void sendRegistryMailToStudent(StudentCreatedEvent createdStudentEvent) {
        Student student = createdStudentEvent.getStudent();

        String title = "ClassVar 응시자 등록 메일입니다.";
        String registryUrl = "classvar.com/students/registry/" + student.getUuid();
        String message = "안녕하세요 응시자 등록 서비스 입니다.\n" +
                "링크에 접속해서 응시자 정보를 등록해주시기 바랍니다.\n" +
                registryUrl;

        SimpleMailForm simpleMailForm = new SimpleMailForm(student.getEmail(), FROM_ADDRESS, title, message);

        mailService.sendMail(simpleMailForm);
    }
}

