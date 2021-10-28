package com.classvar.user.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String email;
    String password;

    String name;
    String department;
}
