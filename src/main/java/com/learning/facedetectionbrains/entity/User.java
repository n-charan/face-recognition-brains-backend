package com.learning.facedetectionbrains.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String email;

    @Column
    private String password;

    @Column
    private Integer entries = 0;

    @Column
    private LocalDateTime joined = LocalDateTime.now();
}
