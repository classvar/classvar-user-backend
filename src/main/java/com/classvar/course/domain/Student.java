package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private String studentId;
    private String email;
    private Boolean verified;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    protected Student() {}

    public Student(String email){
        this.email = email;
        this.uuid = UUID.randomUUID().toString();
        this.verified = false;

    }

    public void updateStudentInfo(String name, String department, String studentId, String email){
        this.name = name;
        this.department = department;
        this.studentId = studentId;
        this.email = email;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void isVerified(){
        verified = true;
    }
}